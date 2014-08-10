package com.github.digin.android.exceptions;

/**
 *  Thrown when a backing data store expects a class of some type
 *  but is given something different.
 *
 *  Created by mike on 7/11/14.
 */
public class InvalidClassException extends RuntimeException {

    public InvalidClassException() {
        super();
    }

    public InvalidClassException(String message) {
        super(message);
    }

}
