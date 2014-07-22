package com.github.digin.android.models.map;

import com.google.android.gms.maps.model.LatLng;

import java.util.List;

/**
 * Created by mike on 7/21/14.
 */
public class LocationList {

    private List<LatLng> locations;

    public List<LatLng> getLocations() {
        return locations;
    }

    public void setLocations(List<LatLng> locations) {
        this.locations = locations;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LocationList that = (LocationList) o;

        if (locations != null ? !locations.equals(that.locations) : that.locations != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        return locations != null ? locations.hashCode() : 0;
    }

}
