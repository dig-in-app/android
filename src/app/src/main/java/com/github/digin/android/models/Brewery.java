package com.github.digin.android.models;

/**
 * Created by mike on 8/10/14.
 */
public class Brewery extends Participant {

    private String tent;

    public String getTent() {
        return tent;
    }

    public void setTent(String tent) {
        this.tent = tent;
    }

    @Override
    public String toString() {
        return this.getName();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Brewery brewery = (Brewery) o;

        if (tent != null ? !tent.equals(brewery.tent) : brewery.tent != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (tent != null ? tent.hashCode() : 0);
        return result;
    }

}
