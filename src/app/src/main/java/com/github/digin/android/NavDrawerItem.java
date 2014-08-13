package com.github.digin.android;

import android.app.Fragment;

import com.github.digin.android.fragments.BoundedMapFragment;
import com.github.digin.android.fragments.LineupListFragment;

import java.util.Arrays;
import java.util.List;

public class NavDrawerItem {

    static NavDrawerItem[] items = new NavDrawerItem[] {
            new NavDrawerItem(BoundedMapFragment.class, "Map"),
            new NavDrawerItem(LineupListFragment.class, "Lineup")
    };

    public static List<NavDrawerItem> getItems() {
        return Arrays.asList(items);
    }

    String itemName;
    Class<? extends Fragment> fragmentClass;
    Fragment fragment;
    public NavDrawerItem(Class<? extends Fragment> fragmentClass, String itemName) {
        this.fragmentClass = fragmentClass;
        this.itemName = itemName;
    }

    public Fragment getFragment() {
        if(fragment == null) {
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
