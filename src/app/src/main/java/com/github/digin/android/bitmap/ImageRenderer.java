package com.github.digin.android.bitmap;

import android.graphics.Bitmap;

/**
 * Created by david on 4/27/14.
 */
public interface ImageRenderer {

    public Bitmap renderBitmap(int sizePX);

    public String getUniqueImageId();

}
