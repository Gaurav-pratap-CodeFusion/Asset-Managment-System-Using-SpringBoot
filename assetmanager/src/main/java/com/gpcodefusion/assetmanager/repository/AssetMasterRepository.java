package com.gpcodefusion.assetmanager.repository;


import com.gpcodefusion.assetmanager.model.AssetMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AssetMasterRepository extends JpaRepository<AssetMaster, String> {

    // Get the latest asset ID
    @Query(value = "SELECT AssetID FROM assetmaster ORDER BY CAST(SUBSTRING(AssetID, 4) AS UNSIGNED) DESC LIMIT 1", nativeQuery = true)
    String findLatestAssetId();



}

