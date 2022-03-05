/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ClientServerConnection;

import java.util.EventObject;

public class EventsConnection extends EventObject {

    private static final long serialVersionUID = 1L;
    private Connection connection;

    public EventsConnection(Connection connection) {
        super(connection);
        this.connection = connection;
    }

    //GETTERS AND SETTERS
    public Connection getConnection() {
        return connection;
    }



}
