package com.github.digin.android.repositories;

import android.content.Context;

import com.github.digin.android.listeners.OnBreweryQueryListener;
import com.github.digin.android.models.Brewery;
import com.github.digin.android.tasks.ParseAllBreweriesTask;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Contains methods which return a list of breweries either stored
 * locally or on parse.
 * <p/>
 * Created by mike on 8/3/14.
 */
public abstract class BreweryStore {

    private static List<Brewery> breweriesCache;

    public static void getBreweries(Context context, final OnBreweryQueryListener listener) {

        if (breweriesCache != null) {
            if (listener != null) {
                listener.onComplete(breweriesCache);
            }
            return;
        }

        ParseAllBreweriesTask task = new ParseAllBreweriesTask(context, new OnBreweryQueryListener() {
            @Override
            public void onComplete(List<Brewery> breweries) {

                // Sort
                Collections.sort(breweries, new Comparator<Brewery>() {
                    public int compare(Brewery lhs, Brewery rhs) {
                        return lhs.getName().compareTo(rhs.getName());
                    }
                });

                breweriesCache = breweries;
                if (listener != null) {
                    listener.onComplete(breweries);
                }
            }
        });
        task.execute();

    }

    public static void getBreweriesNoCache(Context context, final OnBreweryQueryListener listener) {
        breweriesCache = null;
        getBreweries(context, listener);
    }

}
