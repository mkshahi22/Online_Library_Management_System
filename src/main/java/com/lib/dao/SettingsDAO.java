package com.lib.dao;

import com.lib.demo.DatabaseConfig;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class SettingsDAO {
    // Update a setting
    public void updateSetting(String key, String value) {
        String query = "UPDATE settings SET value = ? WHERE key_name = ?";
        try (Connection connection = DatabaseConfig.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, value);
            preparedStatement.setString(2, key);
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Get a setting value by key
    public String getSettingByKey(String key) {
        String query = "SELECT value FROM settings WHERE key_name = ?";
        try (Connection connection = DatabaseConfig.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, key);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getString("value");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
