package ClientServerConnection;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.ListIterator;

/**
 * Connection class, this comunicates with the client
 *
 * @author alfon
 */
public class Connection {

    //VARIABLES
    private Socket socket;
    private ObjectInputStream in;
    private ObjectOutputStream out;
    private String ip;
    private int port;

    private ArrayList<EventsListener> listeners = new ArrayList<EventsListener>();

    //CONSTRUCTORS
    /**
     * Set up variables and run connection
     *
     * @param socket - Socket
     */
    public Connection(Socket socket) {
        this.socket = socket;
        this.ip = this.socket.getInetAddress().toString().substring(1, this.socket.getInetAddress().toString().length());
        this.port = this.socket.getPort();
        runConnection();
    }

    //GETTERS AND SETTERS
    public String getIP() {
        return ip;
    }

    public int getPort() {
        return port;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
        runConnection();
    }

    //PUBLIC METHODS
    /**
     * Creates a thread that sends a message to the server
     *
     * @param message - Message
     */
    public synchronized void send(Message message) {
        Thread connectionThread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    out.writeObject(message);
                    triggerSendEvent(message);
                } catch (Exception e) {
                    triggerErrorEvent();
                    try {
                        socket.close();
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
                }
            }
        });
        connectionThread.start();
    }

    /**
     * Adds a listener to the array
     *
     * @param listener - EventsListener
     */
    public void addListener(EventsListener listener) {
        listeners.add(listener);
    }

    /**
     * Triggers all the connection events
     */
    public void triggerConnectionEvent() {
        ListIterator<EventsListener> li = listeners.listIterator();
        while (li.hasNext()) {
            EventsListener listener = (EventsListener) li.next();
            EventsConnection readerEvents = new EventsConnection(this);
            (listener).onNewConnection(readerEvents);
        }
    }

    /**
     * Triggers all the sends events
     */
    public void triggerSendEvent(Message msg) {
        ListIterator<EventsListener> li = listeners.listIterator();
        while (li.hasNext()) {
            EventsListener listener = (EventsListener) li.next();
            EventsConnection readerEvents = new EventsConnection(this);
            (listener).onNewSendConnection(readerEvents, msg);
        }
    }

    /**
     * Triggers all the recive listeners
     *
     * @param msg - Message
     */
    public void triggerReciveEvent(Message msg) {
        ListIterator<EventsListener> li = listeners.listIterator();
        while (li.hasNext()) {
            EventsListener listener = (EventsListener) li.next();
            EventsConnection readerEvents = new EventsConnection(this);
            (listener).onNewReciveConnection(readerEvents, msg);
        }
    }

    /**
     * Triger all the error listeners
     */
    public void triggerErrorEvent() {
        ListIterator<EventsListener> li = listeners.listIterator();
        while (li.hasNext()) {
            EventsListener listener = (EventsListener) li.next();
            EventsConnection readerEvents = new EventsConnection(this);
            (listener).onNewErrorConnection(readerEvents);
        }
    }

    //PRIVATE METHODS
    /**
     * Set up channels and recive
     */
    private void runConnection() {
        setChannels();
        recive();
    }

    /**
     * set up channels
     *
     * @return String
     */
    private String setChannels() {
        String messageState = "";
        try {
            out = new ObjectOutputStream(socket.getOutputStream());//output channel
            in = new ObjectInputStream(socket.getInputStream());//input channel

        } catch (Exception e) {
            messageState = "Channel error: " + e.getMessage();
        }
        return messageState;
    }

    /**
     * Creates a thread that listens for the input of a message
     */
    private void recive() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public synchronized void run() {
                Message message;
                while (true) {
                    try {
                        message = (Message) in.readObject();
                        triggerReciveEvent(message);
                    } catch (Exception e) {
                        triggerErrorEvent();
                        break;
                    }
                }
            }
        });
        thread.start();
    }

}
