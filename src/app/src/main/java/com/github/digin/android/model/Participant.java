package com.github.digin.android.model;

/**
 *  The Participant class contains fields generic to every participant
 *  of the event. Chefs, breweries, etc.
 *  Created by mike on 7/11/14.
 */
public class Participant {

    private String name;
    private String city;
    private String website;
    private String yelpURL;
    private String thumbnail;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getYelpURL() {
        return yelpURL;
    }

    public void setYelpURL(String yelpURL) {
        this.yelpURL = yelpURL;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Participant that = (Participant) o;

        if (city != null ? !city.equals(that.city) : that.city != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (thumbnail != null ? !thumbnail.equals(that.thumbnail) : that.thumbnail != null)
            return false;
        if (website != null ? !website.equals(that.website) : that.website != null) return false;
        if (yelpURL != null ? !yelpURL.equals(that.yelpURL) : that.yelpURL != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (city != null ? city.hashCode() : 0);
        result = 31 * result + (website != null ? website.hashCode() : 0);
        result = 31 * result + (yelpURL != null ? yelpURL.hashCode() : 0);
        result = 31 * result + (thumbnail != null ? thumbnail.hashCode() : 0);
        return result;
    }

}
