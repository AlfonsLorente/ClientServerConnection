/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ClientServerConnection;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

import javax.swing.JButton;

public class Controller implements ActionListener {
    //El controlador gestiona el pas d'informació de la vista gràfica al model de dades

    Viewer vista;
    Model model;

    //Definir els mètodes que s'han d'executar quan s'activi un event
    public EventsListener eventsListener = new EventsListener() {
        @Override
        //Aquest event s'executarà quan es crei una nova Connexió, ja sigui
        //per part de la classe client com de la classe servidor.
        //Afegeix una nova pestanya i afegeix un listener al nou boto creat.
        public void onNewConnection(EventsConnection event) {
            vista.newTab(event.getConnection().getIP());
            addListener(vista.getButtons().get(vista.getButtons().size() - 1));
        }

        @Override
        //Quan enviam un missatge actualitzam les areas de text.
        public void onNewSendConnection(EventsConnection event, Message msg) {
            vista.getTextAreas().get(model.getBufferConnection().indexOf(event.getConnection()))
                    .setText(vista.getTextAreas().get(model.getBufferConnection().indexOf(event.getConnection())).getText() + "\n" + "Me: " + msg.getMessage());
        }

        @Override
        //Quan rebem un missatge actualitzam les areas de text.
        public void onNewReciveConnection(EventsConnection event, Message msg) {
            vista.getTextAreas().get(model.getBufferConnection().indexOf(event.getConnection()))
                    .setText(vista.getTextAreas().get(model.getBufferConnection().indexOf(event.getConnection())).getText() + "\n" + event.getConnection()
                            .getIP() + ": " + msg.getMessage());
        }

        @Override
        public void onNewErrorConnection(EventsConnection event) {
            Thread healthThread = new Thread(new Runnable() {
                @Override
                public void run() {
                    while (true) {
                        try {
                            Socket socket = new Socket(event.getConnection().getIP(), event.getConnection().getPort());
                            event.getConnection().setSocket(socket);
                            break;
                        } catch (IOException e) {
                        }
                        try {
                            Thread.sleep(1 * 1000);
                        } catch (InterruptedException e) {
                        }
                    }
                }
            });
            healthThread.start();
        }
    };

    public Controller() {
        vista = new Viewer();
        model = new Model(eventsListener);

        ////////////////////
        Scanner teclat = new Scanner(System.in);
        System.out.println("Port del servidor: ");
        int port = Integer.parseInt(teclat.nextLine());
        ////////////////////

        this.model.initServer(port);

        this.vista.getConnectButton().addActionListener(this);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String msgState;
        //Quan és pitja el boto connectar de la pestanya servidor intentam crear connexió
        //Sí es un altre boto és perque volem enviar un missatge.
        if (e.getSource().equals(this.vista.getConnectButton())) {
            //Intentam connectar amb la IP i el port que s'ha indicat a la pestanya servidor
            try {
                msgState = this.model.initConnexion(this.vista.getIp().getText(), Integer.parseInt(this.vista.getPort().getText()));
            } catch (Exception error) {
                msgState = "Error amb la IP i el port: " + error.getMessage();
            }

            this.vista.getCommsLog().setText(this.vista.getCommsLog().getText() + "\n" + msgState);
        } else {
            sendMessage(e);
        }
    }

    public void addListener(JButton button) {
        button.addActionListener(this);
    }

    public void sendMessage(ActionEvent e) {
        Message message = new Message();
        message.setMessage(this.vista.getMessages().get(this.vista.getButtons().indexOf(e.getSource())).getText());
        this.model.getBufferConnection().get(this.vista.getButtons().indexOf(e.getSource())).send(message);
        this.vista.getMessages().get(this.vista.getButtons().indexOf(e.getSource())).setText("");
    }

}
