package com.github.digin.android.tasks;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Handler;

import com.github.digin.android.constants.ParseID;
import com.github.digin.android.exceptions.InvalidClassException;
import com.github.digin.android.factories.ChefFactory;
import com.github.digin.android.listeners.OnChefQueryListener;
import com.github.digin.android.logging.Logger;
import com.github.digin.android.models.Chef;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.LinkedList;
import java.util.List;

/**
 *  Task which queries parse for a list of all the chefs we are currently
 *  storing.
 *
 *  This shouldn't be called by the presentation layer. Its best if
 *  Created by mike on 7/11/14.
 */
public class ParseAllChefsTask extends AsyncTask<Void, Void, List<Chef>> {

    private Context context;
    private OnChefQueryListener listener;

    public ParseAllChefsTask(Context context, OnChefQueryListener listener) {
        this.context = context;
        this.listener = listener;
    }

    @Override
    protected List<Chef> doInBackground(Void... params) {
        Logger.log(ParseAllChefsTask.class, "Starting background download of all chefs from Parse");

        // Create a parse query for the entire chefs table
        ParseQuery<ParseObject> query = ParseQuery.getQuery(ParseID.CLASS_CHEF);

        // Execute the query
        List<ParseObject> queryResult = null;
        try {
            queryResult = query.find();

        } catch (ParseException e) {
            Logger.err(ParseAllChefsTask.class, "An error was thrown during a Parse query for all chefs", e);
            return null;
        }

        // Convert each parse object into our own Chef data model
        List<Chef> chefs = new LinkedList<Chef>();
        for (ParseObject pObject : queryResult) {
            try {
                chefs.add(ChefFactory.createFrom(pObject));
            } catch (InvalidClassException e) {}
        }

        // Alert the listener
        if (listener != null) {
            alert(chefs);
        }

        return chefs;
    }

    /** Alerts the listener on the UI thread that execution has completed */
    private void alert(final List<Chef> chefs) {
        if (listener == null) { return; }

        Handler handler = new Handler(context.getMainLooper());
        handler.post(new Runnable() {
            public void run() {
                listener.onComplete(chefs);
            }
        });

    }

}
