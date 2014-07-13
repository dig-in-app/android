package com.github.digin.android;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;


public class BoundedMapFragment extends MapFragment {

    private final LatLngBounds BOUNDS = new LatLngBounds(new LatLng(39.764251, -86.174382), new LatLng(39.769694, -86.166399));
    private final int MAX_ZOOM = 18;
    private final int MIN_ZOOM = 15;

    private GoogleMap mMap = getMap();
    private OverscrollHandler mOverscrollHandler = new OverscrollHandler();
    private Context mContext;

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mContext = getActivity();
        mMap = getMap();
        CameraUpdate upd = CameraUpdateFactory.newLatLngZoom(new LatLng(39.766639, -86.171351), 17);
        mMap.moveCamera(upd);
        mOverscrollHandler.sendEmptyMessageDelayed(0,100);


    }

    /**
     * Returns the correction for Lat and Lng if camera is trying to get outside of visible map
     * @param point Current camera bounds
     * @return Latitude and Longitude corrections to get back into bounds.
     */
    private LatLng getLatLngCorrection(LatLng point) {

        double lng, lat;
        lng = point.longitude;
        lat = point.latitude;
        if (point.longitude < BOUNDS.southwest.longitude)
            lng = BOUNDS.southwest.longitude;
        else if (point.longitude > BOUNDS.northeast.longitude)
            lng = BOUNDS.northeast.longitude;

        if (point.latitude < BOUNDS.southwest.latitude)
            lat = BOUNDS.southwest.latitude;
        else if (point.latitude > BOUNDS.northeast.latitude)
            lat = BOUNDS.northeast.latitude;

        return new LatLng(lat, lng);
    }


   /**
    * Bounds the user to the overlay.
    */
    private class OverscrollHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            CameraPosition position = mMap.getCameraPosition();
            float zoom = 0;
            if(position.zoom < MIN_ZOOM) zoom = MIN_ZOOM;
            if(position.zoom > MAX_ZOOM) zoom = MAX_ZOOM;
            LatLng correction = getLatLngCorrection(position.target);

            if(zoom != 0 || correction.latitude != 0 || correction.longitude != 0) {
                zoom = (zoom==0)?position.zoom:zoom;
                CameraPosition newPosition = new CameraPosition(correction, zoom, position.tilt, position.bearing);
                CameraUpdate update = CameraUpdateFactory.newCameraPosition(newPosition);
                mMap.moveCamera(update);
            }

            sendEmptyMessageDelayed(0,100);
        }
    }
}
