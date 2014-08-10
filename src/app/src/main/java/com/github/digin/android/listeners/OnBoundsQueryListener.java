package com.github.digin.android.listeners;

import com.github.digin.android.models.map.Bounds;

import java.util.List;
import java.util.Map;

/**
 *  DON'T USE THIS ONE. THIS ONE IS FOR THE BACKEND IN
 *  COMMUNICATION WITH PARSE. USE ONBOUNDSRETRIEVALLISTENER
 *  INSTEAD.
 */
public interface OnBoundsQueryListener {
    
    public void onComplete(Map<String, Bounds> bounds);

}
