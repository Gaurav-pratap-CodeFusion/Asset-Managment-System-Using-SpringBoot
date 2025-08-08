package com.gpcodefusion.assetmanager.service;

import com.gpcodefusion.assetmanager.model.AssetMaster;
import com.gpcodefusion.assetmanager.repository.AssetMasterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AssetMasterService {

    @Autowired
    private AssetMasterRepository assetRepo;

    public String generateNextAssetId() {
        String lastId = assetRepo.findLatestAssetId(); // Example: AST005
        if (lastId == null) {
            return "AST001";
        }

        int number = Integer.parseInt(lastId.substring(3));
        number++;
        return String.format("AST%03d", number);
    }

    public void saveAsset(AssetMaster asset) {
        assetRepo.save(asset);
    }
}
