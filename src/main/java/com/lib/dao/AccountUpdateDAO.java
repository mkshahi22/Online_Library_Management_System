package com.lib.dao;

import com.lib.demo.DatabaseConfig;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class AccountUpdateDAO {
    // Record an account update
    public void addAccountUpdate(int userId, String updateDetails) {
        String query = "INSERT INTO account_updates (user_id, update_details, timestamp) VALUES (?, ?, NOW())";
        try (Connection connection = DatabaseConfig.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, userId);
            preparedStatement.setString(2, updateDetails);
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Get account updates for a specific user
    public List<String> getAccountUpdatesByUserId(int userId) {
        List<String> updates = new ArrayList<>();
        String query = "SELECT update_details FROM account_updates WHERE user_id = ? ORDER BY timestamp DESC";
        try (Connection connection = DatabaseConfig.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                updates.add(resultSet.getString("update_details"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return updates;
    }
}
