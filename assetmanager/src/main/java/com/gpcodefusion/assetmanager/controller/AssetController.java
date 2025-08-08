package com.gpcodefusion.assetmanager.controller;

import com.gpcodefusion.assetmanager.model.AssetMaster;
import com.gpcodefusion.assetmanager.service.AssetMasterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class AssetController {

    @Autowired
    private AssetMasterService assetService;

    @GetMapping("/add-asset")
    public String showAddAssetForm(Model model) {
        AssetMaster asset = new AssetMaster();
        asset.setAssetId(assetService.generateNextAssetId());
        model.addAttribute("asset", asset);
        return "Asset/addAsset";
    }

    @PostMapping("/add-asset")
    public String saveAsset(@ModelAttribute("asset") AssetMaster asset, Model model) {
        assetService.saveAsset(asset);
        System.out.println("Status: " + asset.getAssetStatus());
        model.addAttribute("message", "Asset added successfully!");
        return "redirect:/dashboard";
    }
}
