package com.github.digin.android.bitmap;

import android.graphics.Bitmap;
import android.graphics.Color;

/**
 * Created by david on 8/11/14.
 */
public class BitmapUtils {

    public static int getAverageColor(Bitmap bitmap) {

//        long redBucket = 0;
//        long greenBucket = 0;
//        long blueBucket = 0;
//        long pixelCount = 0;
//
//
//        int intArray[] = new int[bitmap.getWidth()*bitmap.getHeight()];
//        bitmap.getPixels(intArray, 0, bitmap.getWidth(), 0, 0, bitmap.getWidth(), bitmap.getHeight());
//
//
//        for (int i = 0; i < intArray.length; i++)
//        {
//            int colorhex = intArray[i];
//
//            pixelCount++;
//            redBucket += Color.red(colorhex);
//            greenBucket += Color.green(colorhex);
//            blueBucket += Color.blue(colorhex);
//        }
//
//        int averageColor = Color.rgb((int) (redBucket / pixelCount),
//                (int) (greenBucket / pixelCount),
//                (int) (blueBucket / pixelCount));
//
//        return averageColor;
        return Color.GRAY;
    }
}
