package com.github.digin.android.constants;

/**
 *  IDs for objects on parse.
 *  Created by mike on 7/11/14.
 */
public class ParseID {

    public static final String CLASS_INSTALL = "Installation";
    public static final String CLASS_USER = "User";
    public static final String CLASS_BREWERY = "Brewery";
    public static final String CLASS_CHEF = "Chef";
    public static final String CLASS_PRODUCER = "Producer";
    public static final String CLASS_WINERY = "Winery";

    /** General fields across several class (synonymous with Participant model) */

    public static final String FIELD_ID = "objectId";
    public static final String FIELD_NAME = "name";
    public static final String FIELD_THUMBNAIL = "thumbnail";
    public static final String FIELD_WEBSITE = "website";
    public static final String FIELD_YELP = "yelpURL";
    public static final String FIELD_CITY = "city";

    /** Fields on in Chef object */

    public static final String CHEF_CATEGORY = "category";
    public static final String CHEF_INGREDIENT = "mainIngredient";
    public static final String CHEF_DISH = "dishTitle";
    public static final String CHEF_TENT = "tent";

}
