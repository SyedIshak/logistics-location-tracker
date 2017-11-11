package com.turvo.utils;

import java.util.List;

import org.springframework.util.StringUtils;

import com.turvo.exception.VerifyException;

/**
 * The Class Verifier.
 */
public class Verifier {

    /**
     * Verify null.
     *
     * @param object
     *            the object
     * @param message
     *            the message
     */
    public static void verifyNull(Object object, String message) {
	if (object == null) {
	    throw new VerifyException(message);
	}
    }

    /**
     * Verify null or empty.
     *
     * @param object
     *            the object
     * @param message
     *            the message
     */
    public static void verifyNullOrEmpty(Object object, String message) {
	if (object == null || StringUtils.isEmpty(object)) {
	    throw new VerifyException(message);
	}
    }

    /**
     * Verify empty collection.
     *
     * @param collection
     *            the collection
     * @param message
     *            the message
     */
    public static void verifyEmptyCollection(List<?> collection, String message) {
	if (collection.isEmpty()) {
	    throw new VerifyException(message);
	}
    }

}
