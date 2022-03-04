/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ClientServerConnection;

/**
 *
 * @author alfon
 */
import java.net.Socket;
import java.util.ArrayList;

import java.net.Socket;
import java.util.ArrayList;

public class Client {
	private ArrayList<Connection> bufferConnection;
	private EventsListener eventsListener;
	
	public Client (ArrayList<Connection> bufferConnection,EventsListener eventsListener) {
		this.bufferConnection=bufferConnection;
		this.eventsListener=eventsListener;
	}
	
	public String Connect(String ip, int port) {
		String msgState;
		try {
			Socket socket = new Socket(ip,port);
			Connection connection = new Connection(socket);
			connection.addListener(eventsListener);
			connection.triggerConnectionEvent();
			bufferConnection.add(connection);
			msgState = "Connexió establerta amb: "+ip+":"+port;
		}catch (Exception e) {
			System.out.println("Error al connectar: "+ e.getMessage());
			msgState = "Error al connectar: "+ e.getMessage();
		}
		return msgState;
	}
}