package com.github.digin.android.factories;

import com.github.digin.android.constants.ParseID;
import com.github.digin.android.exceptions.InvalidClassException;
import com.github.digin.android.logging.Logger;
import com.github.digin.android.models.Brewery;
import com.parse.ParseObject;

/**
 * Created by mike on 8/10/14.
 */
public abstract class BreweryFactory {

    public static Brewery create() {
        return new Brewery();
    }

    public static Brewery createFrom(ParseObject parseObject) {

        // Make sure the object passed in is of Parse type Brewery
        if (!parseObject.getClassName().equals(ParseID.CLASS_BREWERY)) {
            String message = "The ParseObject passed into createFrom() is not of the type Brewery.";
            InvalidClassException exception = new InvalidClassException(message);
            Logger.err(ChefFactory.class, message, exception);
            throw exception;
        }

        Brewery brewery = create();

        brewery.setId(parseObject.getObjectId());
        brewery.setCreated(parseObject.getCreatedAt().getTime());
        brewery.setUpdated(parseObject.getUpdatedAt().getTime());

        brewery.setName(parseObject.getString(ParseID.F_BREW_NAME));
        brewery.setCity(parseObject.getString(ParseID.F_BREW_CITY));
        brewery.setThumbnail(parseObject.getString(ParseID.F_BREW_THUMB));
        brewery.setWebsite(parseObject.getString(ParseID.F_BREW_WEB));
        brewery.setYelpURL(parseObject.getString(ParseID.F_BREW_YELP));
        brewery.setTent(parseObject.getString(ParseID.F_BREW_TENT));

        return brewery;

    }

}
