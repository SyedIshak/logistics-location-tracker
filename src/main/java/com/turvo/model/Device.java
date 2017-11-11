package com.turvo.model;

import org.springframework.data.mongodb.core.mapping.Document;

/**
 * The Class Device.
 */
@Document(collection = "Devices")
public class Device extends LocationModel{

    /** The device id. */
    private String deviceId;
    
    /** The current vehicle id. */
    private String currentVehicleId;
    
    /** The previous vehicle id. */
    private String previousVehicleId;
    
    /** The speed. */
    private int speed;
    
    /** The driver. */
    private Driver driver;

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
     * @param deviceId the new device id
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
     * @param currentVehicleId the new current vehicle id
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
     * @param previousVehicleId the new previous vehicle id
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
     * @param speed the new speed
     */
    public void setSpeed(int speed) {
	this.speed = speed;
    }

    /**
     * Gets the driver.
     *
     * @return the driver
     */
    public Driver getDriver() {
	return driver;
    }

    /**
     * Sets the driver.
     *
     * @param driver the new driver
     */
    public void setDriver(Driver driver) {
	this.driver = driver;
    }

}
