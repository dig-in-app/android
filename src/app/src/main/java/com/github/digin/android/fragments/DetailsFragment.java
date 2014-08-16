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
public class DetailsFragment extends ParticipantDetailsFragment<Chef> implements View.OnClickListener {

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
    public int getHeaderResource() {
        return R.layout.details_header_image;
    }

    @Override
    public int getContentResource() {
        return R.layout.details_fragment;
    }

    public void fillContent() {
        Chef mChef = getParticipant();

        getView().setBackgroundColor(Color.parseColor("#b48e22")); //Yellowish

        Logger.log(DetailsFragment.class, "Filling data");
        TextView t = (TextView) getView().findViewById(R.id.nameText);
        t.setText(mChef.getName());


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


        // prepare buttons

        yelpButton = (Button) getView().findViewById(R.id.details_button_yelp);
        if (mChef.getYelpURL() == null || mChef.getYelpURL().equals("")) {
            yelpButton.setVisibility(View.GONE);
        } else {
            yelpButton.setOnClickListener(this);
        }

        webButton = (Button) getView().findViewById(R.id.details_button_website);
        webButton.setOnClickListener(this);

        favoriteButton = (Button) getView().findViewById(R.id.details_button_favorite);
        favoriteButton.setOnClickListener(this);

        if (FavoritesStore.contains(getActivity(), mChef)) {
            favoriteButton.setText("Unfavorite");
        }
    }

    @Override
    public void queryParticipant(String id) {
        ChefsStore.getChefById(getActivity(), id, this);
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

        Chef mChef = getParticipant();

        if (mChef.getWebsite() == null || mChef.getWebsite().equals("")) {
            Toast.makeText(getActivity(), "We are sorry, this participant does not have a website.", Toast.LENGTH_SHORT).show();
            return;
        }
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(mChef.getWebsite()));
        AnalyticsHelper.sendEvent(getActivity(), "Details_Item_Click", "Web", mChef.getName());
        startActivity(browserIntent);
    }

    private void setFavorite() {
        Chef mChef = getParticipant();
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
        Chef mChef = getParticipant();
        if (mChef.getYelpURL() == null || mChef.getYelpURL().equals("")) {
            Toast.makeText(getActivity(), "This participant doesn't appear to have a Yelp page", Toast.LENGTH_SHORT).show();
            return;
        }
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(mChef.getYelpURL()));
        AnalyticsHelper.sendEvent(getActivity(), "Details_Item_Click", "Yelp", mChef.getName());
        startActivity(browserIntent);
    }
}
