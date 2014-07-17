package com.github.digin.android.repositories;

import com.github.digin.android.listeners.OnChefQueryListener;
import com.github.digin.android.models.Chef;

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
public class ChefsStore {

    /** The last time our local cache was updated */
    private long cacheUpdateTime = 0;

    /** The local cache */
    private List<Chef> chefCache;

    /** An asynchronous call which on completion returns a list of every chef.
     *  Pass in a listener to receive a list of chefs when the call is complete. */
    public void queryChefs(OnChefQueryListener listener) {

        // Do a cache check to see if we need to update the local list.
        boolean invalid = isCacheInvalid();

    }

    /** Checks the status of the local cache to see if anything has changed up on Parse */
    private boolean isCacheInvalid() {
        return false;
    }

}
