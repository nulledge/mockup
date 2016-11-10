package com.demo.ib.mockup.MessageCreate;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.EditText;

import com.demo.ib.mockup.R;

import Core.Util.Save;
import Core.enums.EventType;
import Core.enums.RegisterType;

import Core.Info.UserProfile;
import Core.Util.Logger;

/**
 * Created by nulledge on 2016-09-29.
 */
public class MessageWrite extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature( Window.FEATURE_NO_TITLE );

        setContentView( R.layout.activity_message_create );

        UserProfile.getInstance().registerEventLogger( this, R.id.messageCreateTextNewMessage, RegisterType.Both );
        UserProfile.getInstance().registerEventLogger( this, R.id.messageCreateTextTo, RegisterType.Both );
        UserProfile.getInstance().registerEventLogger( this, R.id.messageCreateImageCamera, RegisterType.Both );
        UserProfile.getInstance().registerEventLogger( this, R.id.messageCreateSendText, RegisterType.Both );
        UserProfile.getInstance().registerEventLogger( this, R.id.messageCreateSendImage, RegisterType.Both );

        findViewById( R.id.messageCreateButtonCancel ).setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Logger.addEvent(EventType.Click, R.id.messageCreateButtonCancel );
                        finish();
                    }
                }
        );
        findViewById( R.id.messageCreateButtonCancel ).setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Logger.addEvent(EventType.LongClick, R.id.messageCreateButtonCancel );
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

        UserProfile.getInstance().registerEventLogger( this, R.id.messageCreateReceiver, RegisterType.Both );
        UserProfile.getInstance().registerEventLogger( this, R.id.messageCreateMessage, RegisterType.Both );
        findViewById(R.id.messageCreateReceiver).setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if( hasFocus )
                    Logger.addEvent( EventType.Click, R.id.messageCreateReceiver );
            }
        });
        findViewById(R.id.messageCreateMessage).setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if( hasFocus )
                    Logger.addEvent( EventType.Click, R.id.messageCreateMessage );
            }
        });
    }

    @Override
    public void onBackPressed() {
        Logger.addEvent( EventType.Click, "messageCreateSystemBackButton" );
        finish();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.system_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.menuSaveLog:
                Save.SaveLog( this );
                return true;
            case R.id.menuSurvey:
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
