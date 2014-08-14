package com.github.digin.android.constants;

import android.content.Context;
import android.graphics.Color;

import com.github.digin.android.R;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.GroundOverlayOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Polygon;
import com.google.android.gms.maps.model.PolygonOptions;
import com.google.android.gms.maps.model.PolylineOptions;

import java.util.ArrayList;
import java.util.List;

public class MapOverlayData {

//    private static double[][][] tents = new double[][][] {
////            {
////                    //tent 6
////                    {-86.171367,39.766670},
////                    {-86.171436,39.766620},
////                    {-86.171369,39.766554},
////                    {-86.171286,39.766614}
////            },
////            {
////                    //tent 5
////                    {-86.171353,39.766942},
////                    {-86.171412,39.766894},
////                    {-86.171345,39.766855},
////                    {-86.171294,39.766903}
////            },
////            {
////                    //tent 4
////                    {-86.172348,39.767645},
////                    {-86.172413,39.767601},
////                    {-86.172327,39.767531},
////                    {-86.172257,39.767583}
////            },
////            {
////                    //tent 3
////                    {-86.172147,39.767566},
////                    {-86.172069,39.767515},
////                    {-86.171978,39.767581},
////                    {-86.172059,39.767626}
////            },
////            {
////                    //tent 2
////                    {-86.171651,39.767560},
////                    {-86.171739,39.767500},
////                    {-86.171643,39.767422},
////                    {-86.171549,39.767486}
////            },
////            {
////                    //tent 1
////                    {-86.171300,39.767455},
////                    {-86.171377,39.767401},
////                    {-86.171289,39.767346},
////                    {-86.171211,39.767406}
////            },
////            {
////                    //tent vip
////                    {-86.172211,39.767138},
////                    {-86.172099,39.767142},
////                    {-86.172112,39.766956},
////                    {-86.172217,39.766952}
////            }
//
//    };

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

    public static void addTents(GoogleMap map)
    {
        map.addGroundOverlay(new GroundOverlayOptions()
                .position(new LatLng(39.767381, -86.171280), 19)
                .image(BitmapDescriptorFactory.fromResource(R.drawable.tent_overlay_one)));

        map.addGroundOverlay(new GroundOverlayOptions()
                .position(new LatLng(39.767462, -86.171643), 19)
                .image(BitmapDescriptorFactory.fromResource(R.drawable.tent_overlay_two)));

        map.addGroundOverlay(new GroundOverlayOptions()
                .position(new LatLng(39.767515, -86.172000), 19)
                .image(BitmapDescriptorFactory.fromResource(R.drawable.tent_overlay_three)));

        map.addGroundOverlay(new GroundOverlayOptions()
                .position(new LatLng(39.767601, -86.172340), 19)
                .image(BitmapDescriptorFactory.fromResource(R.drawable.tent_overlay_four)));

        map.addGroundOverlay(new GroundOverlayOptions()
                .position(new LatLng(39.766935, -86.171345), 19)
                .image(BitmapDescriptorFactory.fromResource(R.drawable.tent_overlay_five)));

        map.addGroundOverlay(new GroundOverlayOptions()
                .position(new LatLng(39.766614, -86.171369), 19)
                .image(BitmapDescriptorFactory.fromResource(R.drawable.tent_overlay_six)));

        map.addGroundOverlay(new GroundOverlayOptions()
                .position(new LatLng(39.767056, -86.172162), 13)
                .image(BitmapDescriptorFactory.fromResource(R.drawable.tent_overlay_vip)));

        map.addGroundOverlay(new GroundOverlayOptions()
                .position(new LatLng(39.767281, -86.171907), 42)
                .image(BitmapDescriptorFactory.fromResource(R.drawable.alcohol_overlay_ad)));

        map.addGroundOverlay(new GroundOverlayOptions()
                .position(new LatLng(39.767757, -86.172191), 45)
                .image(BitmapDescriptorFactory.fromResource(R.drawable.alcohol_overlay_be)));

        map.addGroundOverlay(new GroundOverlayOptions()
                .position(new LatLng(39.766772, -86.171546), 15)
                .image(BitmapDescriptorFactory.fromResource(R.drawable.alcohol_overlay_cf)));

        map.addGroundOverlay(new GroundOverlayOptions()
                .position(new LatLng(39.766413, -86.171485), 18)
                .image(BitmapDescriptorFactory.fromResource(R.drawable.bathroom_overlay_a)));

        map.addGroundOverlay(new GroundOverlayOptions()
                .position(new LatLng(39.767445, -86.171055), 10)
                .bearing(8)
                .image(BitmapDescriptorFactory.fromResource(R.drawable.bathroom_overlay_a)));

        map.addGroundOverlay(new GroundOverlayOptions()
                .position(new LatLng(39.767098, -86.170830), 8)
                .image(BitmapDescriptorFactory.fromResource(R.drawable.health)));

        map.addGroundOverlay(new GroundOverlayOptions()
                .position(new LatLng(39.767845, -86.173173), 8)
                .image(BitmapDescriptorFactory.fromResource(R.drawable.music_a)));

        map.addGroundOverlay(new GroundOverlayOptions()
                .position(new LatLng(39.766948, -86.170426), 10).zIndex(1)
                .image(BitmapDescriptorFactory.fromResource(R.drawable.entrance_overlay)));

        map.addGroundOverlay(new GroundOverlayOptions()
                .position(new LatLng(39.767086, -86.170301), 9)
                .image(BitmapDescriptorFactory.fromResource(R.drawable.pp_overlay)));

        map.addGroundOverlay(new GroundOverlayOptions()
                .position(new LatLng(39.766699, -86.170518), 8).zIndex(1)
                .image(BitmapDescriptorFactory.fromResource(R.drawable.vip_entrance_overlay)));

        map.addGroundOverlay(new GroundOverlayOptions()
                .position(new LatLng(39.766593, -86.171053), 10).zIndex(1)
                .image(BitmapDescriptorFactory.fromResource(R.drawable.kd_overlay)));

        map.addGroundOverlay(new GroundOverlayOptions()
                .position(new LatLng(39.767111, -86.170481), 8)
                .image(BitmapDescriptorFactory.fromResource(R.drawable.info_overlay)));

        map.addGroundOverlay(new GroundOverlayOptions()
                .position(new LatLng(39.767572, -86.171327), 15)
                .image(BitmapDescriptorFactory.fromResource(R.drawable.music_b)));




        BitmapDescriptor bitmapDescriptor = BitmapDescriptorFactory.fromResource(R.drawable.food_truck_overlay);

        float delta = .00008f;
        for(int i = 0; i < 7; i++) {
            map.addGroundOverlay(new GroundOverlayOptions()
                    .position(new LatLng(39.767382, -86.173280 + (delta * i)), 6)
                    .bearing(2)
                    .image(bitmapDescriptor));
        }

    }

}
