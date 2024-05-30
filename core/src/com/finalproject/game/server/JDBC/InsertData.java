package com.finalproject.game.server.JDBC;

import com.finalproject.game.client.packet.client.jdbc.RegisterPacket;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class InsertData {
    public static void register(RegisterPacket registerPacket) {
        try (Connection connection = MySqlConnection.getConnection();
             Statement statement = connection.createStatement()) {

            statement.execute("CREATE DATABASE IF NOT EXISTS dbteknob");

            String createTableSQL = "CREATE TABLE IF NOT EXISTS tbluseraccount ("
                    + "id INT AUTO_INCREMENT PRIMARY KEY, "
                    + "username VARCHAR(255) NOT NULL, "
                    + "password VARCHAR(255) NOT NULL)";
            statement.execute(createTableSQL);

            try (PreparedStatement preparedStatement = connection.prepareStatement(
                    "INSERT INTO tbluseraccount (username, password) VALUES (?, ?)"
            )) {
                preparedStatement.setString(1, registerPacket.username);
                preparedStatement.setString(2, registerPacket.password);

                int rowsInserted = preparedStatement.executeUpdate();
                if (rowsInserted > 0) {
                    System.out.println("User registered successfully");
                } else {
                    System.out.println("Failed to register user");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
