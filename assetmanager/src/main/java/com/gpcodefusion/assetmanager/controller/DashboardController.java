package com.gpcodefusion.assetmanager.controller;

import com.gpcodefusion.assetmanager.model.DashAsset;
import com.gpcodefusion.assetmanager.service.DashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class DashboardController {

    @Autowired
    private DashboardService dashboardService;

    @GetMapping("/dashboard")
    public String showDashboard(Model model) {
        model.addAttribute("userCount", dashboardService.getUserCount());
        model.addAttribute("employeeCount", dashboardService.getEmployeeCount());
        model.addAttribute("assetCount", dashboardService.getAssetCount());
        model.addAttribute("availableCount", dashboardService.getAvailableAssetCount());
        model.addAttribute("assignedCount", dashboardService.getAssignedAssetCount());
        model.addAttribute("categoryCount", dashboardService.getCategoryCount());
        model.addAttribute("departmentCount", dashboardService.getDepartmentCount());

        List<DashAsset> topAssets = dashboardService.getTopAssets();
        model.addAttribute("topAssets", topAssets);

        return "dashboard/dash";
    }



    @GetMapping("/dashboard/delete/{assetID}")
    public String deleteAsset(@PathVariable("assetID") String assetID) {
        dashboardService.deleteAssetById(assetID);
        return "redirect:/dashboard";
    }


}
