package com.finalproject.game.server.packet.server;

import com.finalproject.game.client.entity.live.enemy.Enemy;
import com.finalproject.game.client.entity.live.player.Player;
import com.finalproject.game.server.entity.projectile.Projectile;

import java.util.List;

public class GameInitialization {
    public List<Player> players;
    public List<Enemy> enemies;
    public List<Projectile> projectiles;

    public GameInitialization(List<Player> players) {
        this.players = players;
    }
}
