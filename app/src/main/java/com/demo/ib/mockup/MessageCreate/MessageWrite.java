package com.demo.ib.mockup.MessageCreate;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.ListView;

import com.demo.ib.mockup.MessageList.MessageListViewAdapter;
import com.demo.ib.mockup.R;
import com.demo.ib.mockup.Register.enums.EventType;
import com.demo.ib.mockup.Register.enums.RegisterType;

import Core.UserProfile;

/**
 * Created by nulledge on 2016-09-29.
 */
public class MessageWrite extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature( Window.FEATURE_NO_TITLE );

        setContentView( R.layout.activity_message_create );

        UserProfile.GetInstance().registerEventLogger( this, R.id.messageCreateTextNewMessage, RegisterType.Both );
        UserProfile.GetInstance().registerEventLogger( this, R.id.messageCreateTextTo, RegisterType.Both );
        UserProfile.GetInstance().registerEventLogger( this, R.id.messageCreateImageCamera, RegisterType.Both );
        UserProfile.GetInstance().registerEventLogger( this, R.id.messageCreateSendText, RegisterType.Both );
        UserProfile.GetInstance().registerEventLogger( this, R.id.messageCreateSendImage, RegisterType.Both );

        findViewById( R.id.messageCreateButtonCancel ).setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        UserProfile.GetInstance().addEvent(EventType.Click, R.id.messageCreateButtonCancel );
                        finish();
                    }
                }
        );
        findViewById( R.id.messageCreateButtonCancel ).setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                UserProfile.GetInstance().addEvent(EventType.LongClick, R.id.messageCreateButtonCancel );
                return false;
            }
        });
        findViewById( R.id.messageCreateSendImage ).setVisibility( View.VISIBLE );
        findViewById( R.id.messageCreateSendText ).setVisibility( View.GONE);
        ((EditText)findViewById( R.id.messageCreateMessage )).addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                boolean activate = s.length() == 0;
                if( activate ) {
                    findViewById( R.id.messageCreateSendImage ).setVisibility( View.VISIBLE );
                    findViewById( R.id.messageCreateSendText ).setVisibility( View.GONE);
                }
                else {
                    findViewById( R.id.messageCreateSendImage ).setVisibility( View.GONE );
                    findViewById( R.id.messageCreateSendText ).setVisibility( View.VISIBLE );
                }
            }
        });

        UserProfile.GetInstance().registerEventLogger( this, R.id.messageCreateReceiver, RegisterType.Both );
        UserProfile.GetInstance().registerEventLogger( this, R.id.messageCreateMessage, RegisterType.Both );
        findViewById(R.id.messageCreateReceiver).setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if( hasFocus )
                    UserProfile.GetInstance().addEvent( EventType.Click, R.id.messageCreateReceiver );
            }
        });
        findViewById(R.id.messageCreateMessage).setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if( hasFocus )
                    UserProfile.GetInstance().addEvent( EventType.Click, R.id.messageCreateMessage );
            }
        });
    }

    @Override
    public void onBackPressed() {
        UserProfile.GetInstance().addEvent( EventType.Click, "messageCreateSystemBackButton" );
        finish();
    }

}
