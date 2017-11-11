package com.turvo.model;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The Class LocationModel.
 */
public class LocationModel {
    
    /** The location list. */
    private List<Location> locationList;
    
    /** The start date. */
    private Date startDate;
    
    /** The end date. */
    private Date endDate;
    
    /** The additional info. */
    private Map<String, Object> additionalInfo = new HashMap<String, Object>();

    /**
     * Gets the start date.
     *
     * @return the start date
     */
    public Date getStartDate() {
        return startDate;
    }

    /**
     * Sets the start date.
     *
     * @param startDate the new start date
     */
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    /**
     * Gets the end date.
     *
     * @return the end date
     */
    public Date getEndDate() {
        return endDate;
    }

    /**
     * Sets the end date.
     *
     * @param endDate the new end date
     */
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    /**
     * Gets the location list.
     *
     * @return the location list
     */
    public List<Location> getLocationList() {
        return locationList;
    }

    /**
     * Sets the location list.
     *
     * @param locationList the new location list
     */
    public void setLocationList(List<Location> locationList) {
        this.locationList = locationList;
    }
    
    /**
     * Gets the additional info.
     *
     * @return the additional info
     */
    public Map<String, Object> getAdditionalInfo() {
	return this.additionalInfo;
    }

    /**
     * Sets the additional info.
     *
     * @param name the name
     * @param value the value
     */
    public void setAdditionalInfo(String name, Object value) {
	this.additionalInfo.put(name, value);
    }
    
    

}
