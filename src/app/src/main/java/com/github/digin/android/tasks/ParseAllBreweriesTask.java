package com.github.digin.android.tasks;

import android.content.Context;
import android.os.AsyncTask;

import com.github.digin.android.listeners.OnBreweryQueryListener;

/**
 * Created by mike on 8/3/14.
 */
public class ParseAllBreweriesTask extends AsyncTask<Void, Void, Void> {

    private Context context;
    private OnBreweryQueryListener listener;

    public ParseAllBreweriesTask(Context context, OnBreweryQueryListener listener) {
        this.context = context;
        this.listener = listener;
    }

    @Override
    protected Void doInBackground(Void... params) {



        return null;
    }

}
