package com.finalproject.game.client.packet.client.jdbc;

public class LoginPacket {
    public String username, password;

    public LoginPacket() {
    }

    public LoginPacket(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
