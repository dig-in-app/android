package com.github.digin.android.fragments;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.github.digin.android.R;
import com.github.digin.android.logging.AnalyticsHelper;
import com.github.digin.android.models.Brewery;
import com.github.digin.android.models.Winery;
import com.github.digin.android.repositories.BreweryStore;
import com.github.digin.android.repositories.FavoritesStore;
import com.github.digin.android.repositories.WineryStore;
import com.loopj.android.image.SmartImageView;

/**
 * This is not actually an extension. They are just similar and I don't have time to pull it.
 */
public class BreweryDetailsFragment extends ParticipantDetailsFragment<Brewery> implements View.OnClickListener {

    public static BreweryDetailsFragment newInstance(Brewery brewery) {
        Bundle b = new Bundle();
        b.putString("ID", brewery.getId());

        BreweryDetailsFragment details = new BreweryDetailsFragment();
        details.setArguments(b);
        return details;
    }


    private Button favoriteButton, yelpButton;
    private Button webButton;


    @Override
    public int getHeaderResource() {
        return R.layout.beer_header_image;
    }


    @Override
    public int getContentResource() {
        return R.layout.participant_content;
    }

    @Override
    public void fillContent() {

        getView().setBackgroundColor(Color.BLACK);

        getActivity().getActionBar().setTitle(getParticipant().getName());

        TextView name = (TextView) getView().findViewById(R.id.nameText);
        name.setText( getParticipant().getName() );

        SmartImageView siv = (SmartImageView) getView().findViewById(R.id.logo);
        siv.setImageUrl( getParticipant().getThumbnail() );

        // prepare buttons

        yelpButton = (Button) getView().findViewById(R.id.details_button_yelp);
        if (getParticipant().getYelpURL() == null || getParticipant().getYelpURL().equals("")) {
            yelpButton.setVisibility(View.GONE);
        } else {
            yelpButton.setOnClickListener(this);
        }

        webButton = (Button) getView().findViewById(R.id.details_button_website);
        webButton.setOnClickListener(this);

        favoriteButton = (Button) getView().findViewById(R.id.details_button_favorite);
        favoriteButton.setOnClickListener(this);

        if (FavoritesStore.contains(getActivity(), getParticipant())) {
            favoriteButton.setText("Unfavorite");
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        AnalyticsHelper.sendScreenView(getActivity(), BreweryDetailsFragment.class, getParticipant().getName());
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

        if (getParticipant().getWebsite() == null || getParticipant().getWebsite().equals("")) {
            Toast.makeText(getActivity(), "We are sorry, this participant does not have a website.", Toast.LENGTH_SHORT).show();
            return;
        }
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(getParticipant().getWebsite()));
        AnalyticsHelper.sendEvent(getActivity(), "Details_Item_Click", "Web", getParticipant().getName());
        startActivity(browserIntent);
    }

    private void setFavorite() {
        boolean isFavorited = FavoritesStore.contains(getActivity(), getParticipant());
        if (isFavorited) {
            FavoritesStore.removeFavorite(getActivity(), getParticipant());
            favoriteButton.setText("Add to favorites");
            AnalyticsHelper.sendEvent(getActivity(), "Details_Item_Click", "Unfavorite", getParticipant().getName());
        } else {
            FavoritesStore.storeFavorite(getActivity(), getParticipant());
            favoriteButton.setText("Unfavorite");
            AnalyticsHelper.sendEvent(getActivity(), "Details_Item_Click", "Favorite", getParticipant().getName());
        }
    }

    private void goToYelp() {
        if (getParticipant().getYelpURL() == null || getParticipant().getYelpURL().equals("")) {
            Toast.makeText(getActivity(), "This participant doesn't appear to have a Yelp page", Toast.LENGTH_SHORT).show();
            return;
        }
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(getParticipant().getYelpURL()));
        AnalyticsHelper.sendEvent(getActivity(), "Details_Item_Click", "Yelp", getParticipant().getName());
        startActivity(browserIntent);
    }

    @Override
    public void queryParticipant(String id) {
        BreweryStore.getBreweryById(getActivity(), id, this);
    }
}
