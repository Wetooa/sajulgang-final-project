package com.finalproject.game.server;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.esotericsoftware.kryonet.Connection;
import com.finalproject.game.server.builder.EntityBuilder;
import com.finalproject.game.server.entities.live.enemies.Enemy;
import com.finalproject.game.server.entities.live.player.Player;
import com.finalproject.game.server.entities.projectile.Projectile;
import com.finalproject.game.server.packet.server.GameInstanceSnapshot;

import java.util.ArrayList;
import java.util.HashMap;

public class GameInstanceServer {


    private final int GAME_ID;
    private boolean isStarted = false;
    public HashMap<Connection, RemoteClient> remoteClients = new HashMap<>();

    public HashMap<RemoteClient, Player> players = new HashMap<>();
    public ArrayList<Projectile> projectiles;
    public ArrayList<Enemy> enemies;

    public World world;

    public GameInstanceServer(int GAME_ID, HashMap<Connection, RemoteClient> remoteClients) {
        this.GAME_ID = GAME_ID;
        this.remoteClients = remoteClients;
        this.world = new World(new Vector2(0, 0), true);
    }


    public void update() {
        world.step(1 / 60f, 6, 2);

        GameInstanceSnapshot gameInstanceSnapshot = new GameInstanceSnapshot(this);
        remoteClients.keySet().forEach(connection -> connection.sendTCP(gameInstanceSnapshot));
    }


    public void addRemoteClient(Connection connection, RemoteClient client) {
        remoteClients.put(connection, client);
        players.put(client, new Player(new EntityBuilder().setGameInstance(this)));
        client.setGameID(this.GAME_ID);
    }

}
