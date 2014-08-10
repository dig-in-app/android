package com.github.digin.android.repositories;

import android.content.Context;

import com.github.digin.android.listeners.OnWineryQueryListener;
import com.github.digin.android.models.Winery;
import com.github.digin.android.tasks.ParseAllWineriesTask;

import java.util.List;

/**
 * Created by mike on 8/10/14.
 */
public abstract class WineryStore {

    private static List<Winery> wineryCache;

    public static void getWineries(Context context, final OnWineryQueryListener listener) {

        if (wineryCache != null) {
            if (listener != null) {
                listener.onComplete(wineryCache);
            }
            return;
        }

        ParseAllWineriesTask task = new ParseAllWineriesTask(context, new OnWineryQueryListener() {

            @Override
            public void onComplete(List<Winery> wineries) {
                wineryCache = wineries;
                if (listener != null) {
                    listener.onComplete(wineries);
                }
            }

        });
        task.execute();

    }

    public static void getWineriesNoCache(Context context, OnWineryQueryListener listener) {
        wineryCache = null;
        getWineries(context, listener);
    }

}
