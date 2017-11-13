package com.turvo.service;

import static org.mockito.Matchers.anyString;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.turvo.model.Asset;
import com.turvo.model.Device;
import com.turvo.model.Location;
import com.turvo.repository.AssetRepository;
import com.turvo.repository.DeviceRepository;

@RunWith(SpringJUnit4ClassRunner.class)
public class LocationTrackingServiceImplTest {
    
    private Asset asset;
    private Device device;
    private Location location;
    private List<Location> locations;
    
    @Mock
    private AssetRepository assetRepositoryMock;
    
    @Mock
    private DeviceRepository deviceRepositoryMock;
    
    @Mock
    private MongoTemplate mongoTemplateMock;
    
    @InjectMocks
    LocationTrackingServiceImpl impl = new LocationTrackingServiceImpl();
    

    @Before
    public void setUp() {
	asset = new Asset();
	device = new Device();
	location= new  Location();
	locations = new ArrayList<Location>();
	locations.add(location);
	asset.setLocations(locations);
	device.setLocations(locations);
    }
    @Test
    public void testTrackLocationByAssetId() {
	Mockito.when(assetRepositoryMock.findByAssetId(anyString())).thenReturn(asset);
	List<Location> actual = impl.trackLocationByAssetId("");
	Assert.assertEquals(locations, actual);
    }
    
    @Test
    public void testAddAssetInfo() {
	Mockito.when(assetRepositoryMock.findByAssetId(anyString())).thenReturn(null);
	Mockito.when(assetRepositoryMock.insert(asset)).thenReturn(asset);
	impl.addAssetInfo(asset);
    }
    
    @Test
    public void testtestTrackLocationByDeviceId() {
	Mockito.when(deviceRepositoryMock.findByDeviceId(anyString())).thenReturn(device);
	List<Location> actual = impl.trackLocationByDeviceId("");
	Assert.assertEquals(locations, actual);
    }
    
    @Test
    public void testAddDeviceInfo() {
	Mockito.when(deviceRepositoryMock.findByDeviceId(anyString())).thenReturn(null);
	Mockito.when(deviceRepositoryMock.insert(device)).thenReturn(device);
	impl.addDeviceInfo(device);
    }
    
}
