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

    public ArrayList<Connection> bufferConnection = new ArrayList<Connection>();

    public Model(){
        Server server = new Server(bufferConnection);
        Client client = new Client(bufferConnection);
        int port = 7070;
        server.run(port);

        }
        

    

}
