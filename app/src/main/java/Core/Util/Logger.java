package Core.Util;

import Core.enums.EventType;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import Core.Info.EventLog;
import Core.Info.UserProfile;

/**
 * Created by nulledge on 2016-11-08.
 */
public class Logger {
    static private ArrayList<EventLog> _logs = new ArrayList<EventLog>();


    static public boolean addEvent(Date date, EventType event, String resourceID ) {
        if( UserProfile.getInstance().onRecord() == false )
            return false;
        try {
            _logs.add( new EventLog ( date, event, resourceID ) );
        }
        catch (Exception e) {
            return false;
        }
        return true;
    }
    static public boolean addEvent( EventType event, String resourceID ) {
        return addEvent(
                Calendar.getInstance().getTime(),
                event,
                resourceID
        );
    }
    static public boolean addEvent( EventType event, int resourceID ) {
        String name = ContextResolver.getApplicationContext().getResources().getResourceName( resourceID );
        return addEvent(
                Calendar.getInstance().getTime(),
                event,
                name.split( "/" )[ 1 ].toString()
        );
    }

    static public void clear() {
        _logs.clear();
    }

     static public String publish() {
        StringBuffer buffer = new StringBuffer();
        for( EventLog log : _logs ) {
            buffer.append( log.toString() + "\n" );
        }
        return buffer.toString();
    }
}
