package com.gpcodefusion.assetmanager.repository;

import com.gpcodefusion.assetmanager.model.AssetMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface AssetMTRepository extends JpaRepository<AssetMaster, String> {
}