package com.gpcodefusion.assetmanager.controller;

import com.gpcodefusion.assetmanager.model.ScanAssetDetailsProjection;
import com.gpcodefusion.assetmanager.service.ScanAssetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.*;

@Controller
public class ScanAssetController {

    private final ScanAssetService assetService;

    @Autowired
    public ScanAssetController(ScanAssetService assetService) {
        this.assetService = assetService;
    }

    @GetMapping("/ScanAsset")
    public String showScanPage() {
        return "viewAssetByQr";
    }

    @PostMapping("/search")
    public String searchBarcode(@RequestParam("barcode") String barcode, Model model) {
        try {
            List<ScanAssetDetailsProjection> results = assetService.getAssetDetails(barcode);

            if (results.isEmpty()) {
                model.addAttribute("errorMessage", "No data found for barcode: " + barcode);
                return "viewAssetByQr"; // Changed to match your HTML file
            }

            List<Map<String, Object>> dataList = new ArrayList<>();

            // Create headers (only once since all results have same structure)
            List<String> headers = Arrays.asList(
                    "Asset Barcode", "Asset ID", "Asset Name", "Asset Description",
                    "Asset Status", "Created On", "Asset Category", "EMP ID", "EMP Name"
            );

            // Process each result
            for (ScanAssetDetailsProjection projection : results) {
                Map<String, Object> dataMap = new LinkedHashMap<>();
                dataMap.put("barcode", projection.getAssetBarcode());
                dataMap.put("headers", headers);

                List<Object> row = Arrays.asList(
                        projection.getAssetBarcode(),
                        projection.getAssetID(),
                        projection.getAssetName(),
                        projection.getAssetDescription(),
                        projection.getAssetStatus(),
                        projection.getCreatedOn(),
                        projection.getAssetCategory(),
                        projection.getEMPID(),  // Note the change to match projection
                        projection.getEMPName()  // Note the change to match projection
                );

                dataMap.put("rows", Collections.singletonList(row));
                dataList.add(dataMap);
            }

            model.addAttribute("dataList", dataList);
            return "viewAssetByQr"; // Changed to match your HTML file
        } catch (Exception e) {
            model.addAttribute("errorMessage", "Error processing request: " + e.getMessage());
            return "viewAssetByQr";
        }
    }
}