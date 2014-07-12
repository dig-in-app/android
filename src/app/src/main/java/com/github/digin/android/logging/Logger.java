package com.github.digin.android.logging;

import android.util.Log;

/**
 *  A facade for the stock android logging utilities which standardizes the
 *  way log tags are created and used.
 *  Created by mike on 7/11/14.
 */
public abstract class Logger {

    private static final String LOG_PREFIX = "digin-";

    /** Standard log */
    public static void log(Class c, String message) {
        Log.i(createTag(c), message);
    }

    /** Something terrible has happened */
    public static void err(Class c, String message, Exception e) {
        Log.e(createTag(c), message);
        Log.e(createTag(c), e.getLocalizedMessage());
    }

    /** Constructs a log tag given a class name */
    private static String createTag(Class c) {
        return LOG_PREFIX + c.getName();
    }

}
