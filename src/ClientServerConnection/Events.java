/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ClientServerConnection;

import java.util.EventObject;

/**
 *
 * @author alfon
 */
public class Events extends EventObject {

    private static final long serialVersionUID = 1L;
    private Server server;

    public Events(Server server) {
        super(server);
        this.server = server;
    }

    public Server getServer() {
        return server;
    }

    public void setServer(Server server) {
        this.server = server;
    }

}
