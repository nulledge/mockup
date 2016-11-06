package Core;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by nulledge on 2016-10-06.
 */
public class MessageData implements Serializable {

    public MessageData( String receiver) {
        _receiver = receiver;

        _messages = new ArrayList<Message>();
    }

    public boolean addMessage( Date date, String message, boolean send ) {
        try {
            _messages.add( new Message( date, message, send ) );
        }
        catch( Exception e ) {
            return false;
        }
        return true;
    }

    public String getReceiver() { return _receiver; }
    public int getSize() { return _messages.size(); }
    public Message getMessage( int index ) { return _messages.get( index ); }

    private String _receiver;
    private ArrayList<Message> _messages;
}