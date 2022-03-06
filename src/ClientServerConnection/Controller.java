package ClientServerConnection;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JButton;

/**
 * Controller class, this controlls all the data that passes between the viewer
 * and the model (MVC), and sets up the app
 *
 * @author alfon
 */
public class Controller implements ActionListener {

    //VARIABLES
    private Viewer viewer;
    private Model model;
    private int serverPort = -1;

    /**
     * Define all the methods that will execute when an event is triggered
     */
    public EventsListener eventsListener = new EventsListener() {

        /**
         * When a new connection is created this will be executed, this will add
         * a new window, and add a listener to the new button created.
         */
        @Override
        public void onNewConnection(EventsConnection event) {
            viewer.newTab(event.getConnection().getIP());
            addListener(viewer.getButtons().get(viewer.getButtons().size() - 1));
        }

        /**
         * When a message is sent this will be executed, this will add text to
         * the text area
         */
        @Override
        public void onNewSendConnection(EventsConnection event, Message msg) {
            viewer.getTextAreas().get(model.getBufferConnection().indexOf(event.getConnection()))
                    .setText(viewer.getTextAreas().get(model.getBufferConnection().indexOf(event.getConnection())).getText()
                            + "\n" + "Me: " + msg.getMessage());
        }

        /**
         * When a message is recived this will execute, this will add text to
         * the text area
         */
        @Override
        public void onNewReciveConnection(EventsConnection event, Message msg) {
            viewer.getTextAreas().get(model.getBufferConnection().indexOf(event.getConnection()))
                    .setText(viewer.getTextAreas().get(model.getBufferConnection().indexOf(event.getConnection())).getText()
                            + "\n" + event.getConnection().getIP() + ": " + msg.getMessage());
        }

        /**
         * When an error in the connection occur this will execute, this will
         * throw a thread that will try to reconect
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
        //server port asking
        viewer.serverPortPopUp();
        this.viewer.getServerPortButton().addActionListener(this);
        this.viewer.getConnectButton().addActionListener(this);

        while (serverPort == -1) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException ex) {
                Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        //initialize server
        this.model.initServer(serverPort);
        //set connect button listener

    }

    //OVERRIDE METHODS
    /**
     * Looks which button has been pressed and do its consequent action
     *
     * @param e - ActionEvent
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        String msgState;
        //When the server port button is pressed gets the port and removes the window
        if (e.getSource().equals(this.viewer.getServerPortButton())) {
            serverPort = Integer.parseInt(viewer.getServerPort().getText());
            viewer.getPopUp().dispose();

        } //When the connect button from the server tab is pressed it trys to create a new connection
        else if (e.getSource().equals(this.viewer.getConnectButton())) {
            //Intentam connectar amb la IP i el port que s'ha indicat a la pestanya servidor
            try {
                msgState = this.model.initConnexion(this.viewer.getIp().getText(), Integer.parseInt(this.viewer.getPort().getText()));
            } catch (Exception error) {
                msgState = "Ip and port error: " + error.getMessage();
            }

            this.viewer.getCommsLog().setText(this.viewer.getCommsLog().getText() + "\n" + msgState);

            //if is another button, send a message 
        } else {
            sendMessage(e);
        }
    }

    //PRIVATE METHODS
    /**
     * Adds a listener to a button
     *
     * @param button - JButton
     */
    private void addListener(JButton button) {
        button.addActionListener(this);
    }

    /**
     * Sends the message at the actual chat you are in
     *
     * @param e - ActionEvent
     */
    private void sendMessage(ActionEvent e) {
        //new message
        Message message = new Message();
        //sets the message up with the text you putted
        message.setMessage(this.viewer.getMessages().get(this.viewer.getButtons().indexOf(e.getSource())).getText());
        //send message to another divice
        this.model.getBufferConnection().get(this.viewer.getButtons().indexOf(e.getSource())).send(message);
        //set the message to the chat's text area
        this.viewer.getMessages().get(this.viewer.getButtons().indexOf(e.getSource())).setText("");
    }

}
