package com.github.digin.android.fragments;

import android.app.Activity;
import android.app.ListFragment;
import android.os.Bundle;
import android.view.View;

import com.github.digin.android.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.github.digin.android.Utils;
import com.github.digin.android.adapters.*;
import com.github.digin.android.bitmap.BitmapCacheHost;
import com.github.digin.android.listeners.OnChefQueryListener;
import com.github.digin.android.models.Chef;
import com.github.digin.android.models.Participant;
import com.github.digin.android.models.TemporaryParticipantPlaceholder;
import com.github.digin.android.repositories.ChefsStore;

/**
 * Created by david on 7/15/14.
 */
public class ParticipantListFragment extends ListFragment {



    public ParticipantListFragment() {
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
}
