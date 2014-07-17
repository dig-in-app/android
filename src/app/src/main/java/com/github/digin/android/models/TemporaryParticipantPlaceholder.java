package com.github.digin.android.models;

/**
 * Created by david on 7/16/14.
 */
public class TemporaryParticipantPlaceholder extends Participant {

    String name;

    public TemporaryParticipantPlaceholder(String name) {
        this.name = name;
        setName(name);
    }

    @Override
    public String toString() {
        return name;
    }
}
