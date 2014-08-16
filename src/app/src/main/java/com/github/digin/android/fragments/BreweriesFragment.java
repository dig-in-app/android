package com.github.digin.android.fragments;

import android.app.Activity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;

import com.github.digin.android.R;
import com.github.digin.android.adapters.ChefListAdapter;
import com.github.digin.android.adapters.ParticipantListAdapter;
import com.github.digin.android.logging.AnalyticsHelper;
import com.github.digin.android.logging.Logger;
import com.github.digin.android.models.Brewery;
import com.github.digin.android.models.Winery;
import com.github.digin.android.repositories.BreweryStore;

import java.util.List;

/**
 * Created by david on 8/16/14.
 */
public class BreweriesFragment extends LineupListFragment<Brewery> {
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Brewery brewery = ((ParticipantListAdapter<Brewery>) getListAdapter()).getItem(position);

        AnalyticsHelper.sendEvent(getActivity(), "List_Click", BreweriesFragment.class.getName(), brewery.getName());

        Logger.log(BreweriesFragment.class, "onItemClick(): " + brewery.getName());

        BreweryDetailsFragment details = BreweryDetailsFragment.newInstance(brewery);
        getFragmentManager().beginTransaction().addToBackStack(DetailsFragment.class.getName()).add(R.id.content_frame, details, ParticipantDetailsFragment.class.getName()).commit();
    }

    @Override
    public void onAttach(final Activity activity) {
        super.onAttach(activity);

        AnalyticsHelper.sendScreenView(getActivity(), BreweriesFragment.class, "Breweries");
    }

    @Override
    public void getChefs() {
        BreweryStore.getBreweries(getActivity(), this);
    }

    @Override
    public ListAdapter getAdapterForParticipants(List<Brewery> items) {
        return new ParticipantListAdapter<Brewery>(getActivity(), items);
    }

    @Override
    public String getErrorMessage() {
        return "We were not able to load your beer at this time. Please try again";
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_sort:
                ((ParticipantListAdapter<Brewery>) getListAdapter()).sort(otherSorting);
                otherSorting = ((otherSorting == Sorting.NAME) ? Sorting.LOCATION : Sorting.NAME);
                item.setTitle(String.format(SORTTEXT, otherSorting.name().toLowerCase()));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
