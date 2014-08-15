package com.github.digin.android.factories;

import com.github.digin.android.constants.ParseID;
import com.github.digin.android.exceptions.InvalidClassException;
import com.github.digin.android.logging.Logger;
import com.github.digin.android.models.Chef;
import com.parse.ParseObject;

/**
 * Factory for creating chef objects in various ways.
 * Created by mike on 7/11/14.
 */
public abstract class ChefFactory {

    public static Chef create() {
        return new Chef();
    }

    public static Chef createFrom(ParseObject parseObject) {

        // Make sure the object passed in is of Parse type Chef
        if (!parseObject.getClassName().equals(ParseID.CLASS_CHEF)) {
            String message = "The ParseObject passed into createFrom() is not of the type Chef.";
            InvalidClassException exception = new InvalidClassException(message);
            Logger.err(ChefFactory.class, message, exception);
            throw exception;
        }

        Chef chef = create();

        chef.setId(parseObject.getObjectId());
        chef.setCreated(parseObject.getCreatedAt().getTime());
        chef.setUpdated(parseObject.getUpdatedAt().getTime());

        chef.setName(parseObject.getString(ParseID.F_CHEF_RESTAURANT));
        chef.setThumbnail(parseObject.getString(ParseID.F_CHEF_THUMB));
        chef.setWebsite(parseObject.getString(ParseID.F_CHEF_WEB));
        chef.setYelpURL(parseObject.getString(ParseID.F_CHEF_YELP));
        chef.setCity(parseObject.getString(ParseID.F_CHEF_CITY));
        chef.setDish(parseObject.getString(ParseID.F_CHEF_DISH));
        chef.setCook(parseObject.getString(ParseID.F_CHEF_CHEFNAME));
        chef.setFarm(parseObject.getString(ParseID.F_CHEF_FARM));
        chef.setTent(parseObject.getString(ParseID.F_CHEF_TENT));

        return chef;

    }

}
