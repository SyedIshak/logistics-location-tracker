package com.turvo.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.data.mongodb.core.mapping.Document;

/**
 * The Class Device.
 */
@Document(collection = "Devices")
public class Device {

    /** The device id. */
    private String deviceId;

    /** The current vehicle id. */
    private String currentVehicleId;

    /** The previous vehicle id. */
    private String previousVehicleId;

    /** The speed. */
    private int speed;

    /** The driver. */
    private String driverId;

    /** The location list. */
    private List<Location> locations;

    /** The additional info. */
    private Map<String, Object> additionalInfo = new HashMap<String, Object>();

    /**
     * Gets the locations.
     *
     * @return the locations
     */
    public List<Location> getLocations() {
        return locations;
    }

    /**
     * Sets the locations.
     *
     * @param locations the new locations
     */
    public void setLocations(List<Location> locations) {
        this.locations = locations;
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
     * @param name
     *            the name
     * @param value
     *            the value
     */
    public void setAdditionalInfo(String name, Object value) {
	this.additionalInfo.put(name, value);
    }

    /**
     * Gets the device id.
     *
     * @return the device id
     */
    public String getDeviceId() {
	return deviceId;
    }

    /**
     * Sets the device id.
     *
     * @param deviceId
     *            the new device id
     */
    public void setDeviceId(String deviceId) {
	this.deviceId = deviceId;
    }

    /**
     * Gets the current vehicle id.
     *
     * @return the current vehicle id
     */
    public String getCurrentVehicleId() {
	return currentVehicleId;
    }

    /**
     * Sets the current vehicle id.
     *
     * @param currentVehicleId
     *            the new current vehicle id
     */
    public void setCurrentVehicleId(String currentVehicleId) {
	this.currentVehicleId = currentVehicleId;
    }

    /**
     * Gets the previous vehicle id.
     *
     * @return the previous vehicle id
     */
    public String getPreviousVehicleId() {
	return previousVehicleId;
    }

    /**
     * Sets the previous vehicle id.
     *
     * @param previousVehicleId
     *            the new previous vehicle id
     */
    public void setPreviousVehicleId(String previousVehicleId) {
	this.previousVehicleId = previousVehicleId;
    }

    /**
     * Gets the speed.
     *
     * @return the speed
     */
    public int getSpeed() {
	return speed;
    }

    /**
     * Sets the speed.
     *
     * @param speed
     *            the new speed
     */
    public void setSpeed(int speed) {
	this.speed = speed;
    }

    /**
     * Gets the driver id.
     *
     * @return the driver id
     */
    public String getDriverId() {
        return driverId;
    }

    /**
     * Sets the driver id.
     *
     * @param driverId the new driver id
     */
    public void setDriverId(String driverId) {
        this.driverId = driverId;
    }
    
    

}
