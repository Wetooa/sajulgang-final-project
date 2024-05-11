package com.finalproject.game.server;

import com.esotericsoftware.kryonet.Connection;
import com.finalproject.game.entities.enemies.Enemy;
import com.finalproject.game.entities.player.Player;

import java.util.ArrayList;
import java.util.HashMap;

public class GameInstance {


    private final int GAME_ID;
    private boolean isStarted = false;
    public HashMap<Connection, RemoteClient> remoteClients = new HashMap<>();


    public GameInstance(int GAME_ID, HashMap<Connection, RemoteClient> remoteClients) {
        this.GAME_ID = GAME_ID;
        this.remoteClients = remoteClients;


    }
}
