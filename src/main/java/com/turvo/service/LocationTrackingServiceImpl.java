package com.turvo.service;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.fields;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.group;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.match;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.newAggregation;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.project;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.unwind;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import com.turvo.exception.VerifyException;
import com.turvo.model.Asset;
import com.turvo.model.Device;
import com.turvo.model.Location;
import com.turvo.repository.AssetRepository;
import com.turvo.repository.DeviceRepository;
import com.turvo.utils.Verifier;

/**
 * The Class LocationTrackingServiceImpl.
 */
@Service
public class LocationTrackingServiceImpl implements LocationTrackingService {

    /** The asset repository. */
    @Autowired
    AssetRepository assetRepository;

    /** The device repository. */
    @Autowired
    DeviceRepository deviceRepository;

    /** The mongo template. */
    @Autowired
    MongoTemplate mongoTemplate;

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.turvo.service.LocationTrackingService#trackLocationByAssetId(java.lang.
     * String)
     */
    @Override
    public List<Location> trackLocationByAssetId(String assetId) {
	Asset asset = assetRepository.findByAssetId(assetId);
	Verifier.verifyNull(asset, "No results found");
	return asset.getLocations();
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.turvo.service.LocationTrackingService#addAssetInfo(com.turvo.model.Asset)
     */
    @Override
    public void addAssetInfo(Asset asset) {
	Asset repoAsset = assetRepository.findByAssetId(asset.getAssetId());
	if (repoAsset != null) {
	    Location location = asset.getLocations().get(0);
	    if (location.getPingTime() == null) {
		location.setPingTime(new Date());
	    }
	    repoAsset.getLocations().add(location);
	    Query query = new Query();
	    query.addCriteria(Criteria.where("assetId").is(asset.getAssetId()));
	    Update update = new Update().push("locations", location);
	    mongoTemplate.updateFirst(query, update, Asset.class);
	} else {
	    if (asset.getLocations().get(0).getPingTime() == null) {
		asset.getLocations().get(0).setPingTime(new Date());
	    }
	    assetRepository.insert(asset);
	}

    }

    /*
     * (non-Javadoc)
     * 
     * @see com.turvo.service.LocationTrackingService#addDeviceInfo(com.turvo.model.
     * Device)
     */
    @Override
    public void addDeviceInfo(Device device) {
	Device repoDevice = deviceRepository.findByDeviceId(device.getDeviceId());
	if (repoDevice != null) {
	    Location location = device.getLocations().get(0);
	    if (location.getPingTime() == null) {
		location.setPingTime(new Date());
	    }
	    Query query = new Query();
	    query.addCriteria(Criteria.where("deviceId").is(device.getDeviceId()));
	    Update update = new Update().push("locations", location);
	    mongoTemplate.updateFirst(query, update, Device.class);
	} else {
	    if (device.getLocations().get(0).getPingTime() == null) {
		device.getLocations().get(0).setPingTime(new Date());
	    }
	    deviceRepository.insert(device);
	}
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.turvo.service.LocationTrackingService#trackLocationByDeviceId(java.lang.
     * String)
     */
    @Override
    public List<Location> trackLocationByDeviceId(String deviceId) {
	Device device = deviceRepository.findByDeviceId(deviceId);
	Verifier.verifyNull(device, "No results found");
	return device.getLocations();
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.turvo.service.LocationTrackingService#trackLocationByDriverId(java.lang.
     * String)
     */
    @Override
    public List<Location> trackLocationByDriverId(String driverId) throws VerifyException {
	try {
	    List<Device> devices = deviceRepository.findAllByDriverId(driverId);
	    Verifier.verifyNull(devices, "No results found");
	    List<Location> locations = new ArrayList<Location>();
	    for (Device device : devices) {
		locations.addAll(device.getLocations());
	    }
	    return locations;
	} catch (VerifyException exception) {
	    throw new VerifyException("no data retrived for the provided search criteria");
	}
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.turvo.service.LocationTrackingService#trackLocationByTime(java.util.Date,
     * java.util.Date)
     */
    @Override
    public List<Location> trackLocationByTime(String assetId, Date startTime, Date endTime) throws VerifyException {
	Aggregation aggregation = newAggregation(unwind("locations"),
		match(Criteria.where("assetId").is(assetId).and("locations.pingTime").gte(startTime).lte(endTime)),
		project(fields().and("location", "$locations")), group("location"));
	AggregationResults<Location> groupResults = mongoTemplate.aggregate(aggregation, "Assets", Location.class);
	Verifier.verifyNull(groupResults, "No Results found for the provided criteria");
	return groupResults.getMappedResults();
    }

}
