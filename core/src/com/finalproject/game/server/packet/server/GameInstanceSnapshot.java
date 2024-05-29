package com.finalproject.game.server.packet.server;

import com.finalproject.game.server.GameInstanceServer;
import com.finalproject.game.server.RemoteClient;
import com.finalproject.game.server.entity.live.player.Player;
import com.finalproject.game.server.entity.projectile.Projectile;

import java.util.ArrayList;
import java.util.List;

public class GameInstanceSnapshot {

    public RemoteClient remoteClient;

    public List<Player> players = new ArrayList<>();
    public List<Projectile> projectiles = new ArrayList<>();

    public GameInstanceSnapshot() {
    }

    public GameInstanceSnapshot(GameInstanceServer gameInstance, RemoteClient remoteClient) {
        this.players = gameInstance.players;
        this.projectiles = gameInstance.projectiles;

        if (players.size() > 0) {
            System.out.println(players.get(0).getFacingDirection());
        }

        this.remoteClient = remoteClient;
    }


}
