package com.gpcodefusion.assetmanager.repository;

import com.gpcodefusion.assetmanager.model.DashAsset;
import jakarta.transaction.Transactional;
import org.eclipse.angus.mail.imap.protocol.ID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DashboardRepository extends JpaRepository<DashAsset, String> {

    @Query(value = "SELECT COUNT(*) FROM usersdetails", nativeQuery = true)
    int countUsers();

    @Query(value = "SELECT COUNT(*) FROM employee_details", nativeQuery = true)
    int countEmployees();

    @Query(value = "SELECT COUNT(*) FROM assetmaster", nativeQuery = true)
    int countAssets();

    @Query(value = "SELECT COUNT(*) FROM assetmaster WHERE AssetStatus = 'Available'", nativeQuery = true)
    int countAvailableAssets();

    @Query(value = "SELECT COUNT(*) FROM assetmaster WHERE AssetStatus = 'Assigned'", nativeQuery = true)
    int countAssignedAssets();

    @Query(value = "SELECT COUNT(DISTINCT AssetCategory) FROM assetmaster", nativeQuery = true)
    int countCategories();

    @Query(value = "SELECT COUNT(*) FROM departmentmaster", nativeQuery = true)
    int countDepartments();

    @Query(value = "SELECT * FROM assetmaster ORDER BY AssetID ASC LIMIT 5", nativeQuery = true)
    List<DashAsset> findTop5Assets();




}
