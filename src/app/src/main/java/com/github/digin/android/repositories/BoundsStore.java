package com.github.digin.android.repositories;

import android.content.Context;

import com.github.digin.android.listeners.OnBoundsQueryListener;
import com.github.digin.android.listeners.OnBoundsRetrievalListener;
import com.github.digin.android.models.map.Bounds;
import com.github.digin.android.tasks.ParseAllBoundsTask;

import java.util.Map;

/**
 * Created by mike on 8/10/14.
 */
public abstract class BoundsStore {

    public static final String EVENT = "Event";
    public static final String TENT_1 = "Tent 1";
    public static final String TENT_2 = "Tent 2";
    public static final String TENT_3 = "Tent 3";
    public static final String TENT_4 = "Tent 4";
    public static final String TENT_5 = "Tent 5";
    public static final String TENT_6 = "Tent 6";
    public static final String TENT_VIP = "VIP Tent";
    private static Map<String, Bounds> map;

    public static void getBounds(Context context, final String location, final OnBoundsRetrievalListener listener) {

        if (map != null) {
            if (listener != null) {
                listener.onComplete(map.get(location));
            }
            return;
        }

        ParseAllBoundsTask task = new ParseAllBoundsTask(context, new OnBoundsQueryListener() {
            public void onComplete(Map<String, Bounds> bounds) {
                map = bounds;
                if (listener != null) {
                    listener.onComplete(map.get(location));
                }
            }
        });
        task.execute();

    }

}
