package com.github.digin.android.tasks;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Handler;

import com.github.digin.android.constants.ParseID;
import com.github.digin.android.exceptions.InvalidClassException;
import com.github.digin.android.factories.BreweryFactory;
import com.github.digin.android.factories.ChefFactory;
import com.github.digin.android.listeners.OnBreweryQueryListener;
import com.github.digin.android.logging.Logger;
import com.github.digin.android.models.Brewery;
import com.github.digin.android.models.Chef;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.LinkedList;
import java.util.List;

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
        Logger.log(ParseAllBreweriesTask.class, "Starting parse sync for brewery data.");

        // Create a parse query for the entire chefs table
        ParseQuery<ParseObject> query = ParseQuery.getQuery(ParseID.CLASS_BREWERY);

        // Execute the query
        List<ParseObject> queryResult = null;
        try {
            queryResult = query.find();

        } catch (ParseException e) {
            Logger.err(ParseAllBreweriesTask.class, "An error was thrown during a Parse query for all breweries", e);

            if (listener != null) {
                listener.onComplete(null);
            }

            throw new RuntimeException();
        }

        // Convert each parse object into our own Brewery data model
        List<Brewery> breweries = new LinkedList<Brewery>();
        for (ParseObject pObject : queryResult) {
            breweries.add(BreweryFactory.createFrom(pObject));
        }

        // Alert the listener
        if (listener != null) {
            alert(breweries);
        }

        return null;
    }

    /** Alerts the listener on the UI thread that execution has completed */
    private void alert(final List<Brewery> breweries) {
        if (listener == null) { return; }

        Handler handler = new Handler(context.getMainLooper());
        handler.post(new Runnable() {
            public void run() {
                listener.onComplete(breweries);
            }
        });

    }

}
