package Core;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;

import com.demo.ib.mockup.R;
import com.demo.ib.mockup.Register.enums.EventType;
import com.demo.ib.mockup.Register.enums.RegisterType;
import com.demo.ib.mockup.Register.enums.TaskType;

import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 * Created by nulledge on 2016-09-22.
 */

public class UserProfile {
    static public UserProfile GetInstance() {
        if( _instance == null )
            _instance = new UserProfile();
        return _instance;
    }
    static private UserProfile _instance = null;

    private UserProfile() {
        _log = new ArrayList<EventLog>();
    }
    public void start() {
        _onRecord = true;
        _log.clear();
        DummyMessage.getInstance().refresh();
    }
    public void stop() {
        _onRecord = false;
        Log.d( "UserProfile", "Stop Record");
    }

    public boolean setProfile( String name, TaskType taskType ) {
        _name = name;
        _task = taskType;

        return true;
    }
    public boolean addEvent( Date date, EventType event, String resourceID ) {
        if( _onRecord == false )
            return false;
        try {
            _log.add(
                    new EventLog (
                            date,
                            event,
                            resourceID
                    ));
        }
        catch (Exception e) {
            return false;
        }
        Log.d( "LOG", _log.get(_log.size() - 1).toString() );
        return true;
    }
    public boolean addEvent( EventType event, String resourceID ) {
        if( _onRecord == false )
            return false;
        try {
            _log.add(
                    new EventLog (
                            Calendar.getInstance().getTime(),
                            event,
                            resourceID
                    ));
        }
        catch (Exception e) {
            return false;
        }
        Log.d( "LOG", _log.get(_log.size() - 1).toString() );
        return true;
    }
    public boolean addEvent( EventType event, int resourceID ) {
        if( _onRecord == false )
            return false;
        try {
            _log.add(
                new EventLog (
                        Calendar.getInstance().getTime(),
                        event,
                        resourceID
                        ));
        }
        catch (Exception e) {
            return false;
        }
        Log.d( "LOG", _log.get(_log.size() - 1).toString() );
        return true;
    }

    public void registerEventLogger(Activity activity, int resourceID, RegisterType type) {
        CustomOnClickListener customOnClickListener = new CustomOnClickListener( resourceID );
        if( type == RegisterType.Both || type == RegisterType.Click )
            activity.findViewById(resourceID).setOnClickListener( customOnClickListener );
        if( type == RegisterType.Both || type == RegisterType.LongClick )
        activity.findViewById(resourceID).setOnLongClickListener( customOnClickListener );
    }

    class CustomOnClickListener implements View.OnClickListener, View.OnLongClickListener {

        CustomOnClickListener( int resourceID ) { _resourceID = resourceID; }
        int _resourceID;

        @Override
        public void onClick(View v) {
            UserProfile.GetInstance().addEvent(EventType.Click, _resourceID );
        }

        @Override
        public boolean onLongClick(View v) {
            UserProfile.GetInstance().addEvent(EventType.LongClick, _resourceID );
            return false;
        }
    }

    public String toString() {
        StringBuffer buffer = new StringBuffer();
        for( EventLog log : _log ) {
            buffer.append( log.toString() + "\n" );
        }

        return buffer.toString();
    }

    public TaskType getTask() { return _task; }

    private String _name;
    private TaskType _task;
    private ArrayList<EventLog> _log;
    private boolean _onRecord;
    static public Activity _activity;
}

class EventLog {

    public EventLog( Date time, EventType event, String name) {
        _time = time;
        _event = event;
        _name = name;
    }

    public EventLog ( Date time, EventType event, int resourceID ) {
        _time = time;
        _event = event;
        _resourceID = resourceID;
    }

    @Override
    public String toString() {
        StringBuffer _return = new StringBuffer();
        _return.append( Format.format( _time ) );
        _return.append( ", " );
        _return.append( _event.toString() );
        _return.append( ", " );
        if( _resourceID != 0 )
            _return.append( UserProfile._activity.getResources().getResourceName( _resourceID ) );
        else
            _return.append( _name );
        return _return.toString();
    }

    private Date _time;
    private EventType _event;
    private int _resourceID;
    private String _name;

    static private SimpleDateFormat Format = new SimpleDateFormat( "yyyy/MM/dd hh:mm:ss" );
}
