package com.gpcodefusion.assetmanager.controller;

import com.gpcodefusion.assetmanager.model.AssetMaster;
import com.gpcodefusion.assetmanager.service.AssetMTService;
import com.gpcodefusion.assetmanager.service.AssetMasterService;
import com.gpcodefusion.assetmanager.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/assets")
public class AssetMTController {

    @Autowired
    private final AssetMTService assetMasterService;
    @Autowired
    private SearchService searchService;

    public AssetMTController(AssetMTService assetMasterService) {
        this.assetMasterService = assetMasterService;
    }

    @GetMapping
    public String viewAllAssets(Model model) {
        List<AssetMaster> assets = assetMasterService.getAllAssets();
//        List<Date> creationDates = assetMasterService.getAllCreationDates();

        model.addAttribute("assets", assets);
        model.addAttribute("assets", assetMasterService.getAllAssets());
        model.addAttribute("categories", searchService.getAllCategories());
//        model.addAttribute("creationDates", creationDates);

        return "Asset/showAsset";
    }

    @GetMapping("/edit/{assetId}")
    public String showEditForm(@PathVariable String assetId, Model model) {
        AssetMaster asset = assetMasterService.getAssetById(assetId);
        model.addAttribute("asset", asset);
//        asset
        return "Asset/editAsset";
    }

    @PostMapping("/update")
    public String updateAsset(@ModelAttribute AssetMaster asset) {
        assetMasterService.updateAsset(asset);
        return "redirect:/assets";
    }

    @GetMapping("/delete/{assetId}")
    public String deleteAsset(@PathVariable String assetId) {
        assetMasterService.deleteAsset(assetId);
        return "redirect:/assets";
    }
}