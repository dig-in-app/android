package com.github.digin.android.constants;

import android.content.Context;

import com.github.digin.android.R;

/**
 * Contains parse's application and secret keys
 * <p/>
 * These are read in from a string resources file which is gitignored due to the
 * open source nature of our application. If you'd like to compile this app, you'll
 * either need these parse keys or a duplicate parse database with all the information
 * pertaining to the digin event.
 * <p/>
 * Contact David or Michael on github for help with either of these.
 * <p/>
 * Created by mike on 7/11/14.
 */
public abstract class ParseKeys {

    public static String getAppId(Context c) {
        String appId = c.getResources().getString(R.string.parse_app_id);
        return appId;
    }

    public static String getClientKey(Context c) {
        String clientKey = c.getResources().getString(R.string.parse_client_key);
        return clientKey;
    }

}
