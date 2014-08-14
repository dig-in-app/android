package com.github.digin.android.constants;

import android.content.Context;
import android.graphics.Color;

import com.github.digin.android.R;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.GroundOverlay;
import com.google.android.gms.maps.model.GroundOverlayOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Polygon;
import com.google.android.gms.maps.model.PolygonOptions;
import com.google.android.gms.maps.model.PolylineOptions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapOverlayData {

    private static double[][] gate = new double[][] {
            {39.767502, -86.173003},
            {39.767507, -86.173424},
            {39.767356, -86.173333},
            {39.767348, -86.172901},
            {39.767333, -86.172874},
            {39.767302, -86.172802},
            {39.767237, -86.172759},
            {39.767160, -86.172732},
            {39.767076, -86.172622},
            {39.766952, -86.172372},
            {39.766775, -86.172434},
            {39.766762, -86.171959},
            {39.766368, -86.171954},
            {39.766341, -86.171085},
            {39.766657, -86.171069},
            {39.766645, -86.170511},
            {39.767158, -86.170371},
            {39.767164, -86.170275},
            {39.767394, -86.170245},
            {39.767469, -86.170822},
            {39.767496, -86.170854},
            {39.767533, -86.171029},
            {39.767558, -86.171077},
            {39.767585, -86.171227},
            {39.767630, -86.171216},
            {39.767667, -86.171452},
            {39.767700, -86.171479},
            {39.767752, -86.171825},
            {39.767781, -86.171820},
            {39.767878, -86.172456},
            {39.767818, -86.172498},
            {39.767964, -86.173295},
            {39.767886, -86.173400},
            {39.767511, -86.173019},
            {39.767502, -86.173003}
    };

    public static void addGate(Context c, GoogleMap map) {
        List<LatLng> gateList = new ArrayList<LatLng>();
        for(double[] coord : gate) {
            gateList.add(new LatLng(coord[0], coord[1]));
        }
        map.addPolyline(new PolylineOptions()
                .addAll(gateList)
                .color(c.getResources().getColor(R.color.digin_orange))).setZIndex(0);
    }

    private static Map<GroundOverlay, String> overlays = new HashMap<GroundOverlay, String>();

    public static void addTents(GoogleMap map)
    {
        overlays.put(map.addGroundOverlay(new GroundOverlayOptions()
                .position(new LatLng(39.767381, -86.171280), 19)
                .image(BitmapDescriptorFactory.fromResource(R.drawable.tent_overlay_one))), "Tent 1");

        overlays.put(map.addGroundOverlay(new GroundOverlayOptions()
                .position(new LatLng(39.767462, -86.171643), 19)
                .image(BitmapDescriptorFactory.fromResource(R.drawable.tent_overlay_two))), "Tent 2");

        overlays.put(map.addGroundOverlay(new GroundOverlayOptions()
                .position(new LatLng(39.767515, -86.172000), 19)
                .image(BitmapDescriptorFactory.fromResource(R.drawable.tent_overlay_three))), "Tent 3");

        overlays.put(map.addGroundOverlay(new GroundOverlayOptions()
                .position(new LatLng(39.767601, -86.172340), 19)
                .image(BitmapDescriptorFactory.fromResource(R.drawable.tent_overlay_four))), "Tent 4");

        overlays.put(map.addGroundOverlay(new GroundOverlayOptions()
                .position(new LatLng(39.766935, -86.171345), 19)
                .image(BitmapDescriptorFactory.fromResource(R.drawable.tent_overlay_five))), "Tent 5");

        overlays.put(map.addGroundOverlay(new GroundOverlayOptions()
                .position(new LatLng(39.766614, -86.171369), 19)
                .image(BitmapDescriptorFactory.fromResource(R.drawable.tent_overlay_six))), "Tent 6");

        overlays.put(map.addGroundOverlay(new GroundOverlayOptions()
                .position(new LatLng(39.767056, -86.172162), 13)
                .image(BitmapDescriptorFactory.fromResource(R.drawable.tent_overlay_vip))), "VIP Tent");

        overlays.put(map.addGroundOverlay(new GroundOverlayOptions()
                .position(new LatLng(39.767281, -86.171907), 42)
                .image(BitmapDescriptorFactory.fromResource(R.drawable.alcohol_overlay_ad))), "Beer A & Wine D");

        overlays.put(map.addGroundOverlay(new GroundOverlayOptions()
                .position(new LatLng(39.767757, -86.172191), 45)
                .image(BitmapDescriptorFactory.fromResource(R.drawable.alcohol_overlay_be))), "Beer B & Wine E");

        overlays.put(map.addGroundOverlay(new GroundOverlayOptions()
                .position(new LatLng(39.766772, -86.171546), 15)
                .image(BitmapDescriptorFactory.fromResource(R.drawable.alcohol_overlay_cf))), "Beer C & Wine F");

        overlays.put(map.addGroundOverlay(new GroundOverlayOptions()
                .position(new LatLng(39.766413, -86.171485), 18)
                .image(BitmapDescriptorFactory.fromResource(R.drawable.bathroom_overlay_a))), "Restrooms");

        overlays.put(map.addGroundOverlay(new GroundOverlayOptions()
                .position(new LatLng(39.767445, -86.171055), 10)
                .bearing(8)
                .image(BitmapDescriptorFactory.fromResource(R.drawable.bathroom_overlay_a))), "Restrooms");

        overlays.put(map.addGroundOverlay(new GroundOverlayOptions()
                .position(new LatLng(39.767098, -86.170830), 8)
                .image(BitmapDescriptorFactory.fromResource(R.drawable.health))), "First Aid");

        overlays.put(map.addGroundOverlay(new GroundOverlayOptions()
                .position(new LatLng(39.767845, -86.173173), 8)
                .image(BitmapDescriptorFactory.fromResource(R.drawable.music_a))), "Music");

        overlays.put(map.addGroundOverlay(new GroundOverlayOptions()
                .position(new LatLng(39.766948, -86.170426), 10).zIndex(1)
                .image(BitmapDescriptorFactory.fromResource(R.drawable.entrance_overlay))), "Main Entrance");

        overlays.put(map.addGroundOverlay(new GroundOverlayOptions()
                .position(new LatLng(39.767086, -86.170301), 9)
                .image(BitmapDescriptorFactory.fromResource(R.drawable.pp_overlay))), "Pedal & Park");

        overlays.put(map.addGroundOverlay(new GroundOverlayOptions()
                .position(new LatLng(39.766699, -86.170518), 8).zIndex(1)
                .image(BitmapDescriptorFactory.fromResource(R.drawable.vip_entrance_overlay))), "VIP Entrance");

        overlays.put(map.addGroundOverlay(new GroundOverlayOptions()
                .position(new LatLng(39.766593, -86.171053), 10).zIndex(1)
                .image(BitmapDescriptorFactory.fromResource(R.drawable.kd_overlay))), "Kitchen Demos");

        overlays.put(map.addGroundOverlay(new GroundOverlayOptions()
                .position(new LatLng(39.767101, -86.170491), 8)
                .image(BitmapDescriptorFactory.fromResource(R.drawable.info_overlay))), "Information");

        overlays.put(map.addGroundOverlay(new GroundOverlayOptions()
                .position(new LatLng(39.767582, -86.171377), 15)
                .image(BitmapDescriptorFactory.fromResource(R.drawable.music_b))), "Music");

        BitmapDescriptor bitmapDescriptor = BitmapDescriptorFactory.fromResource(R.drawable.food_truck_overlay);

        float delta = .00008f;
        for(int i = 0; i < 7; i++) {
            overlays.put(map.addGroundOverlay(new GroundOverlayOptions()
                    .position(new LatLng(39.767382, -86.173280 + (delta * i)), 6)
                    .bearing(2)
                    .image(bitmapDescriptor)), "Food Trucks");
        }

    }

    public static String getTitleForClick(LatLng location) {
        for(GroundOverlay go : overlays.keySet()) {
            LatLngBounds bounds = go.getBounds();

            if(bounds.contains(location)) {
                return overlays.get(go);
            }
        }
        return null;
    }

}
