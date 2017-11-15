package com.turvo.controller;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.turvo.model.Asset;
import com.turvo.model.Device;
import com.turvo.model.Location;
import com.turvo.service.LocationTrackingService;

/**
 * The Class LogisticsControllerTest.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SuppressWarnings("unchecked")
public class LogisticsControllerTest {

    /** The locations. */
    private List<Location> locations;

    /** The location tracking service mock. */
    @Mock
    LocationTrackingService locationTrackingServiceMock;

    /** The controller. */
    @InjectMocks
    LogisticsController controller = new LogisticsController();

    /**
     * Sets the up.
     */
    @Before
    public void setUp() {
	locations = new ArrayList<Location>();
	Location location = new Location();
	location.setLatitude("lat-1");
	location.setLongitude("long-1");
	location.setPingTime(new Date());
	locations.add(location);
    }

    /**
     * Test get location by asset.
     */
    @Test
    public void testGetLocationByAsset() {
	Mockito.when(locationTrackingServiceMock.trackLocationByAssetId(anyString())).thenReturn(locations);
	ResponseEntity<List<Location>> response = (ResponseEntity<List<Location>>) controller
		.getLocationByAsset("test Asset");
	Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
	Assert.assertTrue(response.getBody().size() > 0);
    }

    /**
     * Test get location by asset for null asset id.
     */
    @Test
    public void testGetLocationByAssetForNullAssetId() {
	ResponseEntity<List<Location>> response = (ResponseEntity<List<Location>>) controller.getLocationByAsset(null);
	Assert.assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    /**
     * Test add asset.
     */
    @Test
    public void testAddAsset() {
	Asset asset = new Asset();
	asset.setAssetId("test Asset Id");
	Mockito.doNothing().when(locationTrackingServiceMock).addAssetInfo(asset);
	ResponseEntity<List<Location>> response = (ResponseEntity<List<Location>>) controller.addAsset(asset);
	Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    /**
     * Test add asset for null asset.
     */
    @Test
    public void testAddAssetForNullAsset() {
	ResponseEntity<List<Location>> response = (ResponseEntity<List<Location>>) controller.addAsset(null);
	Assert.assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    /**
     * Test add asset for null asset id.
     */
    @Test
    public void testAddAssetForNullAssetId() {
	Asset asset = new Asset();
	ResponseEntity<List<Location>> response = (ResponseEntity<List<Location>>) controller.addAsset(asset);
	Assert.assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    /**
     * Test get location by device.
     */
    @Test
    public void testGetLocationByDevice() {
	Mockito.when(locationTrackingServiceMock.trackLocationByDeviceId(anyString())).thenReturn(locations);
	ResponseEntity<List<Location>> response = (ResponseEntity<List<Location>>) controller
		.getLocationByDevice("Device test Id");
	Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
	Assert.assertTrue(response.getBody().size() > 0);
    }

    /**
     * Test get location by devicefor null device id.
     */
    @Test
    public void testGetLocationByDeviceforNullDeviceId() {
	ResponseEntity<List<Location>> response = (ResponseEntity<List<Location>>) controller.getLocationByDevice(null);
	Assert.assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    /**
     * Test add device.
     */
    @Test
    public void testAddDevice() {
	Device device = new Device();
	device.setDeviceId("test Device Id");
	Mockito.doNothing().when(locationTrackingServiceMock).addDeviceInfo(device);
	ResponseEntity<List<Location>> response = (ResponseEntity<List<Location>>) controller.addDevice(device);
	Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    /**
     * Test add device for null.
     */
    @Test
    public void testAddDeviceForNull() {
	ResponseEntity<List<Location>> response = (ResponseEntity<List<Location>>) controller.addDevice(null);
	Assert.assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    /**
     * Test add device for null device id.
     */
    @Test
    public void testAddDeviceForNullDeviceId() {
	Device device = new Device();
	ResponseEntity<List<Location>> response = (ResponseEntity<List<Location>>) controller.addDevice(device);
	Assert.assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    /**
     * Test get location by driver.
     */
    @Test
    public void testGetLocationByDriver() {
	Mockito.when(locationTrackingServiceMock.trackLocationByDriverId(anyString())).thenReturn(locations);
	ResponseEntity<List<Location>> response = (ResponseEntity<List<Location>>) controller
		.getLocationByDriver("DriverId Test");
	Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
	Assert.assertTrue(response.getBody().size() > 0);
    }

    /**
     * Test get location by driver for null driver id.
     */
    @Test
    public void testGetLocationByDriverForNullDriverId() {
	ResponseEntity<List<Location>> response = (ResponseEntity<List<Location>>) controller.getLocationByDriver(null);
	Assert.assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    /**
     * Test get location by driver for empty driver id.
     */
    @Test
    public void testGetLocationByDriverForEmptyDriverId() {
	ResponseEntity<List<Location>> response = (ResponseEntity<List<Location>>) controller.getLocationByDriver("");
	Assert.assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    /**
     * Test get location by time.
     */
    @Test
    public void testGetLocationByTime() {
	Mockito.when(locationTrackingServiceMock.trackLocationByTime(anyString(), any(Date.class), any(Date.class)))
		.thenReturn(locations);
	ResponseEntity<List<Location>> response = (ResponseEntity<List<Location>>) controller
		.getLocationByTime("test Asset Id", "2017-11-12T16:04:24", "2017-11-12T16:04:24");
	Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
	Assert.assertTrue(response.getBody().size() > 0);
    }

    /**
     * Test get location by time with null end time.
     */
    @Test
    public void testGetLocationByTimeWithNullEndTime() {
	ResponseEntity<List<Location>> response = (ResponseEntity<List<Location>>) controller
		.getLocationByTime("test Asset Id", "2017-11-12T16:04:24", null);
	Assert.assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    /**
     * Test get location by time with null start time.
     */
    @Test
    public void testGetLocationByTimeWithNullStartTime() {
	ResponseEntity<List<Location>> response = (ResponseEntity<List<Location>>) controller
		.getLocationByTime("test Asset Id", null, "2017-11-12T16:04:24");
	Assert.assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    /**
     * Test get location by time with empty end time.
     */
    @Test
    public void testGetLocationByTimeWithEmptyEndTime() {
	ResponseEntity<List<Location>> response = (ResponseEntity<List<Location>>) controller
		.getLocationByTime("test Asset Id", "2017-11-12T16:04:24", "");
	Assert.assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    /**
     * Test get location by time with empty start time.
     */
    @Test
    public void testGetLocationByTimeWithEmptyStartTime() {
	ResponseEntity<List<Location>> response = (ResponseEntity<List<Location>>) controller
		.getLocationByTime("test Asset Id", "", "2017-11-12T16:04:24");
	Assert.assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

}
