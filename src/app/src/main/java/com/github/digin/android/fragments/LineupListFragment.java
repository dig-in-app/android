package com.github.digin.android.fragments;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.ListFragment;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.github.digin.android.R;
import com.github.digin.android.Utils;
import com.github.digin.android.adapters.ChefListAdapter;
import com.github.digin.android.listeners.OnParticipantQueryListener;
import com.github.digin.android.logging.AnalyticsHelper;
import com.github.digin.android.logging.Logger;
import com.github.digin.android.models.Chef;
import com.github.digin.android.models.Participant;
import com.github.digin.android.repositories.ChefsStore;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Created by david on 7/15/14.
 */
public abstract class LineupListFragment<T extends Participant> extends ListFragment implements AdapterView.OnItemClickListener, OnParticipantQueryListener<T> {

    public static final String SORTTEXT = "Sort by %s";
    Sorting otherSorting = Sorting.LOCATION;
    private boolean mChefsLoaded = false;
    private boolean mLoadingError = false;

    public LineupListFragment() {
    }

    @Override
    public abstract void onItemClick(AdapterView<?> parent, View view, int position, long id);

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
        getActivity().invalidateOptionsMenu();
        getListView().setOnItemClickListener(this);

        getChefs();
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public void onComplete(List<T> chefs) {
        if(chefs == null || chefs.size() == 0) {
            mLoadingError = true;
            mChefsLoaded = false;
            setListAdapter(getAdapterForParticipants(new ArrayList<T>()));
        } else {
            mLoadingError = false;
            setListAdapter(getAdapterForParticipants(chefs));
            mChefsLoaded = true;
            getActivity().invalidateOptionsMenu();
        }
        trySetErrorView();
    }

    public abstract void getChefs();

    public abstract ListAdapter getAdapterForParticipants(List<T> items);

    private void trySetErrorView() {
        if(getView() != null) {
            View error = getView().findViewById(R.id.error);
            View loading = getView().findViewById(android.R.id.empty);
            if (mLoadingError) {
                TextView message = (TextView) error.findViewById(R.id.error_message);
                message.setText(getErrorMessage());
                error.setVisibility(View.VISIBLE);

                loading.setVisibility(View.GONE);
            } else {
                error.setVisibility(View.GONE);
            }
        }
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        view.setBackgroundColor(getResources().getColor(android.R.color.white));

        Utils.fixForActionBarHeight(getActivity(), view);

        trySetErrorView();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {

        if (mChefsLoaded) {
            inflater.inflate(R.menu.list, menu);
            MenuItem item = menu.findItem(R.id.action_sort);
            item.setTitle(String.format(SORTTEXT, otherSorting.name().toLowerCase()));
        }

    }



    public enum Sorting {
        NAME(new Comparator<Participant>() {
            @Override
            public int compare(Participant lhs, Participant rhs) {
                return lhs.getName().compareTo(rhs.getName());
            }
        }), LOCATION(new Comparator<Participant>() {
            @Override
            public int compare(Participant lhs, Participant rhs) {
                return lhs.getTent().compareTo(rhs.getTent());
            }
        });

        Comparator<Participant> comparator;

        Sorting(Comparator<Participant> comparator) {
            this.comparator = comparator;
        }

        public Comparator<Participant> getComparator() {
            return comparator;
        }
    }

    public abstract String getErrorMessage();
}
