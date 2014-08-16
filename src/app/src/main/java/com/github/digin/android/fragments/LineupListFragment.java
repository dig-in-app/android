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
import android.widget.TextView;

import com.github.digin.android.R;
import com.github.digin.android.Utils;
import com.github.digin.android.adapters.ChefListAdapter;
import com.github.digin.android.listeners.OnChefQueryListener;
import com.github.digin.android.logging.AnalyticsHelper;
import com.github.digin.android.logging.Logger;
import com.github.digin.android.models.Chef;
import com.github.digin.android.repositories.ChefsStore;

import java.util.Comparator;
import java.util.List;

/**
 * Created by david on 7/15/14.
 */
public class LineupListFragment extends ListFragment implements AdapterView.OnItemClickListener, OnChefQueryListener {

    public static final String SORTTEXT = "Sort by %s";
    Sorting otherSorting = Sorting.LOCATION;
    private boolean mChefsLoaded = false;
    private boolean mLoadingError = false;

    public LineupListFragment() {
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Chef chef = ((ChefListAdapter) getListAdapter()).getItem(position);

        AnalyticsHelper.sendEvent(getActivity(), "List_Click", LineupListFragment.class.getName(), chef.getName());

        Logger.log(LineupListFragment.class, "onItemClick(): " + chef.getName());
        DetailsFragment details = DetailsFragment.newInstance(chef);
        getFragmentManager().beginTransaction().addToBackStack(DetailsFragment.class.getName()).replace(R.id.content_frame, details, DetailsFragment.class.getName()).commit();

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

        getChefs();
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
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        AnalyticsHelper.sendScreenView(getActivity(), LineupListFragment.class, "Main List");
    }

    @Override
    public void onComplete(List<Chef> chefs) {
        if(chefs == null || chefs.size() == 0) {

            mLoadingError = true;

            trySetErrorView();

        } else {
            setListAdapter(new ChefListAdapter(getActivity(), chefs));
            mChefsLoaded = true;
            getActivity().invalidateOptionsMenu();
        }
    }

    public void getChefs() {
        ChefsStore.getChefs(getActivity().getBaseContext(), this);
    }

    private void trySetErrorView() {
        if(getView() != null && mLoadingError) {
            View error = getView().findViewById(R.id.error);

            TextView message = (TextView) error.findViewById(R.id.error_message);
            message.setText(getErrorMessage());
            error.setVisibility(View.VISIBLE);

            View loading = getView().findViewById(android.R.id.empty);
            loading.setVisibility(View.GONE);
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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_sort:
                ((ChefListAdapter) getListAdapter()).sort(otherSorting);
                otherSorting = ((otherSorting == Sorting.NAME) ? Sorting.LOCATION : Sorting.NAME);
                item.setTitle(String.format(SORTTEXT, otherSorting.name().toLowerCase()));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

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

    public String getErrorMessage() {
        return "We are sorry, we can't load the list right now.\nPlease try loading it again.";
    }
}
