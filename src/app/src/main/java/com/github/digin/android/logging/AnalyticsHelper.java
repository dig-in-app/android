package com.github.digin.android.logging;

import android.app.Activity;
import android.app.Fragment;

import com.github.digin.android.DiginApplication;
import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;

/**
 * Created by david on 8/14/14.
 */
public class AnalyticsHelper {

    public static void sendScreenView(Activity activity, Class<? extends Fragment> fragmentClass, String... data) {
        // Get tracker.
        Tracker t = ((DiginApplication) activity.getApplication()).getTracker(
                DiginApplication.TrackerName.APP_TRACKER);


        StringBuilder sb = new StringBuilder(fragmentClass.getSimpleName() + " : ");
        for (String foo : data) {
            sb.append(" | ");
            sb.append(foo);
        }
        t.setScreenName(sb.toString());

        // Send a screen view.
        t.send(new HitBuilders.AppViewBuilder().build());
    }

    public static void sendEvent(Activity activity, String category, String action, String label) {
        // Get tracker.
        Tracker t = ((DiginApplication) activity.getApplication()).getTracker(
                DiginApplication.TrackerName.APP_TRACKER);
        // Build and send an Event.
        t.send(new HitBuilders.EventBuilder()
                .setCategory(category)
                .setAction(action)
                .setLabel(label)
                .build());
    }
}
