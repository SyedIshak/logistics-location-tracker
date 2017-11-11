package com.turvo.model;

import org.springframework.data.mongodb.core.mapping.Document;

/**
 * The Class Asset.
 */
@Document(collection = "Assets")
public class Asset extends LocationModel {

    /** The asset id. */
    private String assetId;

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
     * @param assetId the new asset id
     */
    public void setAssetId(String assetId) {
	this.assetId = assetId;
    }

}
