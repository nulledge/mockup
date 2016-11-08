package Core.Util;

import com.demo.ib.mockup.Register.enums.EventType;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import Core.Info.EventLog;
import Core.Info.UserProfile;

/**
 * Created by nulledge on 2016-11-08.
 */
public class Logger {
    static public Logger getInstance() {
        if( _instance == null )
            _instance = new Logger();
        return _instance;
    }
    static private Logger _instance;

    private Logger() {
        _logs = new ArrayList<EventLog>();
    }


    static public boolean addEvent(Date date, EventType event, String resourceID ) {
        if( UserProfile.getInstance().onRecord() == false )
            return Values.FAIL;
        try {
            getInstance()._logs.add( new EventLog ( date, event, resourceID ) );
        }
        catch (Exception e) {
            return Values.FAIL;
        }
        return Values.SUCCESS;
    }
    static public boolean addEvent( EventType event, String resourceID ) {
        return addEvent(
                Calendar.getInstance().getTime(),
                event,
                resourceID
        );
    }
    static public boolean addEvent( EventType event, int resourceID ) {
        return addEvent(
                Calendar.getInstance().getTime(),
                event,
                ContextResolver.getApplicationContext().getResources().getResourceName( resourceID )
        );
    }

    @Override
     public String toString() {
        StringBuffer buffer = new StringBuffer();
        for( EventLog log : getInstance()._logs ) {
            buffer.append( log.toString() + "\n" );
        }
        return buffer.toString();
    }

    private ArrayList<EventLog> _logs;
}
