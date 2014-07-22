package com.github.digin.android.models.map;

import com.google.android.gms.maps.model.LatLng;

import java.util.LinkedList;
import java.util.List;

/**
 *  A tent at the event. I'm practically Dr. Seuss.
 *  Created by mike on 7/18/14.
 */
public class Tent {

    private String name;
    private LatLng center;
    private List<LatLng> bounds;

    public Tent() {
        this.bounds = new LinkedList<LatLng>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LatLng getCenter() {
        return center;
    }

    public void setCenter(LatLng center) {
        this.center = center;
    }

    public List<LatLng> getBounds() {
        return bounds;
    }

    public void setBounds(List<LatLng> bounds) {
        this.bounds = bounds;
    }

}
