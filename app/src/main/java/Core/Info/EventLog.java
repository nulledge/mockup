package Core.Info;

import Core.enums.EventType;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by nulledge on 2016-11-08.
 */
public class EventLog {
    static private final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat( "yyyy/MM/dd hh:mm:ss" );

    public EventLog(Date time, EventType event, String name) {
        _time = time;
        _event = event;
        _name = name;
    }

    @Override
    public String toString() {
        StringBuffer _return = new StringBuffer();
        _return.append( DATE_FORMAT.format( _time ) )
                .append( ", " )
                .append( _event.toString() )
                .append( ", " )
                .append( _name );
        return _return.toString();
    }

    private Date _time;
    private EventType _event;
    private String _name;
}
