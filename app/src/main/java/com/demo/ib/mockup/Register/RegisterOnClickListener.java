package com.demo.ib.mockup.Register;

import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;

import com.demo.ib.mockup.MessageList.MessageListActivity;
import com.demo.ib.mockup.R;
import com.demo.ib.mockup.Register.enums.EventType;
import com.demo.ib.mockup.Register.enums.TaskType;

import Core.UserProfile;

/**
 * Created by nulledge on 2016-11-06.
 */
public class RegisterOnClickListener implements View.OnClickListener {
    @Override
    public void onClick(View v) {
        UserProfile userProfile = UserProfile.GetInstance();

        String name = ((EditText)UserProfile._activity.findViewById( R.id.registerTextViewID )).getText().toString();
        int buttonId = ((RadioGroup)UserProfile._activity.findViewById( R.id.registerTaskGroup )).getCheckedRadioButtonId();
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

        userProfile.setProfile( name, taskType );
        userProfile.start();
        if( name.equals( "" ) )
            name = "None";
        userProfile.addEvent(EventType.Register, name );
        userProfile.addEvent(EventType.Register, buttonId );

        Intent messageListActivity = new Intent(v.getContext(), MessageListActivity.class);
        UserProfile._activity.startActivity( messageListActivity );
    }
}
