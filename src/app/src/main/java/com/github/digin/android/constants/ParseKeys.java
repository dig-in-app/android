package com.github.digin.android.constants;

import android.content.Context;

import com.github.digin.android.R;

/**
 *  Fakse ParseKeys.java file.
 *
 *  DO NOT ADD ANYTHING TO THIS FILE. IT WILL BE OVERWRITTEN DURING COMPILE TIME.
 *  It is just here to provide method signatures to the rest of the application.
 *
 *  Created by mike on 7/11/14.
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
