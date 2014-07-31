package com.github.digin.android.repositories;

import android.content.Context;

import com.github.digin.android.listeners.OnChefQueryListener;
import com.github.digin.android.logging.Logger;
import com.github.digin.android.models.Chef;
import com.github.digin.android.tasks.ParseAllChefsTask;

import java.util.List;

/**
 *  Class which handles downloading Chef data from parse, storing this
 *  data locally, querying for cache information, etc.
 *
 *  This is the central place for the presentation layer to get a list of chefs.
 *  That's all you need to know.
 *
 *  Created by mike on 7/11/14.
 */
public abstract class ChefsStore {

    /** The local cache */
    private static List<Chef> chefCache;

    /** Gets a list of all the chefs. This call always returns through the listener
     *  provided. If a copy of the chefs is available locally (in memory) it is used.
     *  Otherwise, this call reaches out to parse asynchronously, returns immediately,
     *  and the list of chefs is provided to the listener on the UI thread. */
    public static void getChefs(Context context, final OnChefQueryListener listener) {

        // Return the local list if it is available
        if (chefCache != null) {
            if (listener != null) {
                listener.onComplete(chefCache);
            }
            return;
        }

        // Query parse
        Logger.log(ChefsStore.class, "Updating local chefs list from Parse");
        ParseAllChefsTask task = new ParseAllChefsTask(context, listener);
        task.execute();

    }

}
