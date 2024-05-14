package com.finalproject.game.server;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.esotericsoftware.kryonet.Connection;
import com.finalproject.game.server.builder.entity.EntityBuilder;
import com.finalproject.game.server.builder.entity.LiveEntityBuilder;
import com.finalproject.game.server.entity.live.enemy.Enemy;
import com.finalproject.game.server.entity.live.player.Player;
import com.finalproject.game.server.entity.projectile.Projectile;
import com.finalproject.game.server.packet.server.GameInstanceSnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class GameInstanceServer {


    // TODO: add current state of game, maybe paused or some thing


    private final int GAME_ID;
    public HashMap<Connection, RemoteClient> remoteClientsInServer = new HashMap<>();

    public HashMap<RemoteClient, Player> players = new HashMap<>();
    public List<Projectile> projectiles = new ArrayList<>();
    public List<Enemy> enemies;

    public World world;

    public GameInstanceServer(int GAME_ID, HashMap<Connection, RemoteClient> remoteClients) {
        this.GAME_ID = GAME_ID;
        this.remoteClientsInServer = remoteClients;
        this.world = new World(new Vector2(0, 0), true);
    }


    public void update() {
        remoteClientsInServer.values().forEach(RemoteClient::update);

        world.step(1 / 60f, 6, 2);

        GameInstanceSnapshot gameInstanceSnapshot = new GameInstanceSnapshot(this);
        remoteClientsInServer.keySet().forEach(connection -> connection.sendTCP(gameInstanceSnapshot));
    }


    public void addRemoteClient(Connection connection, RemoteClient remoteClient) {
        Player p = new Player((LiveEntityBuilder) new LiveEntityBuilder().setGameInstanceServer(this).setRemoteClient(remoteClient));

        remoteClientsInServer.put(connection, remoteClient);
        players.put(remoteClient, p);

        remoteClient.setPlayer(p);
        remoteClient.setCurrentGameID(this.GAME_ID);
    }

}
