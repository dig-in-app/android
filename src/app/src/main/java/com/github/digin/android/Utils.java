package com.github.digin.android;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by david on 8/10/14.
 */
public class Utils {


    public static void fixForActionBarHeight(Context context, View view) {
        final TypedArray styledAttributes = context.obtainStyledAttributes(
                new int[]{android.R.attr.actionBarSize});
        int actionBarSize = (int) styledAttributes.getDimension(0, 0);
        styledAttributes.recycle();

        if (view.getLayoutParams() instanceof ViewGroup.MarginLayoutParams) {
            ViewGroup.MarginLayoutParams p = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
            p.setMargins(p.leftMargin, actionBarSize, p.rightMargin, p.bottomMargin);
            view.requestLayout();
        }
    }
}
