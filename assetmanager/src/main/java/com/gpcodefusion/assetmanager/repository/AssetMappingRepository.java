package com.gpcodefusion.assetmanager.repository;

import com.gpcodefusion.assetmanager.model.AssetMappingDetails;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface AssetMappingRepository extends JpaRepository<AssetMappingDetails, Long> {
    boolean existsByAssetId(String assetId);
}
