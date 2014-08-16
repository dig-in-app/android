package com.github.digin.android.tasks;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Handler;

import com.github.digin.android.constants.ParseID;
import com.github.digin.android.factories.WineryFactory;
import com.github.digin.android.listeners.OnParticipantQueryListener;
import com.github.digin.android.logging.Logger;
import com.github.digin.android.models.Winery;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by mike on 8/10/14.
 */
public class ParseAllWineriesTask extends AsyncTask<Void, Void, Void> {

    private Context context;
    private OnParticipantQueryListener<Winery> listener;

    public ParseAllWineriesTask(Context context, OnParticipantQueryListener<Winery> listener) {
        this.context = context;
        this.listener = listener;
    }

    @Override
    protected Void doInBackground(Void... params) {
        Logger.log(ParseAllChefsTask.class, "Starting background download of all wineries from Parse");

        // Create a parse query for the entire wineries table
        ParseQuery<ParseObject> query = ParseQuery.getQuery(ParseID.CLASS_WINERY);

        // Execute the query
        List<ParseObject> queryResult = null;
        try {
            queryResult = query.find();

        } catch (ParseException e) {
            Logger.err(ParseAllChefsTask.class, "An error was thrown during a Parse query for all wineries", e);

            if (listener != null) {
                listener.onComplete(null);
            }
            return null;
        }

        // Convert each parse object into our own Chef data model
        List<Winery> wineries = new LinkedList<Winery>();
        for (ParseObject pObject : queryResult) {
            wineries.add(WineryFactory.createFrom(pObject));
        }

        // Alert the listener
        if (listener != null) {
            alert(wineries);
        }

        return null;
    }

    /**
     * Alerts the listener on the UI thread that execution has completed
     */
    private void alert(final List<Winery> wineries) {
        if (listener == null) {
            return;
        }

        Handler handler = new Handler(context.getMainLooper());
        handler.post(new Runnable() {
            public void run() {
                listener.onComplete(wineries);
            }
        });

    }

}
