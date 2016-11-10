package Core.Util;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Environment;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;

import Core.Info.UserProfile;

/**
 * Created by nulledge on 2016-11-10.
 */
public class Save {
    static private final String DIRECTORY = Environment.DIRECTORY_DOCUMENTS;

    static public void SaveLog(Activity activity) {
        Permission.grantPermission( activity );
        if( Permission.permissionGranted( activity.getApplicationContext() )
                == PackageManager.PERMISSION_DENIED )
            return;

        FileOutputStream fos;
        try {
            File dir = Environment.getExternalStoragePublicDirectory( DIRECTORY );
            if( dir.exists() == false )
                dir.mkdirs();

            File file = new File( dir, UserProfile.getFileName() );

            FileWriter fileWriter = new FileWriter( file );
            fileWriter.append( UserProfile.getInstance().toString() )
                    .append( "\n" )
                    .append( Logger.publish() );
            fileWriter.flush();
            fileWriter.close();

            Toast.makeText( activity.getApplicationContext(), "Save Success!", Toast.LENGTH_SHORT ).show();
        }
        catch(Exception e) {
            Toast.makeText( activity.getApplicationContext(), e.toString(), Toast.LENGTH_SHORT ).show();
        }
    }

    class FileThread extends Thread implements Runnable {
        FileThread() {}

        @Override
        public void run() {

        }
    }
}
