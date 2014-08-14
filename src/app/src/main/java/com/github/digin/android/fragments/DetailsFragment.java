package com.github.digin.android.fragments;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.github.digin.android.R;
import com.github.digin.android.bitmap.BitmapUtils;
import com.github.digin.android.listeners.OnSingleChefQuery;
import com.github.digin.android.logging.AnalyticsHelper;
import com.github.digin.android.logging.Logger;
import com.github.digin.android.models.Chef;
import com.github.digin.android.repositories.ChefsStore;
import com.github.digin.android.repositories.FavoritesStore;
import com.loopj.android.image.SmartImageView;
import com.manuelpeinado.fadingactionbar.FadingActionBarHelper;
import com.manuelpeinado.fadingactionbar.view.ObservableScrollView;

import java.util.Random;

/**
 * Created by david on 7/27/14.
 */
public class DetailsFragment extends Fragment implements View.OnClickListener {

    private FadingActionBarHelper mFadingHelper;


    public Chef mChef;
    private CharSequence mOldTitle;
    private Button favoriteButton, yelpButton;

    public static DetailsFragment newInstance(Chef chef) {
        Bundle b = new Bundle();
        b.putString("ID", chef.getId());

        DetailsFragment details = new DetailsFragment();
        details.setArguments(b);
        return details;
    }

    static int[] images = new int[] {
            R.drawable.corn,
            R.drawable.wheel,
            R.drawable.paint,
            R.drawable.pigtattoo,
            R.drawable.img1,
            R.drawable.img2,
            R.drawable.img3,
            R.drawable.img4,
            R.drawable.img5,
            R.drawable.img6,
            R.drawable.img7,
            R.drawable.img8,
            R.drawable.img9,
            R.drawable.img10,
            R.drawable.img12,
            R.drawable.img15
    };

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

        Random r = new Random();
        ((ImageView)view.findViewById(R.id.header)).setImageResource(images[r.nextInt(images.length)]);

        AsyncTask<Void, Void, Integer> getColorTask = new AsyncTask<Void, Void, Integer>() {

            @Override
            protected Integer doInBackground(Void... params) {
                ImageView iv = (ImageView) view.findViewById(R.id.header);
                Bitmap b = ((BitmapDrawable)iv.getDrawable()).getBitmap();
                int color = BitmapUtils.getAverageColor(b);
                return color;
            }

            @Override
            protected void onPostExecute(Integer color) {
                super.onPostExecute(color);
                view.setBackgroundColor(color);

            }
        };
        getColorTask.execute();

        //------ Scroll so image is 25% of screen
        ObservableScrollView scrollview = (ObservableScrollView) view.findViewById(R.id.fab__scroll_view);

        View serrations = view.findViewById(R.id.scrollTop);
        int[] location = new int[2];
        serrations.getLocationOnScreen(location);
        int top = location[1];
        Log.i("Scrolling", "Top of scroll:" + top);

        Display display = getActivity().getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int screenHeight = size.y;
        Log.i("Scrolling", "Screen Height:" + screenHeight);

        Log.i("Scrolling", "Scroll Y:" + scrollview.getScrollY());

        scrollview.scrollBy(0, -50);
        //-------- End scroll code


        tryFillData();
    }

    private void tryFillData() {

        if(mChef != null && getView() != null) {
            Logger.log(getClass(), "Filling data");
            TextView t = (TextView) getView().findViewById(R.id.nameText);
            t.setText(mChef.getName());
            mOldTitle = getActivity().getActionBar().getTitle();

            getActivity().getActionBar().setTitle(mChef.getName());

            SmartImageView siv = (SmartImageView) getView().findViewById(R.id.logo);
            if(mChef.getThumbnail() == null)
                siv.setImageResource(R.drawable.logo);
            else {
                Logger.log(getClass(), mChef.getThumbnail());
                siv.setImageUrl(mChef.getThumbnail());
            }

            TextView farmText = (TextView) getView().findViewById(R.id.farmText);
            TextView dishText = (TextView) getView().findViewById(R.id.dishText);
            TextView chefText = (TextView) getView().findViewById(R.id.chefText);

            farmText.setText( mChef.getFarm() );
            dishText.setText( mChef.getDish() );
            chefText.setText( mChef.getCook() );

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
        AnalyticsHelper.sendScreenView(getActivity(), getClass());

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

        if(isVisible()) {

        } else {
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
        }

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
            Toast.makeText(getActivity(), "This chef doesn't appear to have a Yelp page :(", Toast.LENGTH_SHORT).show();
            return;
        }
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(mChef.getYelpURL()));
        AnalyticsHelper.sendEvent(getActivity(), "Details_Item_Click", "Yelp", mChef.getName());
        startActivity(browserIntent);
    }

}
