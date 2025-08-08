package com.gpcodefusion.assetmanager.repository;

import com.gpcodefusion.assetmanager.model.AssetMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SearchRepository extends JpaRepository<AssetMaster, String> {

    AssetMaster findByAssetId(String assetId);
    List<AssetMaster> findByAssetNameContainingIgnoreCase(String assetName);
    List<AssetMaster> findByAssetCategoryContainingIgnoreCase(String category);

    @Query("SELECT DISTINCT a.assetCategory FROM AssetMaster a")
    List<String> findDistinctAssetCategories();
}