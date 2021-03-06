package com.turvo.service;

import java.util.Date;
import java.util.List;

import com.turvo.model.Asset;
import com.turvo.model.Device;
import com.turvo.model.Location;

/**
 * The Interface LocationTrackingService.
 */
public interface LocationTrackingService {

    /**
     * Adds the asset info.
     *
     * @param asset
     *            the asset
     */
    public void addAssetInfo(Asset asset);

    /**
     * Track location by asset id.
     *
     * @param assetId
     *            the asset id
     * @return the list
     */
    public List<Location> trackLocationByAssetId(String assetId);

    /**
     * Adds the device info.
     *
     * @param device
     *            the device
     */
    public void addDeviceInfo(Device device);

    /**
     * Track location by device id.
     *
     * @param deviceId
     *            the device id
     * @return the list
     */
    public List<Location> trackLocationByDeviceId(String deviceId);
    
    /**
     * Track location by driver id.
     *
     * @param driverId the driver id
     * @return the list
     */
    public List<Location> trackLocationByDriverId(String driverId);
    
    /**
     * Track location by time.
     *
     * @param startTime the start time
     * @param endTime the end time
     * @return the list
     */
    public List<Location> trackLocationByTime(String assetId,Date startTime,Date endTime);

}
