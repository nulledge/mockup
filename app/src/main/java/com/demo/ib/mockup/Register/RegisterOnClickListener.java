package com.demo.ib.mockup.Register;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;

import com.demo.ib.mockup.MessageList.MessageListActivity;
import com.demo.ib.mockup.R;
import com.demo.ib.mockup.Register.enums.EventType;
import com.demo.ib.mockup.Register.enums.TaskType;

import Core.Info.UserProfile;
import Core.Util.ContextResolver;
import Core.Util.Logger;

/**
 * Created by nulledge on 2016-11-06.
 */
public class RegisterOnClickListener implements View.OnClickListener {

    public RegisterOnClickListener( Activity activity ) {
        super();
        _activity = activity;
    }

    @Override
    public void onClick(View v) {
        String name = ((EditText) _activity.findViewById( R.id.registerTextViewID ))
                .getText().toString();
        int buttonId = ((RadioGroup) _activity.findViewById( R.id.registerTaskGroup ))
                .getCheckedRadioButtonId();

        TaskType taskType = TaskType.Task1;

        switch ( buttonId ) {
            case R.id.registerTask1:
                taskType = TaskType.Task1;
                break;
            case R.id.registerTask2:
                taskType = TaskType.Task2;
                break;
            case R.id.registerTask3:
                taskType = TaskType.Task3;
                break;
        }

        UserProfile.setProfile( name, taskType );
        if( name.equals( "" ) )
            name = "None";
        Logger.addEvent( EventType.Register, name );
        Logger.addEvent( EventType.Register, buttonId );

        Intent messageListActivity = new Intent(
                _activity.getApplicationContext(),
                MessageListActivity.class );
        _activity.startActivity( messageListActivity );
    }

    private Activity _activity;
}
