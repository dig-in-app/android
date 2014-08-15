package com.github.digin.android.fragments;

import android.app.Activity;
import android.app.ListFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.github.digin.android.R;
import com.github.digin.android.Utils;
import com.github.digin.android.adapters.ChefListAdapter;
import com.github.digin.android.listeners.OnChefQueryListener;
import com.github.digin.android.logging.AnalyticsHelper;
import com.github.digin.android.logging.Logger;
import com.github.digin.android.models.Chef;
import com.github.digin.android.repositories.FavoritesStore;

import java.util.List;

/**
 * Created by mike on 8/13/14.
 */
public class FavoritesFragment extends LineupListFragment {

    public FavoritesFragment() {
    }

    @Override
    public void onAttach(final Activity activity) {
        super.onAttach(activity);

        FavoritesStore.getFavorites(getActivity(), new OnChefQueryListener() {
            @Override
            public void onComplete(List<Chef> chefs) {
                setListAdapter(new ChefListAdapter(getActivity(), chefs));
            }
        });
        AnalyticsHelper.sendScreenView(getActivity(), FavoritesFragment.class);

    }


}
