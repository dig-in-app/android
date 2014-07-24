package com.github.digin.android.repositories;

import android.content.Context;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.digin.android.R;
import com.github.digin.android.logging.Logger;
import com.github.digin.android.models.map.Location;
import com.github.digin.android.models.map.LocationList;
import com.github.digin.android.models.map.Tent;
import com.github.digin.android.models.map.TentList;
import com.google.android.gms.maps.model.LatLng;

import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;

/**
 *  Parses and returns information related to metadata locations of things to be placed
 *  on maps, such as bounds of the park and locations of tents.
 *
 *  Created by mike on 7/18/14.
 */
public class MapLocationStore {

    private Context context;
    private ObjectMapper mapper;

    private List<LatLng> mapBounds;
    private List<Tent> tents;

    public MapLocationStore(Context c) {
        this.context = c;
        this.mapper = new ObjectMapper();
        mapBounds = new LinkedList<LatLng>();
        tents = new LinkedList<Tent>();
        init();
    }

    public void init() {

        InputStream isParkBounds = context.getResources().openRawResource(R.raw.parkbounds);
        InputStream isTents = context.getResources().openRawResource(R.raw.tents);

        try {
            LocationList bounds = mapper.readValue(isParkBounds, LocationList.class);
            TentList tentsO = mapper.readValue(isTents, TentList.class);

            for (Location l : bounds.getLocations()) {
                mapBounds.add(new LatLng(l.getLatitude(), l.getLongitude()));
            }

            for (Tent t : tentsO.getTents()) {
                tents.add(t);
            }
            
        } catch (JsonParseException e) {
            Logger.err(MapLocationStore.class, "Error parsing json resource files", e);
        } catch (JsonMappingException e) {
            Logger.err(MapLocationStore.class, "Error mapping json file to object", e);
        } catch (IOException e) {
            Logger.err(MapLocationStore.class, "Error reading json resource file", e);
        }

    }

    public List<LatLng> getMapBounds() {
        return mapBounds;
    }

    public List<Tent> getTents() {
        return tents;
    }

}
