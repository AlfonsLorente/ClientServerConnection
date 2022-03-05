/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ClientServerConnection;

import java.io.IOException;
import java.net.Socket;

/**
 *
 * @author alfon
 */
public class HealthSurvivor implements Runnable {

    private EventsConnection event;
    //1 min 120 try
    private static final int MAX_WAIT_TIME = 240;

    public HealthSurvivor(EventsConnection event) {
        this.event = event;
    }
    

    @Override
    public void run() {
        int count = 1;
        while (count <= MAX_WAIT_TIME) {
            try {
                Socket socket = new Socket(event.getConnection().getIP(), event.getConnection().getPort());
                event.getConnection().setSocket(socket);
                System.out.println("Connection restablished");
                break;
            } catch (IOException e) {
            }
            try {
                Thread.sleep(500);
                count++;
            } catch (InterruptedException e) {
            }
        }
        if(count == MAX_WAIT_TIME){
            System.out.println("Connexion canceled");
        }
    }

}
