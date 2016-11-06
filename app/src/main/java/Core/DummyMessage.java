package Core;

import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 * Created by nulledge on 2016-10-06.
 */
public class DummyMessage {
    public static DummyMessage getInstance() {
        if( _instance == null )
            _instance = new DummyMessage();
        return _instance;
    }
    private static DummyMessage _instance;

    private DummyMessage() {
        _messages = new ArrayList<MessageData>();

        refresh();
    }

    public void refresh() {
        _messages.clear();
        MakeDummyData1();
        MakeDummyData2();
        MakeDummyData3();
        MakeDummyData4();
        MakeDummyData5();
        MakeDummyData6();
        MakeDummyData7();
        MakeDummyData8();
        MakeDummyData9();
        MakeDummyData10();
        MakeDummyData11();
        MakeDummyData12();
    }

    public ArrayList<MessageData> _messages;
    public int _selected;

    private void MakeDummyData1() {
        MessageData dummy1 = new MessageData( "Kim" );
        SimpleDateFormat format = new SimpleDateFormat( "yyyy-MM-dd hh:mm" );
        try {
            dummy1.addMessage( format.parse("2016-10-05 16:06"), "Out of every movie that you've seen, which one is your favorite?", false );
            dummy1.addMessage( format.parse("2016-10-05 18:15"), "I'm going to have to say that Superbad is the best movie ever.", true );
            dummy1.addMessage( format.parse("2016-10-05 18:17"), "You think so, how come?", false );
            dummy1.addMessage( format.parse("2016-10-05 18:20"), "Well, Superbad is super funny.", true );
            dummy1.addMessage( format.parse("2016-10-05 18:21"), "You're not lying, I found that movie absolutely hilarious.", false );
            dummy1.addMessage( format.parse("2016-10-05 18:30"), "I didn't know that you saw Superbad before..", true );
            dummy1.addMessage( format.parse("2016-10-05 19:16"), "I made sure to be in line to see it the first day it came out.", false );
            dummy1.addMessage( format.parse("2016-10-05 19:18"), "I couldn't keep from laughing throughout the whole movie.", true );
            dummy1.addMessage( format.parse("2016-10-05 19:25"), "I was laughing hysterically the whole time; my stomach muscles hurt afterwards.", false );
            dummy1.addMessage( format.parse("2016-10-05 19:35"), "That's exactly how I felt.", true );
            dummy1.addMessage( format.parse("2016-10-05 19:40"), "I got the movie when it came out on DVD, do you want to come over?", false );
            dummy1.addMessage( format.parse("2016-10-05 19:41"), "I would love to.", true );
        }
        catch( Exception e ) {
        }
        _messages.add( dummy1 );
    }

    private void MakeDummyData2() {
        MessageData dummy = new MessageData( "Jeong" );
        SimpleDateFormat format = new SimpleDateFormat( "yyyy-MM-dd hh:mm" );
        try {
            dummy.addMessage( format.parse("2016-08-09 00:00"), "Have you eat lunch?", false );
            dummy.addMessage( format.parse("2016-08-09 00:01"), "sleep?", false );
            dummy.addMessage( format.parse("2016-08-09 00:02"), "hey call me", false );
            dummy.addMessage( format.parse("2016-08-10 00:00"), "sorry, i lost my mobile", true);
            dummy.addMessage( format.parse("2016-08-11 00:00"), "lunch?", false );
            dummy.addMessage( format.parse("2016-08-11 00:01"), "yes. lotteria?", true );
            dummy.addMessage( format.parse("2016-08-11 00:02"), "NEVER", false );
            dummy.addMessage( format.parse("2016-08-11 00:03"), "KFC?", true );
            dummy.addMessage( format.parse("2016-08-11 00:04"), "ok. see you there", false );
            dummy.addMessage( format.parse("2016-08-15 00:00"), "McDonald?", false );
            dummy.addMessage( format.parse("2016-08-15 00:01"), "got it", true );
            dummy.addMessage( format.parse("2016-08-17 00:00"), "Mc?", false );
            dummy.addMessage( format.parse("2016-08-17 00:01"), "y", true );
            dummy.addMessage( format.parse("2016-09-20 00:00"), "i got pizza. come.", false );
            dummy.addMessage( format.parse("2016-09-20 00:01"), "okayyyyyy", true );
            dummy.addMessage( format.parse("2016-10-03 00:00"), "lunch?", false );
            dummy.addMessage( format.parse("2016-10-03 00:01"), "meet in the lab", true );
        }
        catch( Exception e ) {
        }
        _messages.add( dummy );
    }


