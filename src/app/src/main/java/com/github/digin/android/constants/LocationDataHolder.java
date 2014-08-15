package com.github.digin.android.constants;

/**
 * Created by david on 8/14/14.
 */
public class LocationDataHolder {

    private Station[] stations;
    private String name;

    public LocationDataHolder(String s, Station... stations) {
        name = s;
        this.stations = stations;
    }

    public Station[] getStations() {
        return stations;
    }

    public String getName() {
        return name;
    }
}
