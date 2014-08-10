package com.github.digin.android.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.digin.android.R;
import com.manuelpeinado.fadingactionbar.FadingActionBarHelper;

/**
 * Created by david on 7/27/14.
 */
public class DetailsFragment extends Fragment {

    private FadingActionBarHelper mFadingHelper;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = mFadingHelper.createView(inflater);

        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);


        mFadingHelper = new FadingActionBarHelper()
                .actionBarBackground(android.R.color.holo_red_dark)
                .headerLayout(R.layout.details_header_image)
                .contentLayout(R.layout.details_fragment).lightActionBar(true);
        mFadingHelper.initActionBar(activity);
    }


}
