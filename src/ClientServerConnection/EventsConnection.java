package ClientServerConnection;

import java.util.EventObject;

/**
 * Define an object that will triguer the events, in this case the Connection
 *
 * @author alfon
 */
public class EventsConnection extends EventObject {

    //VARIABLES
    private static final long serialVersionUID = 1L;
    private Connection connection;

    //CONSTRUCTORS
    /**
     * sets the variable connection
     *
     * @param connection
     */
    public EventsConnection(Connection connection) {
        super(connection);
        this.connection = connection;
    }

    //GETTERS AND SETTERS
    public Connection getConnection() {
        return connection;
    }

}
