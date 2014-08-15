package com.github.digin.android.repositories;

import android.content.Context;
import android.content.SharedPreferences;

import com.github.digin.android.listeners.OnChefQueryListener;
import com.github.digin.android.models.Chef;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

/**
 * Stores a user's favorite restaurants
 * Created by mike on 8/13/14.
 */
public abstract class FavoritesStore {

    private static final String prefsName = "prefsFavorites";
    private static final String chefSetPrefId = "favoriteChefsSet";

    public static void storeFavorite(Context context, Chef chef) {

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
        prefs.edit().putStringSet(chefSetPrefId, favorites).apply();

    }

    public static boolean contains(Context context, Chef chef) {

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

    public static void removeFavorite(Context context, Chef chef) {

        SharedPreferences prefs = context.getSharedPreferences(prefsName, 0);

        Set<String> favorites = prefs.getStringSet(chefSetPrefId, null);
        if (favorites == null) {
            return;
        }

        if (!contains(context, chef)) {
            return;
        }

        favorites.remove(chef.getId());

        prefs.edit().putStringSet(chefSetPrefId, favorites).apply();

    }

    public static void getFavorites(final Context context, final OnChefQueryListener listener) {

        SharedPreferences prefs = context.getSharedPreferences(prefsName, 0);

        final Set<String> favorites = prefs.getStringSet(chefSetPrefId, null);
        if (favorites == null) {
            if (listener != null) {
                listener.onComplete(new LinkedList<Chef>());
            }
            return;
        }

        ChefsStore.batchGetChefById(context, favorites, listener);

    }

}
