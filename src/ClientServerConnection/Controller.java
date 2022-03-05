
package ClientServerConnection;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;

import javax.swing.JButton;

/**
 * Controller class, this controlls all the data that passes between the viewer and the model (MVC), 
 * and sets up the app
 * @author alfon
 */
public class Controller implements ActionListener {
    //VARIABLES
    private Viewer viewer;
    private Model model;

    /**
     * Define all the methods that will execute when an event is triggered
     */
    public EventsListener eventsListener = new EventsListener() {

        /**
         * When a new connection is created this will be executed, 
         * this will add a new window, and add a listener to the new button created.
        */
        @Override
        public void onNewConnection(EventsConnection event) {
            viewer.newTab(event.getConnection().getIP());
            addListener(viewer.getButtons().get(viewer.getButtons().size() - 1));
        }

        /**
         * When a message is sent this will be executed, 
         * this will add text to the text area
         */
        @Override
        public void onNewSendConnection(EventsConnection event, Message msg) {
            viewer.getTextAreas().get(model.getBufferConnection().indexOf(event.getConnection()))
                    .setText(viewer.getTextAreas().get(model.getBufferConnection().indexOf(event.getConnection())).getText() + 
                            "\n" + "Me: " + msg.getMessage());
        }

        /**
         * When a message is recived this will execute,
         * this will add text to the text area
         */
        @Override
        public void onNewReciveConnection(EventsConnection event, Message msg) {
            viewer.getTextAreas().get(model.getBufferConnection().indexOf(event.getConnection()))
                    .setText(viewer.getTextAreas().get(model.getBufferConnection().indexOf(event.getConnection())).getText() + 
                            "\n" + event.getConnection().getIP() + ": " + msg.getMessage());
        }

        /**
         * When an error in the connection occur this will execute,
         * this will throw a thread that will try to reconect
         */
        @Override
        public void onNewErrorConnection(EventsConnection event) {
            HealthSurvivor hs = new HealthSurvivor(event);
            Thread healthThread = new Thread(hs);
            healthThread.start();
        }
    };

    
    //CONSTRUCTORS
    /**
     * Starts the viewer and the model, and sets up the app
     */
    public Controller() {
        viewer = new Viewer();
        model = new Model(eventsListener);

        ////////////////////
        int port = Integer.parseInt(viewer.serverPortPopUp());
        ////////////////////
        
        this.model.initServer(port);
        this.viewer.getConnectButton().addActionListener(this);

    }

    //OVERRIDE METHODS
    /**
     * 
     * @param e - ActionEvent
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        String msgState;
        //Quan és pitja el boto connectar de la pestanya servidor intentam crear connexió
        //Sí es un altre boto és perque volem enviar un missatge.
        if (e.getSource().equals(this.viewer.getConnectButton())) {
            //Intentam connectar amb la IP i el port que s'ha indicat a la pestanya servidor
            try {
                msgState = this.model.initConnexion(this.viewer.getIp().getText(), Integer.parseInt(this.viewer.getPort().getText()));
            } catch (Exception error) {
                msgState = "Error amb la IP i el port: " + error.getMessage();
            }

            this.viewer.getCommsLog().setText(this.viewer.getCommsLog().getText() + "\n" + msgState);
        } else {
            sendMessage(e);
        }
    }

    //PUBLIC METHODS
    public void addListener(JButton button) {
        button.addActionListener(this);
    }

    public void sendMessage(ActionEvent e) {
        Message message = new Message();
        message.setMessage(this.viewer.getMessages().get(this.viewer.getButtons().indexOf(e.getSource())).getText());
        this.model.getBufferConnection().get(this.viewer.getButtons().indexOf(e.getSource())).send(message);
        this.viewer.getMessages().get(this.viewer.getButtons().indexOf(e.getSource())).setText("");
    }

}
