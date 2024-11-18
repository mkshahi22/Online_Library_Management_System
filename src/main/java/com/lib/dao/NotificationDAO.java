package com.lib.dao;

import com.lib.demo.DatabaseConfig;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class NotificationDAO {
    // Add a new notification
    public void addNotification(int userId, String message) {
        String query = "INSERT INTO notifications (user_id, message, created_at) VALUES (?, ?, NOW())";
        try (Connection connection = DatabaseConfig.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, userId);
            preparedStatement.setString(2, message);
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Get notifications for a specific user
    public List<String> getNotificationsByUserId(int userId) {
        List<String> notifications = new ArrayList<>();
        String query = "SELECT message FROM notifications WHERE user_id = ? ORDER BY created_at DESC";
        try (Connection connection = DatabaseConfig.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                notifications.add(resultSet.getString("message"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return notifications;
    }
}
