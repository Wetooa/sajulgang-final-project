package com.finalproject.game.server.packet.server;

import com.finalproject.game.client.builder.EntityBuilder;
import com.finalproject.game.server.GameInstanceServer;
import com.finalproject.game.server.RemoteClient;
import com.finalproject.game.server.entity.live.player.Player;
import com.finalproject.game.server.entity.projectile.Projectile;

import java.util.ArrayList;
import java.util.Map;

public class GameInstanceSnapshot {

    public ArrayList<com.finalproject.game.client.entity.live.player.Player> players = new ArrayList<>();
    public ArrayList<com.finalproject.game.client.entity.live.enemy.Enemy> enemies = new ArrayList<>();
    public ArrayList<com.finalproject.game.client.entity.projectile.Projectile> projectiles = new ArrayList<>();

    public GameInstanceSnapshot() {
    }

    public GameInstanceSnapshot(GameInstanceServer gameInstance) {
        for (Map.Entry<RemoteClient, Player> player : gameInstance.players.entrySet()) {
            Player p = player.getValue();
            players.add(new com.finalproject.game.client.entity.live.player.Player(new EntityBuilder().setPos(p.getBoxBody().getPosition())));
        }

        for (Projectile p : gameInstance.projectiles) {
            projectiles.add(new com.finalproject.game.client.entity.projectile.Bullet(new EntityBuilder().setPos(p.getBoxBody().getPosition())));
        }
    }


}
