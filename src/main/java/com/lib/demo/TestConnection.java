package com.lib.demo;

import java.sql.Connection;

public class TestConnection {

    public static void main(String[] args) {
        try {
            Connection connection = DatabaseConfig.getConnection();
            if (connection != null) {
                System.out.println("Connection Successful!");
                connection.close();
            } else {
                System.out.println("Failed to connect to the database.");
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
