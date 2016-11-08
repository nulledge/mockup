package Core.Info;

import android.app.Activity;
import android.util.Log;
import android.view.View;

import com.demo.ib.mockup.Register.enums.EventType;
import com.demo.ib.mockup.Register.enums.RegisterType;
import com.demo.ib.mockup.Register.enums.TaskType;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import Core.DummyMessage;
import Core.Util.Logger;

/**
 * Created by nulledge on 2016-09-22.
 */

public class UserProfile {

    static public UserProfile getInstance() {
        if( _instance == null )
            _instance = new UserProfile();
        return _instance;
    }
    static private UserProfile _instance = null;

    private UserProfile() {
        _onRecord = false;
    }

    static public boolean setProfile( String name, TaskType taskType ) {
        getInstance()._name = name;
        getInstance()._task = taskType;

        return true;
    }

    public boolean onRecord() { return _onRecord; }

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
            Logger.addEvent(EventType.Click, _resourceID );
        }

        @Override
        public boolean onLongClick(View v) {
            Logger.addEvent(EventType.LongClick, _resourceID );
            return false;
        }
    }

    @Override
    public String toString() {
        StringBuffer buffer = new StringBuffer();
        buffer.append( _name )
                .append( "\n" )
                .append( _task )
                .append( "\n" );
        return buffer.toString();
    }

    public TaskType getTask() { return _task; }

    private String _name;
    private TaskType _task;
    private boolean _onRecord;
}