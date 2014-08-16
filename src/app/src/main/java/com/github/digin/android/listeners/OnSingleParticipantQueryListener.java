package com.github.digin.android.listeners;

import com.github.digin.android.models.Chef;
import com.github.digin.android.models.Participant;

/**
 * Created by david on 8/16/14.
 */
public interface OnSingleParticipantQueryListener<T extends Participant> {

    public void onComplete(T item);
}
