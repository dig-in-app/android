package com.github.digin.android.models.map;

import com.google.android.gms.maps.model.LatLng;

/**
 * Created by mike on 8/10/14.
 */
public class BoundPoint {

    private int order;
    private LatLng point;

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public LatLng getPoint() {
        return point;
    }

    public void setPoint(LatLng point) {
        this.point = point;
    }

}
