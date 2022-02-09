/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ClientServerConnection;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 *
 * @author alfon
 */
public class Connection {

    private Socket socket;
    private ObjectInputStream inputBuffer;
    private ObjectOutputStream outputBuffer;
    private Thread connectionThread;
    private Message message = new Message();

    public Connection(Socket socket) {
        this.socket = socket;
        this.runConnection();
    }

    public Socket getSocket() {
        return socket;
    }

    
    
    
    public void runConnection() {
        connectionThread = new Thread(new Runnable() {
            @Override
            public void run() {
                chanel();
                recive();
                send(message);
            }
        });
        connectionThread.start();
    }

    public void chanel() {
        try {
            outputBuffer = new ObjectOutputStream(socket.getOutputStream());
            inputBuffer = new ObjectInputStream(socket.getInputStream());
        } catch (Exception e) {
            System.out.println("Chanel error: " + e.getMessage());
        }
    }

    public void recive() {
        Thread fil = new Thread(new Runnable() {
            @Override
            public void run() {
                Message message;
                while (true) {
                    try{
                        message = (Message)inputBuffer.readObject();
                        System.out.println(socket.getInetAddress()+ ": " + message.getMessage());
                    } catch (Exception e) {
                        System.out.println("recive error: " + e.getMessage());
                    }
                }
            }
        });
        fil.start();
    }

    public void send(Message message) {
        try {
            outputBuffer.writeObject(message);

        } catch (Exception e) {
            System.out.println("sernd error: " + e.getMessage());
        }
    }

   

}
