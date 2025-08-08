package com.gpcodefusion.assetmanager.service;

import com.gpcodefusion.assetmanager.model.AssetMaster;
import com.gpcodefusion.assetmanager.repository.SearchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SearchService {

    @Autowired
    private SearchRepository searchRepository;

    public AssetMaster searchById(String id) {
        return searchRepository.findByAssetId(id);
    }

    public List<AssetMaster> searchByName(String name) {
        return searchRepository.findByAssetNameContainingIgnoreCase(name);
    }

    public List<AssetMaster> searchByCategory(String category) {
        return searchRepository.findByAssetCategoryContainingIgnoreCase(category);
    }

    public List<AssetMaster> getAllAssets() {
        return searchRepository.findAll();
    }

    public List<String> getAllCategories() {
        return searchRepository.findDistinctAssetCategories();
    }
}