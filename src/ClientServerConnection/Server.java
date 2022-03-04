/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ClientServerConnection;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server {

    private ServerSocket serverSocket;
    private Socket socket;
    private int port;
    private ArrayList<Connection> bufferConnection;
    private EventsListener eventsListener;

    public Server(ArrayList<Connection> bufferConnection, EventsListener eventsListener) {
        this.bufferConnection = bufferConnection;
        this.eventsListener = eventsListener;
    }

    public void startServer(int port) {
        Thread serverThread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    serverSocket = new ServerSocket(port);
                    while (true) {
                        socket = serverSocket.accept();
                        Connection connection = new Connection(socket);
                        connection.addListener(eventsListener);
                        connection.triggerConnectionEvent();
                        bufferConnection.add(connection);
                    }
                } catch (Exception e) {
                    System.out.println("serverStart error: " + e.getMessage());
                }
            }
        });
        serverThread.start();
    }
}
