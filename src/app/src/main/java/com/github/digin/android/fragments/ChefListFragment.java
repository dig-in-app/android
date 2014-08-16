package com.github.digin.android.fragments;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;

import com.github.digin.android.R;
import com.github.digin.android.adapters.ChefListAdapter;
import com.github.digin.android.logging.AnalyticsHelper;
import com.github.digin.android.logging.Logger;
import com.github.digin.android.models.Chef;
import com.github.digin.android.repositories.ChefsStore;

import java.util.List;

/**
 * Created by david on 8/15/14.
 */
public class ChefListFragment extends LineupListFragment<Chef> {

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Chef chef = ((ChefListAdapter) getListAdapter()).getItem(position);

        AnalyticsHelper.sendEvent(getActivity(), "List_Click", LineupListFragment.class.getName(), chef.getName());

        Logger.log(LineupListFragment.class, "onItemClick(): " + chef.getName());
        DetailsFragment details = DetailsFragment.newInstance(chef);
        getFragmentManager().beginTransaction().addToBackStack(DetailsFragment.class.getName()).replace(R.id.content_frame, details, DetailsFragment.class.getName()).commit();
    }

    public void getChefs() {
        ChefsStore.getChefs(getActivity().getBaseContext(), this);
    }

    @Override
    public ListAdapter getAdapterForParticipants(List<Chef> items) {
        return new ChefListAdapter(getActivity(), items);
    }

    public String getErrorMessage() {
        return "We are sorry, we can't load the list right now.\nPlease try loading it again.";
    }


}
