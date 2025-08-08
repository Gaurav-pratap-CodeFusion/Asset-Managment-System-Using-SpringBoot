package com.gpcodefusion.assetmanager.repository;

import com.gpcodefusion.assetmanager.model.AssetMaster;
import com.gpcodefusion.assetmanager.model.ScanAssetDetailsProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScanAssetRepository extends JpaRepository<AssetMaster, String> {

    @Query(value = "CALL Proc_GetAssetDetailsByBarcode(:barcode)", nativeQuery = true)
    List<ScanAssetDetailsProjection> getAssetDetailsByBarcode(@Param("barcode") String barcode);
}