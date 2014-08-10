package com.github.digin.android.listeners;

import com.github.digin.android.models.Winery;

import java.util.List;

/**
 * Created by mike on 8/10/14.
 */
public interface OnWineryQueryListener {

    public void onComplete(List<Winery> wineries);

}
