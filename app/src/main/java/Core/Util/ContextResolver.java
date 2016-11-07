package Core.Util;

import android.app.Activity;
import android.content.Context;

/**
 * Created by nulledge on 2016-11-08.
 */
public class ContextResolver {

    static public ContextResolver getInstance() {
        if( _instance == null )
            _instance = new ContextResolver();
        return _instance;
    }
    static private ContextResolver _instance;

    static public boolean Init( Activity activity) {
        if( getInstance()._context != null )
            return Values.FAIL;
        getInstance()._activity = activity;
        getInstance()._context = activity.getApplicationContext();
        return Values.SUCCESS;
    }

    static public Context getApplicationContext() {
        return getInstance()._context;
    }
    static public Activity getActivity() {
        return getInstance()._activity;
    }

    private ContextResolver() {
        _context = null;
        _activity = null;
    }

    private Context _context;
    private Activity _activity;
}