package com.github.digin.android.factories;

import com.github.digin.android.constants.ParseID;
import com.github.digin.android.exceptions.InvalidClassException;
import com.github.digin.android.logging.Logger;
import com.github.digin.android.models.Chef;
import com.parse.ParseObject;

/**
 *  Factory for creating chef objects in various ways.
 *  Created by mike on 7/11/14.
 */
public abstract class ChefFactory {

    public static ChefBuilder create() {
        return new ChefBuilder();
    }

    public static Chef createFrom(ParseObject parseObject) throws InvalidClassException {

        // Make sure the object passed in is of Parse type Chef
        if (!parseObject.getClassName().equals(ParseID.CLASS_CHEF)) {
            String message = "The ParseObject passed into createFrom() is not of the type Chef.";
            InvalidClassException exception = new InvalidClassException(message);
            Logger.err(ChefFactory.class, message, exception);
            throw exception;
        }

        return create()
                .id(parseObject.getObjectId())
                .created(parseObject.getCreatedAt().getTime())
                .modified(parseObject.getUpdatedAt().getTime())
                .name(parseObject.getString(ParseID.FIELD_NAME))
                .city(parseObject.getString(ParseID.FIELD_CITY))
                .thumbnail(parseObject.getString(ParseID.FIELD_THUMBNAIL))
                .website(parseObject.getString(ParseID.FIELD_WEBSITE))
                .yelp(parseObject.getString(ParseID.FIELD_YELP))
                .category(parseObject.getString(ParseID.CHEF_CATEGORY))
                .ingredient(parseObject.getString(ParseID.CHEF_INGREDIENT))
                .dish(parseObject.getString(ParseID.CHEF_DISH))
                .tent(parseObject.getString(ParseID.CHEF_TENT))
                .build();

    }

}
