package com.github.digin.android.fragments;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.github.digin.android.R;
import com.github.digin.android.listeners.OnSingleChefQuery;
import com.github.digin.android.logging.AnalyticsHelper;
import com.github.digin.android.logging.Logger;
import com.github.digin.android.models.Chef;
import com.github.digin.android.repositories.ChefsStore;
import com.github.digin.android.repositories.FavoritesStore;
import com.loopj.android.image.SmartImageView;
import com.manuelpeinado.fadingactionbar.FadingActionBarHelper;

/**
 * Created by david on 7/27/14.
 */
public class DetailsFragment extends Fragment implements View.OnClickListener, OnSingleChefQuery {

    public Chef mChef;
    private FadingActionBarHelper mFadingHelper;
    private CharSequence mOldTitle;
    private Button favoriteButton, yelpButton;
    private Button webButton;

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

        // prepare buttons

        yelpButton = (Button) view.findViewById(R.id.details_button_yelp);
        if (mChef.getYelpURL() == null || mChef.getYelpURL().equals("")) {
            yelpButton.setVisibility(View.GONE);
        } else {
            yelpButton.setOnClickListener(this);
        }

        webButton = (Button) view.findViewById(R.id.details_button_website);
        webButton.setOnClickListener(this);

        favoriteButton = (Button) view.findViewById(R.id.details_button_favorite);
        favoriteButton.setOnClickListener(this);

        if (FavoritesStore.contains(getActivity(), mChef)) {
            favoriteButton.setText("Unfavorite");
        }

        return view;
    }

    @Override
    public void onViewCreated(final View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        view.setBackgroundColor(Color.parseColor("#b48e22")); //Yellowish


        tryFillData();
    }

    private void tryFillData() {

        if (mChef != null && getView() != null) {
            Logger.log(DetailsFragment.class, "Filling data");
            TextView t = (TextView) getView().findViewById(R.id.nameText);
            t.setText(mChef.getName());
            mOldTitle = getActivity().getActionBar().getTitle();

            getActivity().getActionBar().setTitle(mChef.getName());

            SmartImageView siv = (SmartImageView) getView().findViewById(R.id.logo);
            if (mChef.getThumbnail() == null)
                siv.setImageResource(R.drawable.logo);
            else {
                Logger.log(DetailsFragment.class, mChef.getThumbnail());
                siv.setImageUrl(mChef.getThumbnail());
            }

            TextView farmText = (TextView) getView().findViewById(R.id.farmText);
            TextView dishText = (TextView) getView().findViewById(R.id.dishText);
            TextView chefText = (TextView) getView().findViewById(R.id.chefText);

            farmText.setText(mChef.getFarm());
            dishText.setText(mChef.getDish());
            chefText.setText(mChef.getCook());

        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        String chefId = getArguments().getString("ID");

        ChefsStore.getChefById(activity, chefId, this);
        AnalyticsHelper.sendScreenView(getActivity(), DetailsFragment.class);
        getActivity().invalidateOptionsMenu();
    }

    @Override
    public void onPause() {
        super.onPause();
        getActivity().invalidateOptionsMenu();
    }



    @Override
    public void onResume() {
        super.onResume();
        mFadingHelper.initActionBar(getActivity());
        getActivity().invalidateOptionsMenu();
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);

        if (!isVisible()) {
            getActivity().getActionBar().setBackgroundDrawable(getActivity().getResources().getDrawable(R.drawable.ab_solid_diginpassport));
            getActivity().getActionBar().setTitle(mOldTitle);
        }
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.details_button_favorite:
                setFavorite();
                break;
            case R.id.details_button_yelp:
                goToYelp();
                break;
            case R.id.details_button_website:
                goToWeb();
                break;
        }

    }

    private void goToWeb() {
        if (mChef.getWebsite() == null || mChef.getWebsite().equals("")) {
            Toast.makeText(getActivity(), "We are sorry, this participant does not have a website.", Toast.LENGTH_SHORT).show();
            return;
        }
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(mChef.getWebsite()));
        AnalyticsHelper.sendEvent(getActivity(), "Details_Item_Click", "Web", mChef.getName());
        startActivity(browserIntent);
    }

    private void setFavorite() {
        boolean isFavorited = FavoritesStore.contains(getActivity(), mChef);
        if (isFavorited) {
            FavoritesStore.removeFavorite(getActivity(), mChef);
            favoriteButton.setText("Add to favorites");
            AnalyticsHelper.sendEvent(getActivity(), "Details_Item_Click", "Unfavorite", mChef.getName());
        } else {
            FavoritesStore.storeFavorite(getActivity(), mChef);
            favoriteButton.setText("Unfavorite");
            AnalyticsHelper.sendEvent(getActivity(), "Details_Item_Click", "Favorite", mChef.getName());
        }
    }

    private void goToYelp() {
        if (mChef.getYelpURL() == null || mChef.getYelpURL().equals("")) {
            Toast.makeText(getActivity(), "This participant doesn't appear to have a Yelp page", Toast.LENGTH_SHORT).show();
            return;
        }
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(mChef.getYelpURL()));
        AnalyticsHelper.sendEvent(getActivity(), "Details_Item_Click", "Yelp", mChef.getName());
        startActivity(browserIntent);
    }

    @Override
    public void onComplete(Chef chef) {
        Logger.log(DetailsFragment.class, "Chef found for id");
        DetailsFragment.this.mChef = chef;
        tryFillData();
    }
}
