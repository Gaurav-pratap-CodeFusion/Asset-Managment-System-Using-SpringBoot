package com.gpcodefusion.assetmanager.model;

import jakarta.persistence.*;

@Entity
@Table(name = "assetmaster")
public class DashAsset {

    @Id
    @Column(name = "AssetID")
    private String assetID;

    @Column(name = "AssetName")
    private String assetName;

    @Column(name = "AssetDescription")
    private String assetDescription;

    @Column(name = "AssetCategory")
    private String assetCategory;

    @Column(name = "AssetStatus")
    private String assetStatus;

    // Getters and setters
    public String getAssetID() {
        return assetID;
    }

    public void setAssetID(String assetID) {
        this.assetID = assetID;
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

    public String getAssetStatus() {
        return assetStatus;
    }

    public void setAssetStatus(String assetStatus) {
        this.assetStatus = assetStatus;
    }
}
