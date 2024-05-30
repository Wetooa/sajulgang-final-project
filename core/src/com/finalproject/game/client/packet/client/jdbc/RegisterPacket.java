package com.finalproject.game.client.packet.client.jdbc;

public class RegisterPacket {

    public String username;
    public String password;

    public RegisterPacket() {
    }

    public RegisterPacket(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
