package com.gpcodefusion.assetmanager.service;

import com.gpcodefusion.assetmanager.model.AssetMaster;
import com.gpcodefusion.assetmanager.repository.AssetMTRepository;
import com.gpcodefusion.assetmanager.repository.AssetMasterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AssetMTService {

    @Autowired
    private final AssetMTRepository assetMasterRepository;

    public AssetMTService(AssetMTRepository assetMasterRepository) {
        this.assetMasterRepository = assetMasterRepository;
    }

    public List<AssetMaster> getAllAssets() {
        return assetMasterRepository.findAll();
    }

    public AssetMaster getAssetById(String assetId) {
        return assetMasterRepository.findById(assetId)
                .orElseThrow(() -> new RuntimeException("Asset not found with ID: " + assetId));
    }

    public void updateAsset(AssetMaster asset) {
        assetMasterRepository.save(asset);
    }

    public void deleteAsset(String assetId) {
        assetMasterRepository.deleteById(assetId);
    }



}