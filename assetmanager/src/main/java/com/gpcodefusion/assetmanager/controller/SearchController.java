package com.gpcodefusion.assetmanager.controller;

import com.gpcodefusion.assetmanager.model.AssetMaster;
import com.gpcodefusion.assetmanager.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/search")
public class SearchController {

    @Autowired
    private SearchService searchService;

    @GetMapping("/assets")
    public ResponseEntity<?> searchAssets(
            @RequestParam(required = false) String searchText,
            @RequestParam(required = false, defaultValue = "All Categories") String category) {

        List<AssetMaster> assets;

        if (searchText != null && !searchText.isEmpty()) {
            AssetMaster byId = searchService.searchById(searchText);
            if (byId != null) {
                assets = Collections.singletonList(byId);
            } else {
                assets = searchService.searchByName(searchText);
            }
        } else {
            assets = category != null && !category.equals("All Categories")
                    ? searchService.searchByCategory(category)
                    : searchService.getAllAssets();
        }

        return ResponseEntity.ok(assets);
    }

    @GetMapping("/categories")
    public ResponseEntity<List<String>> getAllCategories() {
        return ResponseEntity.ok(searchService.getAllCategories());
    }
}