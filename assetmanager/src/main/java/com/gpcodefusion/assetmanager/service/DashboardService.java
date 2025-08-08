package com.gpcodefusion.assetmanager.service;

import com.gpcodefusion.assetmanager.model.DashAsset;
import com.gpcodefusion.assetmanager.repository.DashboardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DashboardService {

    @Autowired
    private DashboardRepository repository;

    public int getUserCount() {
        return repository.countUsers();
    }

    public int getEmployeeCount() {
        return repository.countEmployees();
    }

    public int getAssetCount() {
        return repository.countAssets();
    }

    public int getAvailableAssetCount() {
        return repository.countAvailableAssets();
    }

    public int getAssignedAssetCount() {
        return repository.countAssignedAssets();
    }

    public int getCategoryCount() {
        return repository.countCategories();
    }

    public int getDepartmentCount() {
        return repository.countDepartments();
    }

    public List<DashAsset> getTopAssets() {
        return repository.findTop5Assets();
    }
    public void deleteAssetById(String assetID) {
        repository.deleteById(assetID);
    }

}
