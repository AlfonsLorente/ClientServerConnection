/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ClientServerConnection;

import java.util.ArrayList;

public class Model {
	private Client client;
	private Server servidor;
	private ArrayList<Connection> bufferConnection = new ArrayList<Connection>();
	private EventsListener eventsListener;
	
	public Model (EventsListener eventsListener) {
		this.eventsListener=eventsListener;
	}

	public void iniciarServidor (int port) {
		servidor = new Server(bufferConnection, eventsListener);
		servidor.runServer(port);
	}
			
	public String establirConnexio (String ip, int port) {
		this.client = new Client(bufferConnection, eventsListener);
		String msgState = this.client.Connectar(ip, port);
		return msgState;
	}
	
	public Server getServidor() {
		return this.servidor;
	}
	
	public ArrayList<Connection> getBufferConnection (){
		return this.bufferConnection;
	}
}