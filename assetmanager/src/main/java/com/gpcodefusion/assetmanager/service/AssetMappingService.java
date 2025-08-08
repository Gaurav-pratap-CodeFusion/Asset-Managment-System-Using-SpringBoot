package com.gpcodefusion.assetmanager.service;

import com.gpcodefusion.assetmanager.model.AssetMappingDetails;
import com.gpcodefusion.assetmanager.repository.AssetMappingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

import static org.yaml.snakeyaml.nodes.NodeId.mapping;

@Service
public class AssetMappingService {

    @Autowired
    private AssetMappingRepository mappingRepo;

    public void saveMapping(String empId, String assetId) {


        // Check if asset is already assigned
        boolean exists = mappingRepo.existsByAssetId(assetId);

        if (exists) {
            throw new IllegalStateException("This asset is already assigned to someone.");
        }

        AssetMappingDetails mapping = new AssetMappingDetails();
        mapping.setEmpId(empId);
        mapping.setAssetId(assetId);
        mapping.setMappedDate(new Date());
        mappingRepo.save(mapping);
        String barcode = String.format("QRC%03dBARC", mapping.getMappingId());
        mapping.setBarcode(barcode);
        mappingRepo.save(mapping);
    }
}

