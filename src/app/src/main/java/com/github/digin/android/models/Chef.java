package com.github.digin.android.models;

/**
 *  A chef as defined by the official digin website is generally just a restaurant.
 *  This model directly corresponds to our backing Chef class on Parse.
 *  Created by mike on 7/11/14.
 */
public class Chef extends Participant {

    private String category;

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Chef chef = (Chef) o;

        if (category != null ? !category.equals(chef.category) : chef.category != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (category != null ? category.hashCode() : 0);
        return result;
    }

}
