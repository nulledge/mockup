package com.demo.ib.mockup.Register;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.audiofx.EnvironmentalReverb;
import android.net.Uri;
import android.os.Environment;
import android.os.Process;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.demo.ib.mockup.MessageList.MessageListActivity;
import com.demo.ib.mockup.R;
import com.demo.ib.mockup.Register.enums.EventType;
import com.demo.ib.mockup.Register.enums.TaskType;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URI;
import java.text.SimpleDateFormat;
import java.util.Date;

import Core.Message;
import Core.MessageData;
import Core.UserProfile;

public class RegisterActivity extends AppCompatActivity {

    String DIRECTORY = "usabilityTest";
    String FILE = "log.txt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature( Window.FEATURE_NO_TITLE );
        setContentView( R.layout.activity_register );

        UserProfile._activity = this;
        UserProfile.GetInstance().start();
        SimpleDateFormat format = new SimpleDateFormat( "yyyy/MM/dd hh:mm:ss" );
        StringBuffer output = new StringBuffer();
        try{
            int permission = ActivityCompat.checkSelfPermission(UserProfile._activity, Manifest.permission.WRITE_EXTERNAL_STORAGE);
            if (permission != PackageManager.PERMISSION_GRANTED) {
                String[] permissions = { Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE };
                // We don't have permission so prompt the user
                ActivityCompat.requestPermissions(
                        UserProfile._activity,
                        permissions,
                        1
                );
            }

            File dir = Environment.getExternalStoragePublicDirectory( Environment.DIRECTORY_DOWNLOADS );
            if( dir.exists() == false )
                dir.mkdirs();

            File file = new File( dir, "log.txt" );
/*
            FileInputStream fis = new FileInputStream( file );
//            FileInputStream fis = openFileInput( "log.txt" );
            BufferedReader buffer = new BufferedReader( new InputStreamReader(fis) );
            String str = buffer.readLine();
            while( str != null ) {
                output.append( str + "\n" );
                String[] strs = str.split( ", ");
                if( strs.length != 3 ) {
                    throw new Exception("Wrong format! Can't load old log!");
                }
                Date date = format.parse( strs[0] );
                EventType eventType;
                if( strs[1].contains( "Register" ) ) {
                    eventType = EventType.Register;
                }
                else if( strs[1].contains( "Click") ) {
                    eventType = EventType.Click;
                }
                else if( strs[1].contains( "LongClick" ) ) {
                    eventType = EventType.LongClick;
                }
                else if( strs[1].contains( "Slide") ) {
                    eventType = EventType.Slide;
                }
                else {
                    throw new Exception( "Wrong format! Can't load old log!" );
                }
                if( strs[2].equals( "" ) )
                    strs[2] = "None";
                UserProfile.GetInstance().addEvent( date, eventType, strs[2] );
                str = buffer.readLine();
            }
            buffer.close();*/
        }
        catch(Exception e){
            Toast.makeText( this, e.toString(), Toast.LENGTH_LONG ).show();
        }
        UserProfile.GetInstance().stop();

/*        findViewById( R.id.registerButtonReset ).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteFile( "log.txt" );
            }
        });*/

        findViewById( R.id.registerButtonRegister ).setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        UserProfile userProfile = UserProfile.GetInstance();

                        String name = ((EditText)findViewById( R.id.registerTextViewID )).getText().toString();
                        int buttonId = ((RadioGroup)findViewById( R.id.registerTaskGroup )).getCheckedRadioButtonId();
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
                        startActivity( messageListActivity );
                    }
                }
        );
        findViewById(R.id.registerButtonLog).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String fileName = "log.txt";
                FileOutputStream fos;

                try {

                    int permission = ActivityCompat.checkSelfPermission(UserProfile._activity, Manifest.permission.WRITE_EXTERNAL_STORAGE);

                    if (permission != PackageManager.PERMISSION_GRANTED) {
                        String[] permissions = { Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE };
                        // We don't have permission so prompt the user
                        ActivityCompat.requestPermissions(
                                UserProfile._activity,
                                permissions,
                                1
                        );
                    }


                    File dir = Environment.getExternalStoragePublicDirectory( Environment.DIRECTORY_DOWNLOADS);
                    if( dir.exists() == false )
                        dir.mkdirs();

                    File file = new File( dir, "log.txt" );

                    FileWriter fileWriter = new FileWriter( file );
                    fileWriter.append( UserProfile.GetInstance().toString() );
                    fileWriter.flush();
                    fileWriter.close();

                    /*fos = openFileOutput( fileName, Context.MODE_PRIVATE );
                    fos.write( UserProfile.GetInstance().toString().getBytes() );
                    fos.close();*/

/*                    String shareBody = UserProfile.GetInstance().toString();
                    Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
                    sharingIntent.setType("text/plain");
                    sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "log.txt");
                    sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
                    startActivity(Intent.createChooser(sharingIntent, ""));*/

                    /*File toSend = new File( getFilesDir() + "/" + fileName );
                    Intent intent = new Intent();
                    intent.setAction( Intent.ACTION_VIEW );
                    Uri uri = FileProvider.getUriForFile( UserProfile.GetInstance()._activity, getApplicationContext().getPackageName() + ".provider", toSend );
                    intent.setDataAndType( uri, "text/plain" );
                    startActivity( intent );*/


                    Toast.makeText( UserProfile.GetInstance()._activity, "Save Success!", Toast.LENGTH_SHORT ).show();
                }
                catch(Exception e) {
                    Toast.makeText( UserProfile.GetInstance()._activity, e.toString(), Toast.LENGTH_SHORT ).show();
                }

                /*  Save to SD card
                String dir = Environment.getExternalStorageDirectory().getAbsolutePath();
                String fileName = dir + "/log.txt";

                try {
                    FileOutputStream fos = new FileOutputStream( fileName );
                    fos.write( UserProfile.GetInstance().toString().getBytes() );
                    fos.close();

                    Toast.makeText( UserProfile.GetInstance()._activity, "Save to: " + getFilesDir() + "/" + FILE, Toast.LENGTH_SHORT ).show();
                }
                catch(Exception e) {
                    Toast.makeText( UserProfile.GetInstance()._activity, e.toString(), Toast.LENGTH_SHORT ).show();
                }*/
            }
        });
    }


    @Override
    public void onBackPressed() {
        UserProfile.GetInstance().addEvent( EventType.Click, "registerSystemBackButton" );
    }
}
