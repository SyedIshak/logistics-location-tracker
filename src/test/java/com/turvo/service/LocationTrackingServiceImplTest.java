package com.turvo.service;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;

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
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.turvo.exception.VerifyException;
import com.turvo.model.Asset;
import com.turvo.model.Device;
import com.turvo.model.Location;
import com.turvo.repository.AssetRepository;
import com.turvo.repository.DeviceRepository;

/**
 * The Class LocationTrackingServiceImplTest.
 */
@RunWith(SpringJUnit4ClassRunner.class)
public class LocationTrackingServiceImplTest {

    /** The asset. */
    private Asset asset;

    /** The device. */
    private Device device;

    /** The location. */
    private Location location;

    /** The locations. */
    private List<Location> locations;

    /** The asset repository mock. */
    @Mock
    private AssetRepository assetRepositoryMock;

    /** The device repository mock. */
    @Mock
    private DeviceRepository deviceRepositoryMock;

    /** The mongo template mock. */
    @Mock
    private MongoTemplate mongoTemplateMock;

    /** The impl. */
    @InjectMocks
    LocationTrackingServiceImpl impl = new LocationTrackingServiceImpl();

    /**
     * Sets the up.
     */
    @Before
    public void setUp() {
	asset = new Asset();
	device = new Device();
	location = new Location();
	locations = new ArrayList<Location>();
	locations.add(location);
	asset.setLocations(locations);
	device.setLocations(locations);
    }

    /**
     * Test track location by asset id.
     */
    @Test
    public void testTrackLocationByAssetId() {
	Mockito.when(assetRepositoryMock.findByAssetId(anyString())).thenReturn(asset);
	List<Location> actual = impl.trackLocationByAssetId("");
	Assert.assertEquals(locations, actual);
    }

    /**
     * Test add asset info.
     */
    @Test
    public void testAddAssetInfo() {
	Mockito.when(assetRepositoryMock.findByAssetId(anyString())).thenReturn(null);
	Mockito.when(assetRepositoryMock.insert(asset)).thenReturn(asset);
	impl.addAssetInfo(asset);
    }

    /**
     * Test add asset info with repo asset.
     */
    @Test
    public void testAddAssetInfoWithRepoAsset() {
	Mockito.when(assetRepositoryMock.findByAssetId(anyString())).thenReturn(asset);
	Mockito.when(mongoTemplateMock.updateFirst(any(Query.class), any(Update.class), eq(Asset.class)))
		.thenReturn(null);
	impl.addAssetInfo(asset);
    }

    /**
     * Test track location by device id.
     */
    @Test
    public void testTrackLocationByDeviceId() {
	Mockito.when(deviceRepositoryMock.findByDeviceId(anyString())).thenReturn(device);
	List<Location> actual = impl.trackLocationByDeviceId("");
	Assert.assertEquals(locations, actual);
    }

    /**
     * Test add device info.
     */
    @Test
    public void testAddDeviceInfo() {
	Mockito.when(deviceRepositoryMock.findByDeviceId(anyString())).thenReturn(null);
	Mockito.when(deviceRepositoryMock.insert(device)).thenReturn(device);
	impl.addDeviceInfo(device);
    }

    /**
     * Test add device info with repo device.
     */
    @Test
    public void testAddDeviceInfoWithRepoDevice() {
	Mockito.when(deviceRepositoryMock.findByDeviceId(anyString())).thenReturn(device);
	Mockito.when(mongoTemplateMock.updateFirst(any(Query.class), any(Update.class), eq(Device.class)))
		.thenReturn(null);
	impl.addDeviceInfo(device);
    }

    /**
     * Test track location by driver id.
     */
    @Test
    public void testTrackLocationByDriverId() {
	List<Device> devices = new ArrayList<Device>();
	devices.add(device);
	Mockito.when(deviceRepositoryMock.findAllByDriverId(anyString())).thenReturn(devices);
	Assert.assertEquals(locations, impl.trackLocationByDriverId("testDriverId"));
    }

    /**
     * Test track location by driver id with no devices retrieved.
     */
    @Test(expected = VerifyException.class)
    public void testTrackLocationByDriverIdWithNoDevicesRetrieved() {
	Mockito.when(deviceRepositoryMock.findAllByDriverId(anyString())).thenReturn(null);
	impl.trackLocationByDriverId("testDriverId");
    }

    /**
     * Test track location by time.
     */
    @Test
    public void testTrackLocationByTime() {
	List<Asset> assets = new ArrayList<Asset>();
	assets.add(asset);
	Mockito.when(mongoTemplateMock.find(any(Query.class), eq(Asset.class))).thenReturn(assets);
	Assert.assertEquals(locations, impl.trackLocationByTime(new Date(), new Date()));
    }

    /**
     * Test track location by time with null response.
     */
    @Test(expected = VerifyException.class)
    public void testTrackLocationByTimeWithNullResponse() {
	Mockito.when(mongoTemplateMock.find(any(Query.class), eq(Asset.class))).thenReturn(null);
	impl.trackLocationByTime(new Date(), new Date());
    }

}
