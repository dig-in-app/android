package com.github.digin.android.tasks;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Handler;

import com.github.digin.android.constants.ParseID;
import com.github.digin.android.factories.ChefFactory;
import com.github.digin.android.listeners.OnBoundsQueryListener;
import com.github.digin.android.logging.Logger;
import com.github.digin.android.models.Chef;
import com.github.digin.android.models.map.BoundPoint;
import com.github.digin.android.models.map.Bounds;
import com.google.android.gms.maps.model.LatLng;
import com.parse.ParseException;
import com.parse.ParseGeoPoint;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by mike on 8/10/14.
 */
public class ParseAllBoundsTask extends AsyncTask<Void, Void, Void> {

    private Context context;
    private OnBoundsQueryListener listener;

    public ParseAllBoundsTask(Context context, OnBoundsQueryListener listener) {
        this.context = context;
        this.listener = listener;
    }

    @Override
    protected Void doInBackground(Void... params) {
        Logger.log(ParseAllChefsTask.class, "Starting background download of all bounds data from Parse");

        // Create a parse query for the entire chefs table
        ParseQuery<ParseObject> query = ParseQuery.getQuery(ParseID.CLASS_BOUNDS);

        // Execute the query
        List<ParseObject> queryResult = null;
        try {
            queryResult = query.find();

        } catch (ParseException e) {
            Logger.err(ParseAllChefsTask.class, "An error was thrown during a Parse query for all bounds", e);

            if (listener != null) {
                listener.onComplete(null);
            }
            return null;
        }

        Map<String, Bounds> map = new HashMap<String, Bounds>();

        // Convert all the parse objects to bound points
        for (ParseObject parseObject : queryResult) {

            String name = parseObject.getString(ParseID.F_BOUNDS_NAME);

            Bounds bounds = map.get(name);
            if (bounds == null) {
                bounds = new Bounds();
                bounds.setName(name);
                map.put(name, bounds);
            }

            BoundPoint point = new BoundPoint();
            point.setOrder(parseObject.getInt(ParseID.F_BOUNDS_ORDER));

            ParseGeoPoint pgPoint = parseObject.getParseGeoPoint(ParseID.F_BOUNDS_LOC);
            LatLng latLng = new LatLng(pgPoint.getLatitude(), pgPoint.getLongitude());
            point.setPoint(latLng);

            bounds.getBounds().add(point);

        }

        // Order each of the bounds lists in the map
        for (String key : map.keySet()) {

            Bounds bounds = map.get(key);

            Collections.sort(bounds.getBounds(), new Comparator<BoundPoint>() {
                public int compare(BoundPoint lhs, BoundPoint rhs) {
                    return lhs.getOrder() - rhs.getOrder();
                }
            });

        }

        // Alert the listener
        if (listener != null) {
            alert(map);
        }

        return null;
    }

    private void alert(final Map<String, Bounds> map) {

        Handler handler = new Handler(context.getMainLooper());

        handler.post(new Runnable() {
            public void run() {
                listener.onComplete(map);
            }
        });


    }

}
