package com.turvo.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.data.mongodb.core.mapping.Document;

/**
 * The Class Asset.
 */
@Document(collection = "Assets")
public class Asset {

    /** The asset id. */
    private String assetId;
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
     * @param locations
     *            the new locations
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
     * Gets the asset id.
     *
     * @return the asset id
     */
    public String getAssetId() {
	return assetId;
    }

    /**
     * Sets the asset id.
     *
     * @param assetId
     *            the new asset id
     */
    public void setAssetId(String assetId) {
	this.assetId = assetId;
    }

}
