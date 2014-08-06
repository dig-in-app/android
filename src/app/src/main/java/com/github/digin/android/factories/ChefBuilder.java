package com.github.digin.android.factories;

import com.github.digin.android.models.Chef;

/**
 * Created by mike on 7/11/14.
 */
public class ChefBuilder {

    private Chef chef;

    public ChefBuilder() {
        chef = new Chef();
    }

    public Chef build() {
        return chef;
    }

    public ChefBuilder id(String id) {
        chef.setId(id);
        return this;
    }

    public ChefBuilder created(long created) {
        chef.setCreated(created);
        return this;
    }

    public ChefBuilder modified(long updated) {
        chef.setUpdated(updated);
        return this;
    }

    public ChefBuilder name(String name) {
        chef.setName(name);
        return this;
    }

    public ChefBuilder city(String city) {
        chef.setCity(city);
        return this;
    }

    public ChefBuilder website(String website) {
        chef.setWebsite(website);
        return this;
    }

    public ChefBuilder yelp(String yelpURL) {
        chef.setYelpURL(yelpURL);
        return this;
    }

    public ChefBuilder thumbnail(String thumbURL) {
        chef.setThumbnail(thumbURL);
        return this;
    }

    public ChefBuilder category(String category) {
        chef.setCategory(category);
        return this;
    }

    public ChefBuilder ingredient(String ingredient) {
        chef.setIngredient(ingredient);
        return this;
    }

    public ChefBuilder dish(String dish) {
        chef.setDish(dish);
        return this;
    }

    public ChefBuilder tent(String tent) {
        chef.setTent(tent);
        return this;
    }

}
