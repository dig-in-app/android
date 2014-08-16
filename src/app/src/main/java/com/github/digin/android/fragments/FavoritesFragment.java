package com.github.digin.android.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.github.digin.android.R;
import com.github.digin.android.adapters.ChefListAdapter;
import com.github.digin.android.logging.AnalyticsHelper;
import com.github.digin.android.logging.Logger;
import com.github.digin.android.models.Chef;
import com.github.digin.android.repositories.FavoritesStore;

import java.util.List;

/**
 * Created by mike on 8/13/14.
 */
public class FavoritesFragment extends LineupListFragment<Chef> {

    public FavoritesFragment() {
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Chef chef = ((ChefListAdapter) getListAdapter()).getItem(position);

        AnalyticsHelper.sendEvent(getActivity(), "List_Click", FavoritesFragment.class.getName(), chef.getName());

        Logger.log(LineupListFragment.class, "onItemClick(): " + chef.getName());
        DetailsFragment details = DetailsFragment.newInstance(chef);
        getFragmentManager().beginTransaction().addToBackStack(DetailsFragment.class.getName()).replace(R.id.content_frame, details, DetailsFragment.class.getName()).commit();
    }

    @Override
    public void onAttach(final Activity activity) {
        super.onAttach(activity);

        AnalyticsHelper.sendScreenView(getActivity(), FavoritesFragment.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v =  super.onCreateView(inflater, container, savedInstanceState);
        TextView tv = (TextView) v.findViewById(R.id.txt_progress);
        tv.setText("Loading Favorites");
        return v;
    }

    @Override
    public String getErrorMessage() {
        return "It looks like you have not selected any favorites.\nClick \"favorite\" at the bottom of any chef's page to favorite them.";
    }

    @Override
    public void getChefs() {
        FavoritesStore.getFavorites(getActivity(), this);
    }

    @Override
    public ListAdapter getAdapterForParticipants(List items) {
        return new ChefListAdapter(getActivity(), items);
    }


}
