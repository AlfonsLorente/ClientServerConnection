/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ClientServerConnection;

/**
 *
 * @author alfon
 */
import java.io.*;
import java.net.Socket;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Client {

    private Socket socket;
    private Model myTask;
    private Connection connection;
    private ArrayList<Connection> bufferConnection;

    public Client(ArrayList<Connection> bufferConnection) {
        this.bufferConnection = bufferConnection;
    }

    public void connect(String ip, int port) {
        try {
            socket = new Socket(ip, port);
            connection = new Connection(socket);
            bufferConnection.add(connection);

        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
