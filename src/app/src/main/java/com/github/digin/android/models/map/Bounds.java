package com.github.digin.android.models.map;

import java.util.LinkedList;
import java.util.List;

/**
 * Bounds around a location.
 * Created by mike on 8/10/14.
 */
public class Bounds {

    private String name;
    private List<BoundPoint> bounds;

    public Bounds() {
        this.bounds = new LinkedList<BoundPoint>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<BoundPoint> getBounds() {
        return bounds;
    }

    public void setBounds(List<BoundPoint> bounds) {
        this.bounds = bounds;
    }

}
