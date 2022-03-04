/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ClientServerConnection;

import java.util.EventListener;

//A la interficie definim quins metodes és podrán executar quan és disparin els events
public interface EventsListener extends EventListener {

    public abstract void onNewConnection(EventsConnection event);

    public abstract void onNewSendConnection(EventsConnection event, Message msg);

    public abstract void onNewReciveConnection(EventsConnection event, Message msg);

    public abstract void onNewErrorConnection(EventsConnection event);
}
