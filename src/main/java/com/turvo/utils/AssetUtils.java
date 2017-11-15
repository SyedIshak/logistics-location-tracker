package com.turvo.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import com.turvo.exception.VerifyException;

/**
 * The Class AssetUtils.
 */
public class AssetUtils {

    /**
     * Parses the date to long.
     *
     * @param date
     *            the date
     * @return the date
     * @throws VerifyException
     *             the verify exception
     */
    public static Date parseDateToLong(String input) throws VerifyException {
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss");
	sdf.setTimeZone(TimeZone.getTimeZone("UTC")); 
	Date date;
	try {
	    date = sdf.parse(input);
	} catch (ParseException e) {
	    throw new VerifyException("Invalid Date format provided");
	}
	return date;

    }

}
