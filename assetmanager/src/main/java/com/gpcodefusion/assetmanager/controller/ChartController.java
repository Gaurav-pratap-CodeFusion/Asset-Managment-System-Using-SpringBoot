package com.gpcodefusion.assetmanager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import javax.sql.DataSource;
import java.sql.*;
import java.util.*;

@RestController
@RequestMapping("/api/charts")
public class ChartController {

    @Autowired
    private DataSource dataSource;

    private final ObjectMapper mapper = new ObjectMapper();

    @PostMapping("/asset-category")
    public List<Map<String, Object>> getChartData() {
        List<Map<String, Object>> chartData = new ArrayList<>();

        String query = "SELECT AssetCategory, COUNT(*) AS AssetCount FROM AssetMaster GROUP BY AssetCategory";

        try (Connection con = dataSource.getConnection();
             PreparedStatement ps = con.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Map<String, Object> row = new HashMap<>();
                row.put("AssetCategory", rs.getString("AssetCategory"));
                row.put("AssetCount", rs.getInt("AssetCount"));
                chartData.add(row);
            }
        } catch (Exception e) {
            Map<String, Object> error = new HashMap<>();
            error.put("error", "Error: " + e.getMessage());
            chartData.add(error);
        }

        return chartData;
    }

    @PostMapping("/asset-status")
    public List<Map<String, Object>> getAssetStatusData() {
        List<Map<String, Object>> statusData = new ArrayList<>();

        String query = "SELECT AssetStatus, COUNT(*) AS StatusCount FROM AssetMaster GROUP BY AssetStatus";

        try (Connection con = dataSource.getConnection();
             PreparedStatement ps = con.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Map<String, Object> row = new HashMap<>();
                row.put("AssetStatus", rs.getString("AssetStatus"));
                row.put("StatusCount", rs.getInt("StatusCount"));
                statusData.add(row);
            }
        } catch (Exception e) {
            Map<String, Object> error = new HashMap<>();
            error.put("error", "Error: " + e.getMessage());
            statusData.add(error);
        }

        return statusData;
    }
}
