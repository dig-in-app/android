package com.github.digin.android;

import android.app.Fragment;

import com.github.digin.android.fragments.*;

import java.util.Arrays;
import java.util.List;

public class NavDrawerItem {

    static NavDrawerItem[] items = new NavDrawerItem[]{
            new NavDrawerItem(BoundedMapFragment.class, "Map"),
            new NavDrawerItem(ChefListFragment.class, "Chefs"),
            new NavDrawerItem(WineriesFragment.class, "Wineries"),
            new NavDrawerItem(BreweriesFragment.class, "Breweries"),
            new NavDrawerItem(FavoritesFragment.class, "Favorites")
    };
    String itemName;
    Class<? extends Fragment> fragmentClass;
    Fragment fragment;
    public NavDrawerItem(Class<? extends Fragment> fragmentClass, String itemName) {
        this.fragmentClass = fragmentClass;
        this.itemName = itemName;
    }

    public static List<NavDrawerItem> getItems() {
        return Arrays.asList(items);
    }

    public Fragment getFragment() {
        if (fragment == null) {
            try {
                fragment = fragmentClass.newInstance();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                throw new RuntimeException("The fragment " + fragmentClass.getSimpleName() + " has no public default constructor.");
            }
        }
        return fragment;
    }

    @Override
    public String toString() {
        return itemName;
    }
}
