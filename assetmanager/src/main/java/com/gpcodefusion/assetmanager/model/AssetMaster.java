package com.gpcodefusion.assetmanager.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "assetmaster")
public class AssetMaster {

    @Id
    @Column(name = "AssetID")
    private String assetId;

    @Column(name = "assetName")
    private String assetName;

    @Column(name = "AssetDescription")
    private String assetDescription;

    @Column(name = "AssetCategory")
    private String assetCategory;

    @Column(name = "AssetStatus")
    private String assetStatus;

    // Getters and Setters

    public String getAssetStatus() {
        return assetStatus;
    }

    public void setAssetStatus(String assetStatus) {
        this.assetStatus = assetStatus;
    }



    public String getAssetId() {
        return assetId;
    }

    public void setAssetId(String assetId) {
        this.assetId = assetId;
    }

    public String getAssetName() {
        return assetName;
    }

    public void setAssetName(String assetName) {
        this.assetName = assetName;
    }

    public String getAssetDescription() {
        return assetDescription;
    }

    public void setAssetDescription(String assetDescription) {
        this.assetDescription = assetDescription;
    }

    public String getAssetCategory() {
        return assetCategory;
    }

    public void setAssetCategory(String assetCategory) {
        this.assetCategory = assetCategory;
    }
}
