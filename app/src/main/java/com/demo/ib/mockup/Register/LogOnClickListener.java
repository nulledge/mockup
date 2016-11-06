package com.demo.ib.mockup.Register;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.view.View;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;

import Core.UserProfile;

/**
 * Created by nulledge on 2016-11-06.
 */
public class LogOnClickListener implements View.OnClickListener {
    @Override
    public void onClick(View v) {
        RegisterActivity.guaranteePermission();
        if( RegisterActivity.PermissionGranted() == PackageManager.PERMISSION_DENIED )
            return;

        FileOutputStream fos;
        try {
            File dir = Environment.getExternalStoragePublicDirectory( RegisterActivity.DIRECTORY );
            if( dir.exists() == false )
                dir.mkdirs();

            File file = new File( dir, RegisterActivity.FILE );

            FileWriter fileWriter = new FileWriter( file );
            fileWriter.append( UserProfile.GetInstance().toString() );
            fileWriter.flush();
            fileWriter.close();

            Toast.makeText( UserProfile.GetInstance()._activity, "Save Success!", Toast.LENGTH_SHORT ).show();
        }
        catch(Exception e) {
            Toast.makeText( UserProfile.GetInstance()._activity, e.toString(), Toast.LENGTH_SHORT ).show();
        }
    }
}
