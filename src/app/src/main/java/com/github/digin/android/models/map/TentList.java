package com.github.digin.android.models.map;

import java.util.List;

/**
 * Created by mike on 7/21/14.
 */
public class TentList {

    private List<Tent> tents;

    public List<Tent> getTents() {
        return tents;
    }

    public void setTents(List<Tent> tents) {
        this.tents = tents;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TentList tentList = (TentList) o;

        if (tents != null ? !tents.equals(tentList.tents) : tentList.tents != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return tents != null ? tents.hashCode() : 0;
    }

}
