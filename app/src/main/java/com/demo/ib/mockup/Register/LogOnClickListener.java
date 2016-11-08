package com.demo.ib.mockup.Register;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Environment;
import android.view.View;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;

import Core.Info.UserProfile;
import Core.Util.ContextResolver;

/**
 * Created by nulledge on 2016-11-06.
 */
public class LogOnClickListener implements View.OnClickListener {

    public LogOnClickListener( RegisterActivity activity ) {
        super();
        _activity = activity;
    }

    @Override
    public void onClick(View v) {
        _activity.grantPermissions();
        if( _activity.PermissionGranted() == PackageManager.PERMISSION_DENIED )
            return;

        FileOutputStream fos;
        try {
            File dir = Environment.getExternalStoragePublicDirectory( RegisterActivity.DIRECTORY );
            if( dir.exists() == false )
                dir.mkdirs();

            File file = new File( dir, RegisterActivity.FILE );

            FileWriter fileWriter = new FileWriter( file );
            fileWriter.append( UserProfile.getInstance().toString() );
            fileWriter.flush();
            fileWriter.close();

            Toast.makeText( _activity.getApplicationContext(), "Save Success!", Toast.LENGTH_SHORT ).show();
        }
        catch(Exception e) {
            Toast.makeText( _activity.getApplicationContext(), e.toString(), Toast.LENGTH_SHORT ).show();
        }
    }

    private RegisterActivity _activity;
}
