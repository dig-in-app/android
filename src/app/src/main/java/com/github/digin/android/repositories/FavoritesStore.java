package com.github.digin.android.repositories;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.Preference;
import android.provider.Telephony;

import com.github.digin.android.listeners.OnParticipantQueryListener;
import com.github.digin.android.models.Brewery;
import com.github.digin.android.models.Chef;
import com.github.digin.android.models.Participant;
import com.github.digin.android.models.Winery;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * Stores a user's favorite restaurants
 * Created by mike on 8/13/14.
 */
public abstract class FavoritesStore {

    private static final String prefsName = "prefsFavorites";
    private static final String chefSetPrefId = "favoriteChefsSet";

    public static void storeFavorite(Context context, Participant chef) {

        SharedPreferences prefs = context.getSharedPreferences(prefsName, 0);

        Set<String> favorites = prefs.getStringSet(chefSetPrefId, null);
        if (favorites == null) {
            favorites = new HashSet<String>();
        }

        // Make sure the chef isn't already a favorite
        if (contains(context, chef)) {
            return;
        }

        // Store the ID
        favorites.add(chef.getId());

        // Recommit to the shared prefs
        prefs.edit().putStringSet(chefSetPrefId, favorites).commit();

    }

    public static boolean contains(Context context, Participant chef) {

        SharedPreferences prefs = context.getSharedPreferences(prefsName, 0);

        Set<String> favorites = prefs.getStringSet(chefSetPrefId, null);
        if (favorites == null) {
            return false;
        }
        if (favorites.contains(chef.getId())) {
            return true;
        }
        return false;

    }

    public static void removeFavorite(Context context, Participant chef) {

        SharedPreferences prefs = context.getSharedPreferences(prefsName, 0);

        Set<String> favorites = prefs.getStringSet(chefSetPrefId, null);
        if (favorites == null) {
            return;
        }

        if (!contains(context, chef)) {
            return;
        }

        favorites.remove(chef.getId());

        prefs.edit().putStringSet(chefSetPrefId, favorites).commit();

    }

    public static void getFavorites(final Context context, final OnParticipantQueryListener<Participant> listener) {

        SharedPreferences prefs = context.getSharedPreferences(prefsName, 0);

        final Set<String> favorites = prefs.getStringSet(chefSetPrefId, null);
        if (favorites == null) {
            if (listener != null) {
                listener.onComplete(new LinkedList<Participant>());
            }
            return;
        }

        final List<Participant> participants = new LinkedList<Participant>();


        ChefsStore.batchGetChefById(context, favorites, new OnParticipantQueryListener<Chef>() {
            @Override
            public void onComplete(List<Chef> items) {
                participants.addAll(items);

                BreweryStore.batchGetBreweryById(context, favorites, new OnParticipantQueryListener<Brewery>() {
                    @Override
                    public void onComplete(List<Brewery> items) {
                        participants.addAll(items);

                        WineryStore.batchGetWineryById(context, favorites, new OnParticipantQueryListener<Winery>() {
                            @Override
                            public void onComplete(List<Winery> items) {
                                participants.addAll(items);

                                listener.onComplete(participants);
                            }
                        });
                    }
                });
            }
        });
    }
}
