package com.demo.ib.mockup.MessageDetail;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.demo.ib.mockup.R;
import com.demo.ib.mockup.Register.enums.EventType;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import Core.Message;
import Core.Info.UserProfile;
import Core.Util.Logger;

/**
 * Created by nulledge on 2016-09-29.
 */
public class MessageDetailViewAdapter extends BaseAdapter {

    private ArrayList<MessageDetailItem> _messages = new ArrayList<MessageDetailItem>();

    public MessageDetailViewAdapter() {

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

        MessageDetailItem listViewItem = _messages.get(position);

        //if( convertView == null ) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            if( listViewItem._message.getSend() )
                convertView = inflater.inflate( R.layout.fragment_message_detail_item_send, parent, false);
            else
                convertView = inflater.inflate( R.layout.fragment_message_detail_item, parent, false);
        //}

        TextView date = (TextView) convertView.findViewById(R.id.messageDetailItemDate);
        TextView text = (TextView) convertView.findViewById(R.id.messageDetailItemMessage);

        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Logger.addEvent(EventType.Click, R.id.messageDetailItemDate);
            }
        });
        date.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Logger.addEvent(EventType.LongClick, R.id.messageDetailItemDate);
                return true;
            }
        });
        text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Logger.addEvent(EventType.Click, R.id.messageDetailItemMessage);
            }
        });
        text.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Logger.addEvent(EventType.LongClick, R.id.messageDetailItemMessage);
                return true;
            }
        });


        date.setVisibility( View.VISIBLE );


        SimpleDateFormat format = new SimpleDateFormat( "yyyy. MM. dd." );
        date.setText( format.format(listViewItem._message.getTime()) );
        text.setText( listViewItem._message.getMessage() );

        if( position > 0 ) {
            Calendar cal1 = Calendar.getInstance();
            Calendar cal2 = Calendar.getInstance();
            cal1.setTime(listViewItem._message.getTime());
            cal2.setTime(_messages.get(position - 1)._message.getTime());
            boolean sameDay = cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR) &&
                    cal1.get(Calendar.DAY_OF_YEAR) == cal2.get(Calendar.DAY_OF_YEAR);
            if (sameDay)
                date.setVisibility(View.GONE);
        }

        return convertView;
    }

    public void addItem( Message message ) {
        MessageDetailItem item = new MessageDetailItem();
        item._message = message;
        _messages.add(item );
    }
}
