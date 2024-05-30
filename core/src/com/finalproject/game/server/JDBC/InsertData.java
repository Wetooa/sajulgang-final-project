package com.finalproject.game.server.JDBC;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class InsertData {
    public static void signup(String username, String password) {
        try (Connection connection = MySqlConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "INSERT INTO tbluseraccount (username, password) VALUES (?, ?)"
             )) {
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);

            int rowsInserted = preparedStatement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("User registered successfully");
            } else {
                System.out.println("Failed to register user");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
