package com.github.digin.android.repositories;

import android.content.Context;
import android.content.SharedPreferences;

import com.github.digin.android.listeners.OnSingleChefQuery;
import com.github.digin.android.models.Chef;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 *  Stores a user's favorite restaurants
 *  Created by mike on 8/13/14.
 */
public abstract class FavoritesStore {

    private static final String prefsName = "prefsFavorites";
    private static final String chefSetPrefId = "favoriteChefsSet";

    public static void storeFavorite(Context context, Chef chef) {

        SharedPreferences prefs = context.getSharedPreferences(prefsName, 0);

        Set<String> favorites =  prefs.getStringSet(chefSetPrefId, null);
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

        prefs.edit().putStringSet(chefSetPrefId, favorites).commit();

    }

    public static List<Chef> getFavorites(Context context) {

        SharedPreferences prefs = context.getSharedPreferences(prefsName, 0);

        Set<String> favorites = prefs.getStringSet(chefSetPrefId, null);
        if (favorites == null) {
            return new LinkedList<Chef>();
        }

        final List<Chef> chefs = new LinkedList<Chef>();

        // I dont even want to think about the implications of this method if the chefs list isn't
        // already cached in the ChefsStore, which is should be most times this is called.
        // Jesus take the wheel.

        for (String cID : favorites) {
            ChefsStore.getChefById(context, cID, new OnSingleChefQuery() {
                public void onComplete(Chef chef) {
                    chefs.add(chef);
                }
            });
        }

        return chefs;

    }

}
