package com.github.digin.android.bitmap;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.github.digin.android.ImageHelper;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public final class BitmapDownloader {

    public static Bitmap getBitmap(String url) {
        //url = "http://lorempixel.com/300/300/";

        HttpURLConnection httpURLConnection = null;
        try {
            httpURLConnection = (HttpURLConnection) new URL(url).openConnection();
            final InputStream is = httpURLConnection.getInputStream();
            Bitmap gravitar = BitmapFactory.decodeStream(is, null, new BitmapFactory.Options());
            return ImageHelper.getRoundedCornerBitmap(gravitar, 500);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            httpURLConnection.disconnect();
        }
        return null;
    }
}