    private void MakeDummyData3() {
        MessageData dummy = new MessageData( "1599-1111" );
        SimpleDateFormat format = new SimpleDateFormat( "yyyy-MM-dd hh:mm" );
        try {
            dummy.addMessage( format.parse("2016-10-02 15:55"), "[From Web]\n[KEB Hana Bank]Inbum, your account has been blocked.", false);
        }
        catch( Exception e ) {
        }
        _messages.add( dummy );
    }

    private void MakeDummyData4() {
        MessageData dummy = new MessageData( "Prof. Han" );
        SimpleDateFormat format = new SimpleDateFormat( "yyyy-MM-dd hh:mm" );
        try {
            dummy.addMessage( format.parse("2016-10-02 14:55"), "Professor. I send you an email. Please check it and reply.", true);
            dummy.addMessage( format.parse("2016-10-02 15:35"), "i see", false );
        }
        catch( Exception e ) {
        }
        _messages.add( dummy );
    }

    private void MakeDummyData5() {
        MessageData dummy = new MessageData( "Woo" );
        SimpleDateFormat format = new SimpleDateFormat( "yyyy-MM-dd hh:mm" );
        try {
            dummy.addMessage( format.parse("2016-10-01 12:17"), "Can I borrow Oculus Rift DK 2? I need it for my project.", false );
        }
        catch( Exception e ) {
        }
        _messages.add( dummy );
    }

    private void MakeDummyData6() {
        MessageData dummy = new MessageData( "Choi" );
        SimpleDateFormat format = new SimpleDateFormat( "yyyy-MM-dd hh:mm" );
        try {
            dummy.addMessage( format.parse("2016-09-21 12:17"), "did you wear my shirts? my favorite? the red one.", true);
        }
        catch( Exception e ) {
        }
        _messages.add( dummy );
    }

    private void MakeDummyData7() {
        MessageData dummy = new MessageData( "Roh" );
        SimpleDateFormat format = new SimpleDateFormat( "yyyy-MM-dd hh:mm" );
        try {
            dummy.addMessage( format.parse("2016-09-17 08:12"), "where are you? today we have meeting", false);
            dummy.addMessage( format.parse("2016-09-17 09:07"), "sorry, i overslept", true);
        }
        catch( Exception e ) {
        }
        _messages.add( dummy );
    }

    private void MakeDummyData8() {
        MessageData dummy = new MessageData( "Park" );
        SimpleDateFormat format = new SimpleDateFormat( "yyyy-MM-dd hh:mm" );
        try {
            dummy.addMessage( format.parse("2016-09-15 11:57"), "take your laptop. it's on my desk.", true);
        }
        catch( Exception e ) {
        }
        _messages.add( dummy );
    }

    private void MakeDummyData9() {
        MessageData dummy = new MessageData( "010-3494-3655" );
        SimpleDateFormat format = new SimpleDateFormat( "yyyy-MM-dd hh:mm" );
        try {
            dummy.addMessage( format.parse("2016-09-15 07:24"), "Karl. Did you change your phone number?", false );
            dummy.addMessage( format.parse("2016-09-15 08:24"), "it's not Karl. you call wrong number.", true );
            dummy.addMessage( format.parse("2016-09-15 08:26"), "sorry :-)", false );
        }
        catch( Exception e ) {
        }
        _messages.add( dummy );
    }

    private void MakeDummyData10() {
        MessageData dummy = new MessageData( "Baek" );
        SimpleDateFormat format = new SimpleDateFormat( "yyyy-MM-dd hh:mm" );
        try {
            dummy.addMessage( format.parse("2016-08-13 21:10"), "Do you interest in my laboratory?", false );
        }
        catch( Exception e ) {
        }
        _messages.add( dummy );
    }


    private void MakeDummyData11() {
        MessageData dummy = new MessageData( "Lim" );
        SimpleDateFormat format = new SimpleDateFormat( "yyyy-MM-dd hh:mm" );
        try {
            dummy.addMessage( format.parse("2016-08-12 17:57"), "Hello, World!", false );
            dummy.addMessage( format.parse("2016-08-12 17:59"), "you geek", true );
        }
        catch( Exception e ) {
        }
        _messages.add( dummy );
    }


    private void MakeDummyData12() {
        MessageData dummy = new MessageData( "Seo" );
        SimpleDateFormat format = new SimpleDateFormat( "yyyy-MM-dd hh:mm" );
        try {
            dummy.addMessage( format.parse("2016-07-15 07:24"), "Hi, Seo! It's me!", true );
            dummy.addMessage( format.parse("2016-07-16 21:07"), "...me who?", false );
        }
        catch( Exception e ) {
        }
        _messages.add( dummy );
    }
}
