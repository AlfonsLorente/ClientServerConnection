/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ClientServerConnection;

import java.util.ArrayList;

/**
 *
 * @author alfon
 */
public class MyTask {
    public ArrayList<Connection> bufferConnection = new ArrayList<Connection>();
    
    public static void main(String[] args) {
        MyTask myTask = new MyTask();
        Server server = new Server(myTask);
        Client client = new Client(myTask);
        int port = 7070;
        server.run(port);
        client.run();
    }
    
    
}
