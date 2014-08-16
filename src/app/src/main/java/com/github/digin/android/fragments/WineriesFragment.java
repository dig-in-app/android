package com.github.digin.android.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.github.digin.android.R;
import com.github.digin.android.adapters.ChefListAdapter;
import com.github.digin.android.logging.AnalyticsHelper;
import com.github.digin.android.logging.Logger;
import com.github.digin.android.models.Chef;
import com.github.digin.android.models.Winery;
import com.github.digin.android.repositories.FavoritesStore;
import com.github.digin.android.repositories.WineryStore;

import java.util.List;

public class WineriesFragment extends LineupListFragment<Winery> {



    public WineriesFragment() {
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Winery winery = ((ParticipantListAdapter<Winery>) getListAdapter()).getItem(position);



        AnalyticsHelper.sendEvent(getActivity(), "List_Click", WineriesFragment.class.getName(), winery.getName());

        Logger.log(LineupListFragment.class, "onItemClick(): " + winery.getName());

        WineryDetailsFragment details = WineryDetailsFragment.newInstance(winery);
        getFragmentManager().beginTransaction().addToBackStack(DetailsFragment.class.getName()).replace(R.id.content_frame, details, ParticipantDetailsFragment.class.getName()).commit();
    }

    @Override
    public void onAttach(final Activity activity) {
        super.onAttach(activity);

        AnalyticsHelper.sendScreenView(getActivity(), WineriesFragment.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v =  super.onCreateView(inflater, container, savedInstanceState);
        TextView tv = (TextView) v.findViewById(R.id.txt_progress);
        tv.setText("Loading Favorites");
        return v;
    }

    @Override
    public void getChefs() {
        WineryStore.getWineries(getActivity(), this);
    }

    @Override
    public String getErrorMessage() {
        return "Sorry, we couldn't load your wines. Please try again.";
    }

    @Override
    public ListAdapter getAdapterForParticipants(List<Winery> items) {
        return new ParticipantListAdapter<Winery>(getActivity(), items);
    }


}
