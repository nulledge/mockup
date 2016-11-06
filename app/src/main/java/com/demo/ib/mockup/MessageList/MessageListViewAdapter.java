package com.demo.ib.mockup.MessageList;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.demo.ib.mockup.R;
import com.demo.ib.mockup.Register.enums.EventType;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import Core.DummyMessage;
import Core.Message;
import Core.MessageData;
import Core.UserProfile;

/**
 * Created by nulledge on 2016-09-29.
 */
public class MessageListViewAdapter extends BaseAdapter {

    private ArrayList<MessageListItem> _messages = new ArrayList<MessageListItem>();

    public MessageListViewAdapter() {
        _lastClicked = Calendar.getInstance().getTime();
    }

    @Override
    public int getCount() {
        return _messages.size();
    }

    @Override
    public Object getItem(int position) {
        return _messages.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent ) {
        final int pos = position;
        final Context context = parent.getContext();

        if( convertView == null ) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate( R.layout.fragment_message_list_item, parent, false);
        }

        TextView receiver = (TextView) convertView.findViewById(R.id.messageListItemReceiver) ;
        TextView date = (TextView) convertView.findViewById(R.id.messageListItemDate) ;
        TextView text = (TextView) convertView.findViewById(R.id.messageListItemText) ;

        final MessageListItem listViewItem = _messages.get(position);

        int size = listViewItem._messageData.getSize();
        Message last = listViewItem._messageData.getMessage( size - 1 );

        receiver.setText( listViewItem._messageData.getReceiver() );
        SimpleDateFormat format = new SimpleDateFormat( "yyyy. MM. dd." );
        date.setText( format.format(last.getTime()) );
        text.setText( last.getMessage() );

        final ImageView deleteIcon = (ImageView)convertView.findViewById(R.id.messageListDelete);
        if( listViewItem._visible == false )
            deleteIcon.setVisibility(View.GONE);
        else {
            deleteIcon.setVisibility(View.VISIBLE);
            listViewItem._visible = false;
        }
        deleteIcon.setOnClickListener(new CustomTouchListener(listViewItem, convertView));

        return convertView;
    }

    static Date _lastClicked;

    class CustomTouchListener implements View.OnClickListener {

        CustomTouchListener( MessageListItem item, View view ) {
            _item = item;
            _view = view;
        }

        private MessageListItem _item;
        private View _view;

        @Override
        public void onClick(View v) {
            UserProfile.GetInstance().addEvent( EventType.Click, R.id.messageListDelete );
            _item._visible = false;
            _view.findViewById(R.id.messageListDelete).setVisibility( View.GONE );
            for( int i = 0; i < DummyMessage.getInstance()._messages.size(); i ++ ) {
                if( DummyMessage.getInstance()._messages.get( i ).getReceiver().equals( _item._messageData.getReceiver() ) == false )
                    continue;
                DummyMessage.getInstance()._messages.remove( i );
                break;
            }
            _messages.remove( _item );
            notifyDataSetInvalidated();
            return;
        }
    }

    public void addItem( MessageData messageData ) {
        MessageListItem item = new MessageListItem();
        item._messageData = messageData;
        item._visible = false;
        _messages.add(item);
    }
}
