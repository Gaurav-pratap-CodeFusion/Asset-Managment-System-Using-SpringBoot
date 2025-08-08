package com.gpcodefusion.assetmanager.service;

import com.gpcodefusion.assetmanager.model.ScanAssetDetailsProjection;
import com.gpcodefusion.assetmanager.repository.ScanAssetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScanAssetService {

    private final ScanAssetRepository assetRepository;

    @Autowired
    public ScanAssetService(ScanAssetRepository assetRepository) {
        this.assetRepository = assetRepository;
    }

    public List<ScanAssetDetailsProjection> getAssetDetails(String barcode) {
        return assetRepository.getAssetDetailsByBarcode(barcode);
    }
}