package com.demo.ib.mockup.Register;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.widget.Toast;

import com.demo.ib.mockup.R;
import com.demo.ib.mockup.Register.enums.EventType;

import java.io.File;

import Core.Util.ContextResolver;
import Core.Util.Logger;

public class RegisterActivity extends AppCompatActivity {

    public static final String DIRECTORY = Environment.DIRECTORY_DOCUMENTS;
    public static final String FILE = "log.txt";

    private static final int READ_WRITE_EXTERNAL_DIR_PERMISSION_REQUEST = 105;

    private int _permissionGranted = PackageManager.PERMISSION_DENIED;
    public int PermissionGranted() { return _permissionGranted; }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature( Window.FEATURE_NO_TITLE );
        setContentView( R.layout.activity_register );

        ContextResolver.Init( this );

        grantPermissions();

        try{
            File dir = Environment.getExternalStoragePublicDirectory( DIRECTORY );
            if( dir.exists() == false )
                dir.mkdirs();

            File file = new File( dir, FILE );
        }
        catch(Exception e){
            Toast.makeText( this, e.toString(), Toast.LENGTH_LONG ).show();
        }

        findViewById( R.id.registerButtonRegister ).setOnClickListener(
                new RegisterOnClickListener( this ) );
        findViewById(R.id.registerButtonLog).setOnClickListener(
                new LogOnClickListener( this ) );
    }


    @Override
    public void onBackPressed() {
        Logger.addEvent( EventType.Click, "registerSystemBackButton" );
    }

    public void grantPermissions() {
        int permissionWrite = ContextCompat.checkSelfPermission(
                getApplicationContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE );
        int permissionRead = ContextCompat.checkSelfPermission(
                getApplicationContext(), Manifest.permission.READ_EXTERNAL_STORAGE );

        if( permissionWrite == PackageManager.PERMISSION_GRANTED
                && permissionRead == PackageManager.PERMISSION_GRANTED ) {
            _permissionGranted = PackageManager.PERMISSION_GRANTED;
            return;
        }

        ActivityCompat.requestPermissions( this,
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
