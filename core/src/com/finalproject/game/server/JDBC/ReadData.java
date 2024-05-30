package com.finalproject.game.server.JDBC;

import com.finalproject.game.client.packet.client.jdbc.LoginPacket;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ReadData {
    public static String login(LoginPacket loginPacket) {
        try (Connection connection = MySqlConnection.getConnection()) {
            String sql = "SELECT * FROM tbluseraccount WHERE username = ? AND password = ?";

            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, loginPacket.username);
                statement.setString(2, loginPacket.password);

                try (ResultSet resultSet = statement.executeQuery()) {
                    return resultSet.getString("userId");
                }

            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
