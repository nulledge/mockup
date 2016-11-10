package Core.Info;

import android.app.Activity;
import android.view.View;

import Core.enums.EventType;
import Core.enums.RegisterType;
import Core.enums.TaskType;

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
        _onRecord = true;
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

    static public String getFileName() {
        StringBuffer buffer = new StringBuffer();
        buffer.append( getInstance()._name )
                .append( ".txt" );
        return buffer.toString();
    }

    public TaskType getTask() { return _task; }

    private String _name;
    private TaskType _task;
    private boolean _onRecord;
}