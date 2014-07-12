package com.github.digin.android.models;

/**
 *  Common fields to every single parse backed model we are storing.
 *  Created by mike on 7/11/14.
 */
public class ParseBackedModel {

    private String id;
    private long created;
    private long updated;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public long getCreated() {
        return created;
    }

    public void setCreated(long created) {
        this.created = created;
    }

    public long getUpdated() {
        return updated;
    }

    public void setUpdated(long updated) {
        this.updated = updated;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ParseBackedModel that = (ParseBackedModel) o;

        if (created != that.created) return false;
        if (updated != that.updated) return false;
        if (id != null ? !id.equals(that.id) : that.id != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (int) (created ^ (created >>> 32));
        result = 31 * result + (int) (updated ^ (updated >>> 32));
        return result;
    }

}
