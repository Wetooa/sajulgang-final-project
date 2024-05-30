package com.finalproject.game.server.JDBC;

import java.sql.*;

public class ReadData {
    public static boolean login(String username, String password) {
        try (Connection connection = MySqlConnection.getConnection()) {
            String sql = "SELECT * FROM tbluseraccount WHERE username = ? AND password = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, username);
                statement.setString(2, password);
                try (ResultSet resultSet = statement.executeQuery()) {
                    return resultSet.next(); // Returns true if user exists
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
