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
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Client {

    private Socket socket;
    private MyTask myTask;
    private Connection connection;

    public Client(MyTask myTask) {
        this.myTask = myTask;
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);

        Thread clientThread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {

                    System.out.println("Introduce the server IP:");
                    String ip = scanner.nextLine();
                    System.out.println("Introduce the server port");
                    int port = Integer.parseInt(scanner.nextLine());

                    try {
                        socket = new Socket(ip, port);
                        connection = new Connection(socket);
                        myTask.bufferConnection.add(connection);

                    } catch (IOException ex) {
                        Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        });
        clientThread.start();

    }
}
