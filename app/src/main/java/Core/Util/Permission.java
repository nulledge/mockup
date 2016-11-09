package Core.Util;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

/**
 * Created by nulledge on 2016-11-09.
 */
public class Permission {

    static public final int PERMISSION_REQUEST = 100;
    static private final String[] REQUIRED_PERMISSIONS = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };

    static public int permissionGranted( Context context ) {
        for( String permission : REQUIRED_PERMISSIONS ) {
            int granted = ContextCompat.checkSelfPermission(
                    context,
                    permission );
            if( granted == PackageManager.PERMISSION_DENIED ) {
                return PackageManager.PERMISSION_DENIED;
            }
        }
        return PackageManager.PERMISSION_GRANTED;
    }

    static public void grantPermission( Activity activity ) {
        if( permissionGranted( activity.getApplicationContext() )
                == PackageManager.PERMISSION_GRANTED )
            return;

        ActivityCompat.requestPermissions(
                activity,
                REQUIRED_PERMISSIONS,
                PERMISSION_REQUEST
        );
    }
}
