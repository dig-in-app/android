package com.github.digin.android.tasks;

import android.os.AsyncTask;

import com.github.digin.android.constants.ParseID;
import com.github.digin.android.exceptions.InvalidClassException;
import com.github.digin.android.factories.ChefFactory;
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
 *  This should NEVER EVER EVER be called by the presentation layer. Instead, access
 *  methods in ChefsStore. ChefsStore handles caching, which is important to save on API calls.
 *
 *  Created by mike on 7/11/14.
 */
public class ParseAllChefsTask extends AsyncTask<Void, Void, List<Chef>> {

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected List<Chef> doInBackground(Void... params) {
        Logger.log(this.getClass(), "Starting background download of all chefs from Parse");

        // Create a parse query for the entire chefs table
        ParseQuery<ParseObject> query = ParseQuery.getQuery(ParseID.CLASS_CHEF);

        // Execute the query
        List<ParseObject> queryResult = null;
        try {
            queryResult = query.find();

        } catch (ParseException e) {
            Logger.err(this.getClass(), "An error was thrown during a Parse query for all chefs", e);
            return null;
        }

        // Convert each parse object into our own Chef data model
        List<Chef> chefs = new LinkedList<Chef>();
        for (ParseObject pObject : queryResult) {
            try {
                chefs.add(ChefFactory.createFrom(pObject));
            } catch (InvalidClassException e) {}
        }

        return chefs;

    }

}
