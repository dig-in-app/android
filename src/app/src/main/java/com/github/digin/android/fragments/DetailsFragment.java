package com.github.digin.android.fragments;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.digin.android.R;
import com.github.digin.android.bitmap.BitmapUtils;
import com.github.digin.android.listeners.OnSingleChefQuery;
import com.github.digin.android.logging.Logger;
import com.github.digin.android.models.Chef;
import com.github.digin.android.repositories.ChefsStore;
import com.manuelpeinado.fadingactionbar.FadingActionBarHelper;

/**
 * Created by david on 7/27/14.
 */
public class DetailsFragment extends Fragment {

    private FadingActionBarHelper mFadingHelper;


    public Chef mChef;
    private CharSequence mOldTitle;

    public static DetailsFragment newInstance(Chef chef) {
        Bundle b = new Bundle();
        b.putString("ID", chef.getId());

        DetailsFragment details = new DetailsFragment();
        details.setArguments(b);
        return details;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mFadingHelper = new FadingActionBarHelper()
                .actionBarBackground(R.drawable.ab_solid_diginpassport)
                .headerLayout(R.layout.details_header_image)
                .contentLayout(R.layout.details_fragment).lightActionBar(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = mFadingHelper.createView(inflater);

        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ImageView iv = (ImageView) view.findViewById(R.id.header);
        Bitmap b = ((BitmapDrawable)iv.getDrawable()).getBitmap();
        int color = BitmapUtils.getAverageColor(b);

        view.setBackgroundColor(color);

        tryFillData();
    }

    private void tryFillData() {
        if(mChef != null && getView() != null) {
            Logger.log(getClass(), "Filling data");
            TextView t = (TextView) getView().findViewById(R.id.nameText);
            t.setText(mChef.getName());
            mOldTitle = getActivity().getActionBar().getTitle();

            getActivity().getActionBar().setTitle(mChef.getName());
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        String chefId = getArguments().getString("ID");

        ChefsStore.getChefById(activity, chefId, new OnSingleChefQuery() {

            @Override
            public void onComplete(Chef chef) {
                Logger.log(getClass(), "Chef found for id");
                DetailsFragment.this.mChef = chef;
                tryFillData();
            }
        });



    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
        mFadingHelper.initActionBar(getActivity());
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);

        if(isVisible()) {

        } else {
            getActivity().getActionBar().setBackgroundDrawable(getActivity().getResources().getDrawable(R.drawable.ab_solid_diginpassport));
            getActivity().getActionBar().setTitle(mOldTitle);
        }
    }
}
