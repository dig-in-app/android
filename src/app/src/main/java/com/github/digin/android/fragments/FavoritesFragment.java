package com.github.digin.android.fragments;

import android.app.Activity;
import android.app.Fragment;
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
import com.github.digin.android.logging.Logger;
import com.github.digin.android.models.Chef;
import com.github.digin.android.repositories.ChefsStore;
import com.github.digin.android.repositories.FavoritesStore;

import java.util.List;

/**
 * Created by mike on 8/13/14.
 */
public class FavoritesFragment extends ListFragment {

    public FavoritesFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.chef_list_layout, container, false);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);


        Chef chef = ((ChefListAdapter) getListAdapter()).getItem(position);

        Logger.log(getClass(), "onItemClick(): " + chef.getName());
        DetailsFragment details = DetailsFragment.newInstance(chef);

        getFragmentManager().beginTransaction().addToBackStack(DetailsFragment.class.getName()).add(R.id.content_frame, details, DetailsFragment.class.getName()).commit();
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

    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        view.setBackgroundColor(getResources().getColor(android.R.color.white));

        Utils.fixForActionBarHeight(getActivity(), view);
    }

}
