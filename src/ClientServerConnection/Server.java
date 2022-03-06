package ClientServerConnection;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

/**
 * Model class for server
 * @author alfon
 */
public class Server {

    //VARIABLES
    private ServerSocket serverSocket;
    private Socket socket;
    private int port;
    private ArrayList<Connection> bufferConnection;
    private EventsListener eventsListener;

    //CONSTRUCTOR
    /**
     * Initialize variables
     * @param bufferConnection ArrayList of Connection
     * @param eventsListener EventsListener
     */
    public Server(ArrayList<Connection> bufferConnection, EventsListener eventsListener) {
        this.bufferConnection = bufferConnection;
        this.eventsListener = eventsListener;
    }

    //PUBLIC METHODS
    /**
     * Starts the server
     * @param port int
     */
    public void startServer(int port) {
        //Create a server thread
        Thread serverThread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    serverSocket = new ServerSocket(port);
                    while (true) {
                        //wait for a new connection
                        socket = serverSocket.accept();
                        //create a Connection
                        Connection connection = new Connection(socket);
                        connection.addListener(eventsListener);
                        connection.triggerConnectionEvent();//connection trigger
                        bufferConnection.add(connection);//save the connection in the array
                    }
                } catch (Exception e) {
                    System.out.println("serverStart error: " + e.getMessage());
                }
            }
        });
        serverThread.start();
    }
}
