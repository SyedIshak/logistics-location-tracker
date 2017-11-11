package com.turvo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.turvo.model.Asset;

/**
 * The Interface AssetRepository.
 */
@Repository
public interface AssetRepository extends MongoRepository<Asset, String> {

    /**
     * Find by asset id.
     *
     * @param assetId the asset id
     * @return the asset
     */
    public Asset findByAssetId(String assetId);

}
