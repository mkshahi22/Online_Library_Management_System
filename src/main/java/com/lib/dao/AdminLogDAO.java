package com.lib.dao;

import com.lib.demo.DatabaseConfig;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class AdminLogDAO {
    // Add an admin log entry
    public void addAdminLog(int adminId, String action) {
        String query = "INSERT INTO admin_logs (admin_id, action, timestamp) VALUES (?, ?, NOW())";
        try (Connection connection = DatabaseConfig.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, adminId);
            preparedStatement.setString(2, action);
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Get all admin logs
    public List<String> getAllLogs() {
        List<String> logs = new ArrayList<>();
        String query = "SELECT CONCAT('Admin ', admin_id, ': ', action, ' at ', timestamp) AS log_entry FROM admin_logs ORDER BY timestamp DESC";
        try (Connection connection = DatabaseConfig.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                logs.add(resultSet.getString("log_entry"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return logs;
    }
}
