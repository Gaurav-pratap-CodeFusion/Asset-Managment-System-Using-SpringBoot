package com.gpcodefusion.assetmanager.model;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "AssetMappingDetails")
public class AssetMappingDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MappingID") // Make sure your DB has this column as PK, auto-increment
    private Long mappingId;

    @Column(name = "EMPID")
    private String empId;

    @Column(name = "AssetID")
    private String assetId;

    @Column(name = "AssetBarcode")
    private String barcode;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "CreatedOn")
    private Date mappedDate;



    // Getters and setters

    public Long getMappingId() {
        return mappingId;
    }

    public void setMappingId(Long mappingId) {
        this.mappingId = mappingId;
    }

    public String getEmpId() {
        return empId;
    }

    public void setEmpId(String empId) {
        this.empId = empId;
    }

    public String getAssetId() {
        return assetId;
    }

    public void setAssetId(String assetId) {
        this.assetId = assetId;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public Date getMappedDate() {
        return mappedDate;
    }

    public void setMappedDate(Date mappedDate) {
        this.mappedDate = mappedDate;
    }
}
