package com.github.digin.android.repositories;

import android.content.Context;

import com.github.digin.android.listeners.OnParticipantQueryListener;
import com.github.digin.android.models.Winery;
import com.github.digin.android.tasks.ParseAllWineriesTask;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by mike on 8/10/14.
 */
public abstract class WineryStore {

    private static List<Winery> wineryCache;

    public static void getWineries(Context context, final OnParticipantQueryListener<Winery> listener) {

        if (wineryCache != null) {
            if (listener != null) {
                listener.onComplete(wineryCache);
            }
            return;
        }

        ParseAllWineriesTask task = new ParseAllWineriesTask(context, new OnParticipantQueryListener<Winery>() {

            @Override
            public void onComplete(List<Winery> wineries) {

                // Sort
                Collections.sort(wineries, new Comparator<Winery>() {
                    public int compare(Winery lhs, Winery rhs) {
                        return lhs.getName().compareTo(rhs.getName());
                    }
                });

                wineryCache = wineries;
                if (listener != null) {
                    listener.onComplete(wineries);
                }
            }

        });
        task.execute();

    }

    public static void getWineriesNoCache(Context context, OnParticipantQueryListener<Winery> listener) {
        wineryCache = null;
        getWineries(context, listener);
    }

}
