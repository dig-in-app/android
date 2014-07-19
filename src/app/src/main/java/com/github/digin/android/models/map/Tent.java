package com.github.digin.android.models.map;

import java.util.LinkedList;
import java.util.List;

/**
 *  A tent at the event. I'm practically Dr. Seuss.
 *  Created by mike on 7/18/14.
 */
public class Tent {

    private String name;
    private Location center;
    private List<Location> bounds;

    public Tent() {
        this.bounds = new LinkedList<Location>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Location getCenter() {
        return center;
    }

    public void setCenter(Location center) {
        this.center = center;
    }

    public List<Location> getBounds() {
        return bounds;
    }

    public void setBounds(List<Location> bounds) {
        this.bounds = bounds;
    }

}
