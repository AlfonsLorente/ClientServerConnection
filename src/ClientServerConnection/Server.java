/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ClientServerConnection;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

/**
 *
 * @author alfon
 */
public class Server {
    private ServerSocket serverSocket;
    private Socket socket;
    private Model myTask;
    Thread serverThread;
    private  ArrayList<Connection> bufferConnection;
    
    public Server(ArrayList<Connection> bufferConnection){
        this.bufferConnection = bufferConnection;
    }
    
    public void run(int port){
        serverThread = new Thread(new Runnable() {
            @Override
            public void run() {
               try{
                    serverSocket = new ServerSocket(port);
                    while(true){
                        System.out.println("Waiting for connection...");
                        socket = serverSocket.accept();
                        Connection connection = new Connection(socket);
                        myTask.bufferConnection.add(connection);
                        System.out.println("Buffer Size: " + myTask.bufferConnection.size());

                    }
                }catch(Exception e){
                System.out.println(e.getMessage());
            }
        }
        });
        serverThread.start();
   
    }
}
