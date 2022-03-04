/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ClientServerConnection;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.ListIterator;

public class Connection {

    private Socket socket;
    private ObjectInputStream bufferEntrada;
    private ObjectOutputStream bufferSortida;
    private String ip;
    private int port;
    private int localPort;

    private ArrayList<EventsListener> listeners = new ArrayList<EventsListener>();

    public Connection(Socket socket) {
        this.socket = socket;
        this.ip = this.socket.getInetAddress().toString().substring(1, this.socket.getInetAddress().toString().length());
        System.out.println(this.ip);
        this.port = this.socket.getPort();
        System.out.println(this.port);
        this.localPort = this.socket.getLocalPort();
        System.out.println(this.localPort);
        runConnection();
    }

    public void runConnection() {
        channels();
        recive();
    }

    public String channels() {
        String msgState = "";
        try {
            bufferSortida = new ObjectOutputStream(socket.getOutputStream());
            bufferEntrada = new ObjectInputStream(socket.getInputStream());
        } catch (Exception e) {
            msgState = "Error als canals: " + e.getMessage();
        }
        return msgState;
    }

    public synchronized void send(Message msg) {
        Thread connectionThread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    bufferSortida.writeObject(msg);
                    triggerSendEvent(msg);
                } catch (Exception e) {
                    System.out.println("Error a enviar: " + e.getMessage());
                    triggerErrorEvent();
                    try {
                        socket.close();
                    } catch (IOException e1) {
                        // TODO Auto-generated catch block
                        e1.printStackTrace();
                    }
                }
            }
        });
        connectionThread.start();
    }

    public void recive() {
        Thread fil = new Thread(new Runnable() {
            @Override
            public synchronized void run() {
                Message message;
                while (true) {
                    try {
                        message = (Message) bufferEntrada.readObject();
                        triggerReciveEvent(message);
                        System.out.println(socket.getInetAddress() + ": " + message.getMessage());
                    } catch (Exception e) {
                        System.out.println("Error a rebre: " + e.getMessage());
                        triggerErrorEvent();
                        break;
                    }
                }
            }
        });
        fil.start();
    }

    public void addListener(EventsListener listener) {
        listeners.add(listener);
    }

    public void triggerConnectionEvent() {
        ListIterator<EventsListener> li = listeners.listIterator();
        while (li.hasNext()) {
            EventsListener listener = (EventsListener) li.next();
            EventsConnection readerEvents = new EventsConnection(this);
            (listener).onNewConnection(readerEvents);
        }
    }

    public void triggerSendEvent(Message msg) {
        ListIterator<EventsListener> li = listeners.listIterator();
        while (li.hasNext()) {
            EventsListener listener = (EventsListener) li.next();
            EventsConnection readerEvents = new EventsConnection(this);
            (listener).onNewSendConnection(readerEvents, msg);
        }
    }

    public void triggerReciveEvent(Message msg) {
        ListIterator<EventsListener> li = listeners.listIterator();
        while (li.hasNext()) {
            EventsListener listener = (EventsListener) li.next();
            EventsConnection readerEvents = new EventsConnection(this);
            (listener).onNewReciveConnection(readerEvents, msg);
        }
    }

    public void triggerErrorEvent() {
        ListIterator<EventsListener> li = listeners.listIterator();
        while (li.hasNext()) {
            EventsListener listener = (EventsListener) li.next();
            EventsConnection readerEvents = new EventsConnection(this);
            (listener).onNewErrorConnection(readerEvents);
        }
    }

    public String getIP() {
        return this.ip;
    }

    public int getPort() {
        return this.port;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
        runConnection();
    }

}
