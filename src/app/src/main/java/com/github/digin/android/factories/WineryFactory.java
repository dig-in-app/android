package com.github.digin.android.factories;

import com.github.digin.android.constants.ParseID;
import com.github.digin.android.exceptions.InvalidClassException;
import com.github.digin.android.logging.Logger;
import com.github.digin.android.models.Winery;
import com.parse.ParseObject;

/**
 * Created by mike on 8/10/14.
 */
public abstract class WineryFactory {

    public static Winery create() {
        return new Winery();
    }

    public static Winery createFrom(ParseObject parseObject) {

        // Make sure the object passed in is of Parse type Brewery
        if (!parseObject.getClassName().equals(ParseID.CLASS_WINERY)) {
            String message = "The ParseObject passed into createFrom() is not of the type Winery.";
            InvalidClassException exception = new InvalidClassException(message);
            Logger.err(ChefFactory.class, message, exception);
            throw exception;
        }

        Winery winery = create();

        winery.setId(parseObject.getObjectId());
        winery.setCreated(parseObject.getCreatedAt().getTime());
        winery.setUpdated(parseObject.getUpdatedAt().getTime());

        winery.setName(parseObject.getString(ParseID.F_WINE_NAME));
        winery.setCity(parseObject.getString(ParseID.F_WINE_CITY));
        winery.setThumbnail(parseObject.getString(ParseID.F_WINE_THUMB));
        winery.setWebsite(parseObject.getString(ParseID.F_WINE_WEB));
        winery.setYelpURL(parseObject.getString(ParseID.F_WINE_YELP));
        winery.setTent(parseObject.getString(ParseID.F_WINE_TENT));

        return winery;

    }
}
