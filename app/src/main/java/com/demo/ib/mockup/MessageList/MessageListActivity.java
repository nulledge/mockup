package com.demo.ib.mockup.MessageList;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.demo.ib.mockup.MessageCreate.MessageWrite;
import com.demo.ib.mockup.MessageDetail.MessageDetailActivity;
import com.demo.ib.mockup.R;
import com.demo.ib.mockup.Register.enums.EventType;
import com.demo.ib.mockup.Register.enums.RegisterType;
import com.demo.ib.mockup.Register.enums.TaskType;

import java.util.HashMap;

import Core.DummyMessage;
import Core.MessageData;
import Core.Info.UserProfile;
import Core.Util.Logger;

/**
 * Created by nulledge on 2016-09-29.
 */
public class MessageListActivity extends Activity {

    private void refreshListView() {
        if( _deleteTarget != null ) {
            _deleteTarget._visible = false;
            ((MessageListViewAdapter)((ListView)findViewById(R.id.messageListListView)).getAdapter()).notifyDataSetChanged();
        }
        _deleteTarget = null;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature( Window.FEATURE_NO_TITLE );
        setContentView( R.layout.activity_message_list );

        _lock = new HashMap<Integer, Boolean>();

        MessageListViewAdapter adapter = new MessageListViewAdapter();
        ListView listView = (ListView)findViewById(R.id.messageListListView);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new CustomOnItemClickListener());
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Logger.addEvent( EventType.LongClick, R.id.messageListListView );
                refreshListView();
                return false;
            }
        });
        _swipeDetector = new SwipeDetector();
        listView.setOnTouchListener(_swipeDetector);
        listView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                refreshListView();
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

            }
        });

        findViewById( R.id.messageListButtonCreateMessage ).setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Logger.addEvent( EventType.Click, R.id.messageListButtonCreateMessage );
                        refreshListView();
                        Intent messageWriteActivity = new Intent(v.getContext(), MessageWrite.class);
                        startActivity( messageWriteActivity );
                    }
                }
        );
        findViewById( R.id.messageListButtonCreateMessage ).setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Logger.addEvent( EventType.LongClick, R.id.messageListButtonCreateMessage );
                refreshListView();
                return false;
            }
        });

        for( MessageData messageData : DummyMessage.getInstance()._messages ) {
            adapter.addItem( messageData );
        }

        findViewById(R.id.messageListTextEdit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Logger.addEvent( EventType.Click, R.id.messageListTextEdit );
                refreshListView();
            }
        });
        findViewById(R.id.messageListTextMessage).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Logger.addEvent(EventType.Click, R.id.messageListTextMessage );
                refreshListView();
            }
        });
        findViewById(R.id.messageListTextEdit).setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Logger.addEvent( EventType.LongClick, R.id.messageListTextEdit );
                refreshListView();
                return true;
            }
        });
        findViewById(R.id.messageListTextMessage).setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Logger.addEvent( EventType.LongClick, R.id.messageListTextMessage );
                refreshListView();
                return true;
            }
        });

        //UserProfile.GetInstance().registerEventLogger( this, R.id.messageListTextEdit, RegisterType.Both );
        //UserProfile.GetInstance().registerEventLogger( this, R.id.messageListTextMessage, RegisterType.Both );

        if( UserProfile.GetInstance().getTask() == TaskType.Task2 ) {
            ImageView buttonCreateMessage = (ImageView) findViewById(R.id.messageListButtonCreateMessage);
            ViewGroup.LayoutParams params = buttonCreateMessage.getLayoutParams();
            params.width = params.width / 5 * 3;
            params.height = params.height / 5 * 4;
            buttonCreateMessage.setLayoutParams( params );
        }

        UserProfile.GetInstance().registerEventLogger( this, R.id.messageListSearch, RegisterType.Both );
        ((EditText)findViewById(R.id.messageListSearch)).setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                EditText search = (EditText)findViewById(R.id.messageListSearch);
                ImageView icon = (ImageView)findViewById(R.id.messageListSearchIcon);
                RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams)icon.getLayoutParams();
                if( hasFocus ) {
                    Logger.addEvent( EventType.Click, R.id.messageListSearch );
                    search.setGravity(Gravity.LEFT );
                    params.addRule( RelativeLayout.CENTER_HORIZONTAL, 0 );
                    icon.setLayoutParams( params );
                }
                else {
                    if( search.getText().length() == 0 ) {
                        search.setGravity(Gravity.CENTER );
                        params.addRule( RelativeLayout.CENTER_HORIZONTAL );
                        icon.setLayoutParams( params );
                    }
                }
                refreshListView();
            }
        });

        ((EditText)findViewById(R.id.messageListSearch)).addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                EditText search = ((EditText)findViewById(R.id.messageListSearch));
                ImageView icon = (ImageView)findViewById(R.id.messageListSearchIcon);
                RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams)icon.getLayoutParams();
                if( s.length() != 0 ) {
                    search.setGravity(Gravity.LEFT );
                    params.addRule( RelativeLayout.CENTER_HORIZONTAL, 0 );
                    icon.setLayoutParams( params );
                }
            }
        });

        LinearLayout touchInterceptor = (LinearLayout)findViewById(R.id.messageListRoot);
        touchInterceptor.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    EditText search = (EditText)findViewById(R.id.messageListSearch);
                    if (search.isFocused()) {
                        Rect outRect = new Rect();
                        search.getGlobalVisibleRect(outRect);
                        if (!outRect.contains((int)event.getRawX(), (int)event.getRawY())) {
                            search.clearFocus();
                            InputMethodManager imm = (InputMethodManager) v.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                            imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                        }
                    }
                }
                refreshListView();
                return false;
            }
        });

    }

    static SwipeDetector _swipeDetector;
    static HashMap<Integer, Boolean> _lock;
    static enum Action {
        LR, // Left to right
        RL, // Right to left
        TB, // Top to bottom
        BT, // Bottom to top
        None // Action not found
    }

    class SwipeDetector implements View.OnTouchListener {

        private static final int HORIZONTAL_MIN_DISTANCE = 30; // The minimum
        // distance for
        // horizontal swipe
        private static final int VERTICAL_MIN_DISTANCE = 80; // The minimum distance
        // for vertical
        // swipe
        private float downX, downY, upX, upY; // Coordinates
        private Action mSwipeDetected = Action.None; // Last action

        public boolean swipeDetected() {
            return mSwipeDetected != Action.None;
        }

        public Action getAction() {
            return mSwipeDetected;
        }

        /**
         * Swipe detection
         */@Override
        public boolean onTouch(View v, MotionEvent event) {
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                {
                    downX = event.getX();
                    downY = event.getY();
                    mSwipeDetected = Action.None;
                    return false; // allow other events like Click to be processed
                }
                case MotionEvent.ACTION_MOVE:
                {
                    upX = event.getX();
                    upY = event.getY();

                    float deltaX = downX - upX;
                    float deltaY = downY - upY;

                    // horizontal swipe detection
                    if (Math.abs(deltaX) > HORIZONTAL_MIN_DISTANCE) {
                        // left or right
/*                        if (deltaX < 0) {

                            mSwipeDetected = Action.LR;
                            return true;
                        }*/
                        if (deltaX > 100) {

                            mSwipeDetected = Action.RL;
                            refreshListView();
                            return true;
                        }
                    }/* else

                        // vertical swipe detection
*                       if (Math.abs(deltaY) > VERTICAL_MIN_DISTANCE) {
                            // top or down
                            if (deltaY < 0) {

                                mSwipeDetected = Action.TB;
                                refreshListView();
                                return false;
                            }
                            if (deltaY > 0) {

                                mSwipeDetected = Action.BT;
                                refreshListView();
                                return false;
                            }
                        }
                    return true;*/
                }
            }
            return false;
        }

    }

    static MessageListItem _deleteTarget = null;

    class CustomOnItemClickListener implements AdapterView.OnItemClickListener {

        boolean _run = false;

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            if (_swipeDetector.swipeDetected()) {
                if (_swipeDetector.getAction() == Action.RL) {
                    Logger.addEvent( EventType.Slide, R.id.messageListListView );

                    ListView listView = (ListView)findViewById(R.id.messageListListView);
                    MessageListViewAdapter adapter = (MessageListViewAdapter)listView.getAdapter();
                    MessageListItem item = (MessageListItem)adapter.getItem( position );
                    item._visible = true;
                    adapter.getView( position, view, listView );

                    if( _deleteTarget != null ) {
                        _deleteTarget._visible = false;
                        adapter.notifyDataSetInvalidated();
                    }
                    _deleteTarget = item;

                    return;
                }
            }

            Logger.addEvent( EventType.Click, R.id.messageListListView );
            if( UserProfile.GetInstance().getTask() == TaskType.Task3 ) {
                if( _lock.containsKey( position ) == false ) {
                    _lock.put( position, Boolean.TRUE );
                    new Handler().postDelayed(new CustomRunnable(view, position), 3000);
                }
            }
            else {
                new CustomRunnable(view, position).run();
            }
        }

        class CustomRunnable implements Runnable {

            CustomRunnable(View view, int index) {
                _view = view;
                _index = index;
            }

            View _view;
            int _index;

            @Override
            public void run() {
                _lock.remove( _index );
                refreshListView();
                Intent messageDetailActivity = new Intent(_view.getContext(), MessageDetailActivity.class);
                messageDetailActivity.putExtra( "index", _index );
                startActivity( messageDetailActivity );
            }
        }
    }

    @Override
    public void onBackPressed() {
        Logger.addEvent( EventType.Click, "messageListSystemBackButton" );
        finish();
    }
}