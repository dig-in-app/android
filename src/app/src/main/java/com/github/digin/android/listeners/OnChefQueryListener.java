package com.github.digin.android.listeners;

import com.github.digin.android.models.Chef;

import java.util.List;

/**
 *  Called when the ChefsStore completes a query for all chefs.
 *
 *  This is called on the UI thread.
 *
 *  Created by mike on 7/11/14.
 */
public interface OnChefQueryListener {

    public void onComplete(List<Chef> chefs);

}
