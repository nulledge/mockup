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
import android.support.v4.content.ContextCompat;
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

    public static final String DIRECTORY = Environment.DIRECTORY_DOCUMENTS;
    public static final String FILE = "log.txt";

    private static final int READ_WRITE_EXTERNAL_DIR_PERMISSION_REQUEST = 105;

    private static int _permissionGranted = PackageManager.PERMISSION_DENIED;
    public static int PermissionGranted() { return _permissionGranted; }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature( Window.FEATURE_NO_TITLE );
        setContentView( R.layout.activity_register );

        UserProfile._activity = this;
        UserProfile.GetInstance().start();

        guaranteePermission();

        try{
            File dir = Environment.getExternalStoragePublicDirectory( DIRECTORY );
            if( dir.exists() == false )
                dir.mkdirs();

            File file = new File( dir, FILE );
        }
        catch(Exception e){
            Toast.makeText( this, e.toString(), Toast.LENGTH_LONG ).show();
        }
        UserProfile.GetInstance().stop();

        findViewById( R.id.registerButtonRegister ).setOnClickListener(
                new RegisterOnClickListener() );
        findViewById(R.id.registerButtonLog).setOnClickListener(
                new LogOnClickListener() );
    }


    @Override
    public void onBackPressed() {
        UserProfile.GetInstance().addEvent( EventType.Click, "registerSystemBackButton" );
    }

    static public void guaranteePermission() {
        int permissionWrite = ContextCompat.checkSelfPermission(
                UserProfile._activity, Manifest.permission.WRITE_EXTERNAL_STORAGE );
        int permissionRead = ContextCompat.checkSelfPermission(
                UserProfile._activity, Manifest.permission.WRITE_EXTERNAL_STORAGE );

        if( permissionWrite == PackageManager.PERMISSION_GRANTED
                && permissionRead == PackageManager.PERMISSION_GRANTED ) {
            _permissionGranted = PackageManager.PERMISSION_GRANTED;
            return;
        }

        ActivityCompat.requestPermissions( UserProfile._activity,
                new String[]{
                        Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE },
                READ_WRITE_EXTERNAL_DIR_PERMISSION_REQUEST
        );
    }

    @Override
    public void onRequestPermissionsResult( int requestCode,
                                            String[] permissions, int[] grantResults ) {
        switch( requestCode ) {
            case READ_WRITE_EXTERNAL_DIR_PERMISSION_REQUEST:
                if( grantResults.length == 0 )
                    return;
                _permissionGranted = PackageManager.PERMISSION_GRANTED;
                break;

            default:
                break;
        }

    }
}
