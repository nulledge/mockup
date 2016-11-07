package Core.Info;

import com.demo.ib.mockup.Register.enums.EventType;

import java.text.SimpleDateFormat;
import java.util.Date;

import Core.Util.Values;

/**
 * Created by nulledge on 2016-11-08.
 */
public class EventLog {

    public EventLog(Date time, EventType event, String name) {
        _time = time;
        _event = event;
        _name = name;
    }

    @Override
    public String toString() {
        StringBuffer _return = new StringBuffer();
        _return.append( Values.Format().format( _time ) )
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
