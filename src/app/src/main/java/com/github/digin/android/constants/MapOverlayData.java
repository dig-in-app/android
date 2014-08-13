package com.github.digin.android.constants;

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
            {-86.173437,39.767480},
            {-86.172997,39.767472},
            {-86.172662,39.767478},
            {-86.172627,39.767465},
            {-86.172606,39.767465},
            {-86.172587,39.767465},
            {-86.172560,39.767457},
            {-86.172512,39.767472},
            {-86.172493,39.767509},
            {-86.172493,39.767552},
            {-86.172536,39.767583},
            {-86.172472,39.767851},
            {-86.171841,39.767756},
            {-86.171849,39.767727},
            {-86.171514,39.767672},
            {-86.171517,39.767647},
            {-86.171278,39.767614},
            {-86.171211,39.767595},
            {-86.171053,39.767571},
            {-86.171037,39.767560},
            {-86.171029,39.767546},
            {-86.170841,39.767511},
            {-86.170819,39.767494},
            {-86.170814,39.767484},
            {-86.170280,39.767385},
            {-86.170366,39.767140},
            {-86.170519,39.766639},
            {-86.171055,39.766635},
            {-86.171088,39.766331},
            {-86.171975,39.766360},
            {-86.171973,39.766754},
            {-86.172391,39.766775},
            {-86.172346,39.766950},
            {-86.172552,39.766993},
            {-86.172673,39.767090},
            {-86.172673,39.767127},
            {-86.172673,39.767166},
            {-86.172692,39.767201},
            {-86.172716,39.767245},
            {-86.172748,39.767276},
            {-86.172777,39.767296},
            {-86.172834,39.767311},
            {-86.172925,39.767323},
            {-86.173330,39.767333},
            {-86.173437,39.767480}
    };

    public static void addGate(GoogleMap map) {
        List<LatLng> gateList = new ArrayList<LatLng>();
        for(double[] coord : gate) {
            gateList.add(new LatLng(coord[1], coord[0]));
        }
        map.addPolyline(new PolylineOptions()
                .addAll(gateList)
                .color(Color.RED));
    }

    public static void addTents(GoogleMap map)
    {
//        List<LatLng> tentPts;
//        for(double[][] tent : tents) {
//            tentPts = new ArrayList<LatLng>();
//            for(double[] coord : tent) {
//                tentPts.add(new LatLng(coord[1], coord[0]));
//            }
//            map.addPolygon(new PolygonOptions()
//                .addAll(tentPts)
//                .strokeColor(Color.BLACK)
//                .fillColor(Color.WHITE));
//        }

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

//        map.addGroundOverlay(new GroundOverlayOptions()
//                .position(new LatLng(39.767098, -86.170830), 8)
//                .image(BitmapDescriptorFactory.fromResource(R.drawable.health)));


        BitmapDescriptor bitmapDescriptor = BitmapDescriptorFactory.fromResource(R.drawable.food_truck_overlay);

        float delta = .0001f;
        for(int i = 0; i < 7; i++) {
            map.addGroundOverlay(new GroundOverlayOptions()
                    .position(new LatLng(39.767362, -86.173280 + (delta * i)), 6)
                    .bearing(2)
                    .image(bitmapDescriptor));
        }

    }

}
