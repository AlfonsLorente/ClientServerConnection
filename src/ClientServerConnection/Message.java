package ClientServerConnection;

import java.io.Serializable;

/**
 * Message that will me changed
 *
 * @author alfon
 */
public class Message implements Serializable {

    //VARIABLES
    private String message = new String();

    //GETTERS AND SETTERS
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
