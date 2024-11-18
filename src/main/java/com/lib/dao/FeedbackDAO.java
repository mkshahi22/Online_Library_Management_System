package com.lib.dao;

import com.lib.demo.DatabaseConfig;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class FeedbackDAO {
    // Add feedback
    public void addFeedback(int userId, String feedbackText) {
        String query = "INSERT INTO feedback (user_id, feedback_text, created_at) VALUES (?, ?, NOW())";
        try (Connection connection = DatabaseConfig.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, userId);
            preparedStatement.setString(2, feedbackText);
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Get all feedback
    public List<String> getAllFeedback() {
        List<String> feedbackList = new ArrayList<>();
        String query = "SELECT feedback_text FROM feedback ORDER BY created_at DESC";
        try (Connection connection = DatabaseConfig.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                feedbackList.add(resultSet.getString("feedback_text"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return feedbackList;
    }
}
