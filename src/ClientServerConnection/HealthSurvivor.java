package ClientServerConnection;

import java.io.IOException;
import java.net.Socket;

/**
 * Trys to reconnect with the device when the connection has failed for an
 * amount of time
 *
 * @author alfon
 */
public class HealthSurvivor implements Runnable {

    //VARIABLES
    private EventsConnection event;
    private static final int MAX_WAIT_TIME = 240;//1 min 120 try

    //CONSTRUCTORS
    /**
     * Sets the event
     *
     * @param event EventsConnection
     */
    public HealthSurvivor(EventsConnection event) {
        this.event = event;
    }

    //OVERRIDE METHODS
    /**
     * trys to reconect to the old socked
     */
    @Override
    public void run() {
        int count = 0;
        while (count < MAX_WAIT_TIME) {
            try {
                //Reconect to the server socket
                Socket socket = new Socket(event.getConnection().getIP(), event.getConnection().getPort());
                //Change the new socket in eventsconnection
                event.getConnection().setSocket(socket);
                System.out.println("Connection restablished");
                break;
            } catch (IOException e) {
            }
            try {
                //Wait 500ms to reconect
                Thread.sleep(500);
                count++;
            } catch (InterruptedException e) {
            }
        }
        //If the connection didn't recover
        if (count == MAX_WAIT_TIME) {
            System.out.println("Connexion canceled");
        }
    }

}
