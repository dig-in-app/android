package com.github.digin.android.listeners;

import com.github.digin.android.models.Brewery;
import com.github.digin.android.models.Participant;

import java.util.List;

/**
 * Created by mike on 8/3/14.
 */
public interface OnBreweryQueryListener {

    public void onComplete(List<Brewery> breweries);

}
