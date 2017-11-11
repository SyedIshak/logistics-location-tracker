package com.turvo.model;

import java.util.Date;

/**
 * The Class Location.
 */
public class Location {
    
    /** The latittude. */
    private String latittude;
    
    /** The longitude. */
    private String longitude;
    
    /** The ping time. */
    private Date pingTime;

    /**
     * Gets the latittude.
     *
     * @return the latittude
     */
    public String getLatittude() {
	return latittude;
    }

    /**
     * Sets the latittude.
     *
     * @param latittude the new latittude
     */
    public void setLatittude(String latittude) {
	this.latittude = latittude;
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
     * @param longitude the new longitude
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
     * @param pingTime the new ping time
     */
    public void setPingTime(Date pingTime) {
        this.pingTime = pingTime;
    }
}
