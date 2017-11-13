package com.turvo.model;

import java.util.Date;

/**
 * The Class Location.
 */
public class Location {

    /** The latitude. */
    private String latitude;

    /** The longitude. */
    private String longitude;

    /** The ping time. */
    private Date pingTime;

    /**
     * Gets the latitude.
     *
     * @return the latitude
     */
    public String getLatitude() {
	return latitude;
    }

    /**
     * Sets the latitude.
     *
     * @param latitude
     *            the new latitude
     */
    public void setLatitude(String latitude) {
	this.latitude = latitude;
    }

    /**
     * Gets the longitude.
     *
     * @return the longitude
     */
    public String getLongitude() {
	return longitude;
    }

    /**
     * Sets the longitude.
     *
     * @param longitude
     *            the new longitude
     */
    public void setLongitude(String longitude) {
	this.longitude = longitude;
    }

    /**
     * Gets the ping time.
     *
     * @return the ping time
     */
    public Date getPingTime() {
	return pingTime;
    }

    /**
     * Sets the ping time.
     *
     * @param pingTime
     *            the new ping time
     */
    public void setPingTime(Date pingTime) {
	this.pingTime = pingTime;
    }
}
