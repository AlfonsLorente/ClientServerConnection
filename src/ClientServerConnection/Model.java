package ClientServerConnection;

import java.util.ArrayList;

/**
 * Data model to store the data values
 *
 * @author alfon
 */
public class Model {

    //VARIABLES
    private Client client;
    private Server server;
    private ArrayList<Connection> bufferConnection = new ArrayList<Connection>();
    private EventsListener eventsListener;

    //CONSTRUCTORS
    /**
     * Initialize eventsListener
     *
     * @param eventsListener EventsListeners
     */
    public Model(EventsListener eventsListener) {
        this.eventsListener = eventsListener;
    }

    //GETTERS AND SETTERS
    public Server getServer() {
        return this.server;
    }

    public ArrayList<Connection> getBufferConnection() {
        return bufferConnection;
    }

    //PUBLIC METHODS
    /**
     * Starts the server
     *
     * @param port int
     */
    public void initServer(int port) {
        server = new Server(bufferConnection, eventsListener);
        server.startServer(port);
    }

    /**
     * Initialize the connection with the client
     *
     * @param ip String
     * @param port int
     * @return String
     */
    public String initConnexion(String ip, int port) {
        this.client = new Client(bufferConnection, eventsListener);
        String messageState = this.client.Connect(ip, port);
        return messageState;
    }

}
