package com.finalproject.game;


import com.finalproject.game.server.GameServer;

import java.io.IOException;

/**
 * Launches the server application.
 */
public class ServerLauncher {


    public static void main(String[] args) {
        try {
            new GameServer();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // TODO Implement server application.
    }
}
