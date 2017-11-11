package com.turvo.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

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

    /* (non-Javadoc)
     * @see com.turvo.service.LocationTrackingService#trackLocationByAssetId(java.lang.String)
     */
    @Override
    public List<Location> trackLocationByAssetId(String assetId) {
	Asset asset = assetRepository.findByAssetId(assetId);
	Verifier.verifyNull(asset, "No results found");
	return asset.getLocationList();
    }

    /* (non-Javadoc)
     * @see com.turvo.service.LocationTrackingService#addAssetInfo(com.turvo.model.Asset)
     */
    @Override
    public void addAssetInfo(Asset asset) {
	Asset repoAsset = assetRepository.findByAssetId(asset.getAssetId());
	if (repoAsset != null) {
	    Location location = asset.getLocationList().get(0);
	    if (location.getPingTime() == null) {
		location.setPingTime(new Date());
	    }
	    repoAsset.getLocationList().add(location);
	    repoAsset.setEndDate(new Date());
	    Query query = new Query();
	    query.addCriteria(Criteria.where("assetId").is(asset.getAssetId()));
	    Update update = new Update().set("location", repoAsset.getLocationList());
	    mongoTemplate.updateFirst(query, update, Asset.class);
	} else {
	    if (asset.getLocationList().get(0).getPingTime() == null) {
		asset.getLocationList().get(0).setPingTime(new Date());
	    }
	    if (asset.getStartDate() == null) {
		asset.setStartDate(new Date());
	    }
	    assetRepository.insert(asset);
	}

    }
    
    /* (non-Javadoc)
     * @see com.turvo.service.LocationTrackingService#addDeviceInfo(com.turvo.model.Device)
     */
    @Override
    public void addDeviceInfo(Device device) {
	Device repoDevice = deviceRepository.findByDeviceId(device.getDeviceId());
	if (repoDevice != null) {
	    Location location = device.getLocationList().get(0);
	    if (location.getPingTime() == null) {
		location.setPingTime(new Date());
	    }
	    repoDevice.getLocationList().add(location);
	    Query query = new Query();
	    repoDevice.setEndDate(new Date());
	    query.addCriteria(Criteria.where("deviceId").is(device.getDeviceId()));
	    Update update = new Update().set("location", repoDevice.getLocationList());
	    mongoTemplate.updateFirst(query, update, Device.class);
	} else {
	    if (device.getLocationList().get(0).getPingTime() == null) {
		device.getLocationList().get(0).setPingTime(new Date());
	    }
	    if (device.getStartDate() == null) {
		device.setStartDate(new Date());
	    }
	    deviceRepository.insert(device);
	}
    }

    /* (non-Javadoc)
     * @see com.turvo.service.LocationTrackingService#trackLocationByDeviceId(java.lang.String)
     */
    @Override
    public List<Location> trackLocationByDeviceId(String deviceId) {
	Device device = deviceRepository.findByDeviceId(deviceId);
	Verifier.verifyNull(device, "No results found");
	return device.getLocationList();
    }

}
