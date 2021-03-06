package com.turvo.controller;

import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.turvo.constants.Constants;
import com.turvo.exception.VerifyException;
import com.turvo.model.Asset;
import com.turvo.model.Device;
import com.turvo.model.Location;
import com.turvo.model.Response;
import com.turvo.service.LocationTrackingService;
import com.turvo.utils.AssetUtils;
import com.turvo.utils.Verifier;

/**
 * The Class LogisticsController.
 */
@RestController
public class LogisticsController {

    /** The Constant bundle. */
    private static final ResourceBundle bundle = ResourceBundle.getBundle("turvo_bundle", Locale.US);

    /** The location tracking service. */
    @Autowired
    LocationTrackingService locationTrackingService;

    /**
     * Gets the location by asset.
     *
     * @param assetId
     *            the asset id
     * @return the location by asset
     */
    @RequestMapping(value = "getLocationByAsset/{assetId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getLocationByAsset(@PathVariable String assetId) {
	try {
	    Verifier.verifyNull(assetId, bundle.getString("com.turvo.controller.AssetNotNull"));
	    List<Location> assets = locationTrackingService.trackLocationByAssetId(assetId);
	    return new ResponseEntity<>(assets, HttpStatus.OK);
	} catch (VerifyException exception) {
	    return new ResponseEntity<>(new Response(Constants.INVALID_INPUT, exception.getMessage()),
		    HttpStatus.BAD_REQUEST);
	}
    }

    /**
     * Adds the asset.
     *
     * @param asset
     *            the asset
     * @return the response entity
     */
    @RequestMapping(value = "/addAsset", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> addAsset(@RequestBody Asset asset) {
	try {
	    Verifier.verifyNull(asset, bundle.getString("com.turvo.controller.AssetNotNull"));
	    Verifier.verifyNullOrEmpty(asset.getAssetId(), bundle.getString("com.turvo.controller.AssetInfoNotNull"));
	    locationTrackingService.addAssetInfo(asset);
	    return new ResponseEntity<>(new Response(Constants.SUCESS, "Asset Added Sucessfully"), HttpStatus.OK);
	} catch (VerifyException exception) {
	    return new ResponseEntity<>(new Response(Constants.FAILURE, exception.getMessage()),
		    HttpStatus.BAD_REQUEST);
	}

    }

    /**
     * Gets the location by device.
     *
     * @param deviceId
     *            the device id
     * @return the location by device
     */
    @RequestMapping(value = "getLocationByDevice/{deviceId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getLocationByDevice(@PathVariable String deviceId) {
	try {
	    Verifier.verifyNull(deviceId, bundle.getString("com.turvo.controller.DeviceNotNull"));
	    List<Location> locationList = locationTrackingService.trackLocationByDeviceId(deviceId);
	    return new ResponseEntity<>(locationList, HttpStatus.OK);
	} catch (VerifyException exception) {
	    return new ResponseEntity<>(new Response(Constants.INVALID_INPUT, exception.getMessage()),
		    HttpStatus.BAD_REQUEST);
	}
    }

    /**
     * Adds the device.
     *
     * @param device
     *            the device
     * @return the response entity
     */
    @RequestMapping(value = "/addDevice", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> addDevice(@RequestBody Device device) {
	try {
	    Verifier.verifyNull(device, bundle.getString("com.turvo.controller.DeviceNotNull"));
	    Verifier.verifyNullOrEmpty(device.getDeviceId(),
		    bundle.getString("com.turvo.controller.DeviceInfoNotNull"));
	    locationTrackingService.addDeviceInfo(device);
	    return new ResponseEntity<>(new Response(Constants.SUCESS, "Device Added Sucessfully"), HttpStatus.OK);
	} catch (VerifyException exception) {
	    return new ResponseEntity<>(new Response(Constants.FAILURE, exception.getMessage()),
		    HttpStatus.BAD_REQUEST);
	}
    }

    /**
     * Gets the location by driver.
     *
     * @param driverId
     *            the driver id
     * @return the location by driver
     */
    @RequestMapping(value = "/getLocationByDriver/{driverId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getLocationByDriver(@PathVariable String driverId) {
	try {
	    Verifier.verifyNullOrEmpty(driverId, bundle.getString("com.turvo.controller.DiverIdNotNull"));
	    List<Location> locations = locationTrackingService.trackLocationByDriverId(driverId);
	    return new ResponseEntity<>(locations, HttpStatus.OK);
	} catch (VerifyException exception) {
	    return new ResponseEntity<>(new Response(Constants.INVALID_INPUT, exception.getMessage()),
		    HttpStatus.BAD_REQUEST);
	}
    }

    /**
     * Gets the location by time.
     *
     * @param startTime the start time
     * @param endTime the end time
     * @return the location by time
     */
    @RequestMapping(value = "/getLocationByTime/{assetId}/{startTime}/{endTime}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getLocationByTime(@PathVariable String assetId,@PathVariable String startTime, @PathVariable String endTime) {
	try {
	    Verifier.verifyNullOrEmpty(startTime, "startTime cannot be null or empty");
	    Verifier.verifyNullOrEmpty(endTime, "endTime cannot be null or empty");
	    Verifier.verifyNullOrEmpty(assetId, "assetId cannot be null or empty");
	    List<Location> locations = locationTrackingService
		    .trackLocationByTime(assetId, AssetUtils.parseDateToLong(startTime), AssetUtils.parseDateToLong(endTime));
	    if(locations.isEmpty() || locations.size() ==0) {
		 return new ResponseEntity<>(new Response("Success", "No Results found for the provided criteria"), HttpStatus.OK);
	    }
	    return new ResponseEntity<>(locations, HttpStatus.OK);
	} catch (VerifyException exception) {
	    return new ResponseEntity<>(new Response(Constants.INVALID_INPUT, exception.getMessage()),
		    HttpStatus.BAD_REQUEST);
	}

    }

}
