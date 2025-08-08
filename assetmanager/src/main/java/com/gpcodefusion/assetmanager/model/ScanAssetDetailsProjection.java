package com.gpcodefusion.assetmanager.model;

import java.time.LocalDateTime;

public interface ScanAssetDetailsProjection {
    String getAssetBarcode();
    String getAssetID();
    String getAssetName();
    String getAssetDescription();
    String getAssetStatus();
    LocalDateTime getCreatedOn();
    String getAssetCategory();
    String getEMPID();
    String getEMPName();
}