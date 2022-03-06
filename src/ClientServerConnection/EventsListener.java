package ClientServerConnection;

import java.util.EventListener;

//A la interficie definim quins metodes és podrán executar quan és disparin els events
/**
 * Interface that defines which methods can be executed on event triggering
 *
 * @author alfon
 */
public interface EventsListener extends EventListener {

    //PUBLIC METHODS
    /**
     * When a new connection is creted
     *
     * @param event EventConnection
     */
    public abstract void onNewConnection(EventsConnection event);

    /**
     * When a message is sent
     *
     * @param event - EventConnection
     * @param msg - Message
     */
    public abstract void onNewSendConnection(EventsConnection event, Message msg);

    /**
     * When a message is recived
     *
     * @param event - EventsConnection
     * @param msg - Message
     */
    public abstract void onNewReciveConnection(EventsConnection event, Message msg);

    /**
     * When a connection error occur
     *
     * @param event EventsConnection
     */
    public abstract void onNewErrorConnection(EventsConnection event);
}
