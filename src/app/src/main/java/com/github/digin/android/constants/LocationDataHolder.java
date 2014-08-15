package com.github.digin.android.constants;

/**
 * Created by david on 8/14/14.
 */
public class LocationDataHolder {

    public Station[] getStations() {
        return stations;
    }

    private Station[] stations;

    public String getName() {
        return name;
    }

    private String name;

    public LocationDataHolder(String s, Station... stations) {
        name = s;
        this.stations = stations;
    }
}
