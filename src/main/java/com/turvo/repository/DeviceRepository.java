package com.turvo.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.turvo.model.Device;

/**
 * The Interface DeviceRepository.
 */
@Repository
public interface DeviceRepository extends MongoRepository<Device, String> {
    
    /**
     * Find by device id.
     *
     * @param deviceId the device id
     * @return the device
     */
    public Device findByDeviceId(String deviceId);
    
    /**
     * Find by driver id.
     *
     * @param driverId the driver id
     * @return the device
     */
    public List<Device> findAllByDriverId(String driverId);
}
