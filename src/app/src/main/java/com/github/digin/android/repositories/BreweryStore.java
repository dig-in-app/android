package com.github.digin.android.repositories;

import android.content.Context;

import com.github.digin.android.listeners.OnBreweryQueryListener;
import com.github.digin.android.logging.Logger;
import com.github.digin.android.models.Participant;
import com.github.digin.android.tasks.ParseAllBreweriesTask;

import java.util.List;

/**
 *  Contains methods which return a list of breweries either stored
 *  locally or on parse.
 *
 *  Created by mike on 8/3/14.
 */
public abstract class BreweryStore {

    private static List<Participant> breweriesCache;

    public static void getBreweries(Context context, final OnBreweryQueryListener listener) {

        if (breweriesCache != null) {
            if (listener != null) {
                listener.onComplete(breweriesCache);
            }
            return;
        }

        Logger.log(BreweryStore.class, "Querying parse for brewery information.");
        ParseAllBreweriesTask task = new ParseAllBreweriesTask(context, new OnBreweryQueryListener() {
            @Override
            public void onComplete(List<Participant> breweries) {
                breweriesCache = breweries;
                listener.onComplete(breweries);
            }
        });
        task.execute();

    }

    public static void getBreweriesNoCache(Context context, final OnBreweryQueryListener listener) {
        breweriesCache = null;
        getBreweries(context, listener);
    }

}
