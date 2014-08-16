package com.github.digin.android.repositories;

import android.app.Activity;
import android.content.Context;

import com.github.digin.android.fragments.BreweryDetailsFragment;
import com.github.digin.android.listeners.OnParticipantQueryListener;
import com.github.digin.android.listeners.OnSingleParticipantQueryListener;
import com.github.digin.android.logging.Logger;
import com.github.digin.android.models.Brewery;
import com.github.digin.android.models.Chef;
import com.github.digin.android.models.Winery;
import com.github.digin.android.tasks.ParseAllBreweriesTask;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * Contains methods which return a list of breweries either stored
 * locally or on parse.
 * <p/>
 * Created by mike on 8/3/14.
 */
public abstract class BreweryStore {

    private static List<Brewery> breweriesCache;

    public static void getBreweries(Context context, final OnParticipantQueryListener<Brewery> listener) {

        if (breweriesCache != null) {
            if (listener != null) {
                listener.onComplete(breweriesCache);
            }
            return;
        }

        ParseAllBreweriesTask task = new ParseAllBreweriesTask(context, new OnParticipantQueryListener<Brewery>() {
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

    public static void getBreweriesNoCache(Context context, final OnParticipantQueryListener<Brewery> listener) {
        breweriesCache = null;
        getBreweries(context, listener);
    }

    //TODO: Change wine to beer.
    public static void getBreweryById(Context context, final String id, final OnSingleParticipantQueryListener<Brewery> listener) {
        Logger.log(ChefsStore.class, "Getting chef for ID");
        getBreweries(context, new OnParticipantQueryListener<Brewery>() {
            @Override
            public void onComplete(List<Brewery> wineries) {

                for (Brewery winery : wineries) {
                    if (winery.getId().equals(id)) {
                        listener.onComplete(winery);
                        return;
                    }

                }
                Logger.log(BreweryStore.class, "Found no brewery matching given ID");
                listener.onComplete(null);
            }
        });
    }

    public static void batchGetBreweryById(Context context, final Set<String> ids, final OnParticipantQueryListener<Brewery> listener) {
        Logger.log(ChefsStore.class, "Getting a subset of chefs by id");

        getBreweries(context, new OnParticipantQueryListener<Brewery>() {
            public void onComplete(List<Brewery> chefs) {

                List<Brewery> subset = new LinkedList<Brewery>();
                for (Brewery chef : chefs) {
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
