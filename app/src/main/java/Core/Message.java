package Core;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by nulledge on 2016-10-06.
 */
public class Message implements Serializable {

    public Message(Date time, String message, boolean send ) {
        _time = time;
        _message = message;
        _send = send;
    }

    public Date getTime() { return _time; }
    public String getMessage() { return _message; }
    public boolean getSend() { return _send; }
    @Override
    public String toString() {
        StringBuffer buffer = new StringBuffer();
        buffer.append( _time.toString() );
        buffer.append( ", " );
        buffer.append( _message );
        buffer.append( ", " );
        if( _send )
            buffer.append( "send" );
        else
            buffer.append( "receive" );

        return buffer.toString();
    }

    private Date _time;
    private String _message;
    private boolean _send;
}