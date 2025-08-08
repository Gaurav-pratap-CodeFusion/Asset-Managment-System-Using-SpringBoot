package com.gpcodefusion.assetmanager.controller;

import com.gpcodefusion.assetmanager.model.AssetMappingDetails;
import com.gpcodefusion.assetmanager.repository.AssetMappingRepository;
import com.gpcodefusion.assetmanager.repository.AssetMasterRepository;
import com.gpcodefusion.assetmanager.repository.EmployeeRepository;
import com.gpcodefusion.assetmanager.service.AssetMappingService;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class AssetMappingController {

    @Autowired
    private AssetMappingService mappingService;

    @Autowired
    private EmployeeRepository employeeRepo;

    @Autowired
    private AssetMasterRepository assetRepo;

    @Autowired
    private AssetMappingRepository assetMappingRepository;

    @GetMapping("/showall")
    public String showAssetMappings(Model model) {
        List<AssetMappingDetails> mappings = assetMappingRepository.findAll();
        model.addAttribute("mappings", mappings);
        return "ShowAllBarCode";
    }

    @GetMapping("/assignAsset")
    public String showForm(Model model) {
        model.addAttribute("employees", employeeRepo.findAll());
        model.addAttribute("assets", assetRepo.findAll());
        return "employee/assign_asset";
    }

    @PostMapping("/assignAsset")
    public String assignAsset(@RequestParam String empId, @RequestParam String assetId, RedirectAttributes redirectAttributes) {
        try {
            mappingService.saveMapping(empId, assetId);
            redirectAttributes.addFlashAttribute("success", "Asset assigned successfully!");
        } catch (IllegalStateException ex) {
            redirectAttributes.addFlashAttribute("error", ex.getMessage());
        }
        return "redirect:/assignAsset";
    }

}

