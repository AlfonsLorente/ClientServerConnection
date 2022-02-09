/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ClientServerConnection;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author alfon
 */
public class Model {

    public static ArrayList<Connection> bufferConnection = new ArrayList<Connection>();

    public Model(){
        Server server = new Server(bufferConnection);
        //Client client = new Client(bufferConnection);
        int port = 7070;
        server.run(port);
        //client.run();
        }
        /*
        System.out.println("Choose one:");
        System.out.println("1. Start connection");
        System.out.println("2. See connections");
        System.out.print("> ");
        int option = Integer.parseInt(scanner.nextLine());
        switch (option) {
            case 1:
                client.run();
                break;
            case 2:
                for (int i = 0; i < bufferConnection.size(); i++) {
                    bufferConnection.get(i).getSocket().getInetAddress();
                }
                break;

        }*/

    

}
