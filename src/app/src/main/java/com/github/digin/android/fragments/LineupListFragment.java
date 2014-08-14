package com.github.digin.android.fragments;

import android.app.Activity;
import android.app.ListFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.github.digin.android.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import com.github.digin.android.Utils;
import com.github.digin.android.adapters.*;
import com.github.digin.android.bitmap.BitmapCacheHost;
import com.github.digin.android.listeners.OnChefQueryListener;
import com.github.digin.android.logging.Logger;
import com.github.digin.android.models.Chef;
import com.github.digin.android.models.Participant;
import com.github.digin.android.models.TemporaryParticipantPlaceholder;
import com.github.digin.android.repositories.ChefsStore;

/**
 * Created by david on 7/15/14.
 */
public class LineupListFragment extends ListFragment {

    public enum Sorting {
        NAME(new Comparator<Chef>() {
            @Override
            public int compare(Chef lhs, Chef rhs) {
                return lhs.getName().compareTo(rhs.getName());
            }
        }), LOCATION(new Comparator<Chef>() {
            @Override
            public int compare(Chef lhs, Chef rhs) {
                return lhs.getTent().compareTo(rhs.getTent());
            }
        });

        Comparator<Chef> comparator;

        Sorting(Comparator<Chef> comparator) {
            this.comparator = comparator;
        }

        public Comparator<Chef> getComparator() {
            return comparator;
        }
    }

    public static final String SORTTEXT = "Sort by %s";
    Sorting currentSorting = Sorting.NAME;


    public LineupListFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.chef_list_layout, container, false);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        getListView().setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Chef chef = ((ChefListAdapter) getListAdapter()).getItem(position);
                Logger.log(LineupListFragment.this.getClass(), "onItemClick(): " + chef.getName() );
                DetailsFragment details = DetailsFragment.newInstance(chef);
                getFragmentManager().beginTransaction().addToBackStack(DetailsFragment.class.getName()).add(R.id.content_frame, details, DetailsFragment.class.getName()).commit();
            }
        });
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        ChefsStore.getChefs(activity.getBaseContext(), new OnChefQueryListener() {
            @Override
            public void onComplete(List<Chef> chefs) {
                setListAdapter(new ChefListAdapter(getActivity(), chefs));
            }
        });
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        view.setBackgroundColor(getResources().getColor(android.R.color.white));

        Utils.fixForActionBarHeight(getActivity(), view);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.list, menu);
        MenuItem item = menu.findItem(R.id.action_sort);
        item.setTitle( String.format( SORTTEXT, currentSorting.name().toLowerCase() ));

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.action_sort:
                currentSorting = ((currentSorting == Sorting.NAME) ? Sorting.LOCATION : Sorting.NAME);
                item.setTitle( String.format( SORTTEXT, currentSorting.name().toLowerCase() ));
                ((ChefListAdapter) getListAdapter()).sort(currentSorting);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
