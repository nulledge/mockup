package Core.Util;

import java.text.SimpleDateFormat;

/**
 * Created by nulledge on 2016-11-08.
 */
public class Values {
    static final boolean SUCCESS    = true;
    static final boolean FAIL       = false;

    static public Values Instance() {
        if( _instance == null )
            _instance = new Values();
        return _instance;
    }
    static private Values _instance;

    static public SimpleDateFormat Format() {
        return Instance()._format;
    }

    private Values(){
        _format = new SimpleDateFormat( "yyyy/MM/dd hh:mm:ss" );
    }

    private static SimpleDateFormat _format;
}
