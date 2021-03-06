package com.github.digin.android.repositories;

import android.content.Context;

import com.github.digin.android.listeners.OnParticipantQueryListener;
import com.github.digin.android.listeners.OnSingleParticipantQueryListener;
import com.github.digin.android.logging.Logger;
import com.github.digin.android.models.Chef;
import com.github.digin.android.tasks.ParseAllChefsTask;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * Class which handles downloading Chef data from parse, storing this
 * data locally, querying for cache information, etc.
 * <p/>
 * Created by mike on 7/11/14.
 */
public abstract class ChefsStore {

    /**
     * The local cache
     */
    private static List<Chef> chefCache;

    /**
     * Gets a list of all the chefs. This call always returns through the listener
     * provided. If a copy of the chefs is available locally (in memory) it is used.
     * Otherwise, this call reaches out to parse asynchronously, returns immediately,
     * and the list of chefs is provided to the listener on the UI thread.
     */
    public static void getChefs(Context context, final OnParticipantQueryListener<Chef> listener) {

        // Return the local list if it is available
        if (chefCache != null) {
            if (listener != null) {
                listener.onComplete(chefCache);
            }
            return;
        }

        // Query parse
        Logger.log(ChefsStore.class, "Updating local chefs list from Parse");
        ParseAllChefsTask task = new ParseAllChefsTask(context, new OnParticipantQueryListener<Chef>() {
            @Override
            public void onComplete(List<Chef> chefs) {

                // Sort the list
                Collections.sort(chefs, new Comparator<Chef>() {
                    public int compare(Chef lhs, Chef rhs) {
                        return lhs.getName().compareTo(rhs.getName());
                    }
                });

                // Store the returned list locally
                chefCache = chefs;

                // Alert the listener passed in to the storage framework
                if (listener != null) {
                    listener.onComplete(chefs);
                }

            }
        });
        task.execute();

    }

    /**
     * Forces this class to reach out to parse to update instead of getting from the local cache
     */
    public static void getChefsNoCache(Context context, final OnParticipantQueryListener<Chef> listener) {
        chefCache = null;
        getChefs(context, listener);
    }

    public static void getChefById(Context context, final String id, final OnSingleParticipantQueryListener<Chef> listener) {
        Logger.log(ChefsStore.class, "Getting chef for ID");
        getChefs(context, new OnParticipantQueryListener<Chef>() {
            @Override
            public void onComplete(List<Chef> chefs) {

                for (Chef chef : chefs) {
                    if (chef.getId().equals(id)) {
                        listener.onComplete(chef);
                        return;
                    }

                }
                Logger.log(ChefsStore.class, "Found no chef matching given ID");
                listener.onComplete(null);
            }
        });
    }

    public static void batchGetChefById(Context context, final Set<String> ids, final OnParticipantQueryListener<Chef> listener) {
        Logger.log(ChefsStore.class, "Getting a subset of chefs by id");

        getChefs(context, new OnParticipantQueryListener<Chef>() {
            public void onComplete(List<Chef> chefs) {

                List<Chef> subset = new LinkedList<Chef>();
                for (Chef chef : chefs) {
                    if (ids.contains(chef.getId())) {
                        subset.add(chef);
                    }
                }

                if (listener != null) {
                    listener.onComplete(subset);
                }

            }
        });

    }

}
