/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ClientServerConnection;

import java.net.Socket;
import java.util.ArrayList;

/**
 * Clase client
 *
 * @author alfon
 */
public class Client {
    //VARIABLES
    private ArrayList<Connection> bufferConnection;
    private EventsListener eventsListener;

    //CONSTRUCTOR
    /**
     * Initialitzes variables
     *
     * @param bufferConnection - Arraylist of Connection
     * @param eventsListener - EventListener
     */
    public Client(ArrayList<Connection> bufferConnection, EventsListener eventsListener) {
        this.bufferConnection = bufferConnection;
        this.eventsListener = eventsListener;
    }

    //PUBLIC METHODS
    /**
     * Trys to connect to the server
     *
     * @param ip - String
     * @param port - int
     * @return String - Message state
     */
    public String Connect(String ip, int port) {
        String messageState;
        try {
            //Connection to the server
            Socket socket = new Socket(ip, port);
            //Starts the connection
            Connection connection = new Connection(socket);
            connection.addListener(eventsListener);
            connection.triggerConnectionEvent();
            bufferConnection.add(connection);
            messageState = "Established connection: " + ip + ":" + port;
        } catch (Exception e) {
            messageState = "Connection error: " + e.getMessage();
        }
        return messageState;
    }
}
