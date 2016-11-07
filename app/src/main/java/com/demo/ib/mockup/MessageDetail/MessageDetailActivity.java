package com.demo.ib.mockup.MessageDetail;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.demo.ib.mockup.R;
import com.demo.ib.mockup.Register.enums.EventType;
import com.demo.ib.mockup.Register.enums.RegisterType;

import Core.DummyMessage;
import Core.MessageData;
import Core.Info.UserProfile;
import Core.Util.Logger;

/**
 * Created by nulledge on 2016-09-29.
 */
public class MessageDetailActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature( Window.FEATURE_NO_TITLE );
        setContentView( R.layout.activity_message_detail );

        findViewById(R.id.messageDetailBack).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Logger.addEvent(EventType.Click, R.id.messageDetailBack );
                finish();
            }
        });
        UserProfile.GetInstance().registerEventLogger( this, R.id.messageDetailBack, RegisterType.LongClick);

        int selected = getIntent().getIntExtra( "index", -1 );
        MessageData messageData = DummyMessage.getInstance()._messages.get( selected );
        UserProfile.GetInstance().registerEventLogger( this, R.id.messageDetailReceiver, RegisterType.Both);
        ((TextView)findViewById(R.id.messageDetailReceiver)).setText( messageData.getReceiver() );

        MessageDetailViewAdapter adapter = new MessageDetailViewAdapter();
        ListView listView = (ListView)findViewById(R.id.messageDetailListView);
        listView.setAdapter(adapter);

        UserProfile.GetInstance().registerEventLogger( this, R.id.messageDetailSendImage, RegisterType.Both);
        UserProfile.GetInstance().registerEventLogger( this, R.id.messageDetailSendText, RegisterType.Both);
        UserProfile.GetInstance().registerEventLogger( this, R.id.messageDetailImageCamera, RegisterType.Both);

        findViewById( R.id.messageDetailSendImage ).setVisibility( View.VISIBLE );
        findViewById( R.id.messageDetailSendText ).setVisibility( View.GONE);
        ((EditText)findViewById( R.id.messageDetailMessage )).addTextChangedListener(new TextWatcher() {
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
                    findViewById( R.id.messageDetailSendImage ).setVisibility( View.VISIBLE );
                    findViewById( R.id.messageDetailSendText ).setVisibility( View.GONE);
                }
                else {
                    findViewById( R.id.messageDetailSendImage ).setVisibility( View.GONE );
                    findViewById( R.id.messageDetailSendText ).setVisibility( View.VISIBLE );
                }
            }
        });
        UserProfile.GetInstance().registerEventLogger( this, R.id.messageDetailMessage, RegisterType.Both );
        findViewById(R.id.messageDetailMessage).setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if( hasFocus )
                    Logger.addEvent( EventType.Click, R.id.messageDetailMessage );
            }
        });

        for( int i = 0; i < messageData.getSize(); i ++ ) {
            adapter.addItem( messageData.getMessage( i ) );
        }
    }

    @Override
    public void onBackPressed() {
        Logger.addEvent( EventType.Click, "messageDetailSystemBackButton" );
        finish();
    }

}
