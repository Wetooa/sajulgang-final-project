package com.finalproject.game.server.packet.server;

import com.badlogic.gdx.physics.box2d.Body;
import com.finalproject.game.client.builder.EntityBuilder;
import com.finalproject.game.client.entity.Entity;
import com.finalproject.game.server.GameInstanceServer;
import com.finalproject.game.server.RemoteClient;
import com.finalproject.game.server.entity.live.enemy.Enemy;
import com.finalproject.game.server.entity.live.player.Player;
import com.finalproject.game.server.entity.projectile.Projectile;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class GameInstanceSnapshot {

    public List<com.finalproject.game.client.entity.live.player.Player> players = new ArrayList<>();
    public List<com.finalproject.game.client.entity.live.enemy.Enemy> enemies = new ArrayList<>();
    public List<com.finalproject.game.client.entity.projectile.Projectile> projectiles = new ArrayList<>();

    public GameInstanceSnapshot() {
    }

    public <T extends Entity> void getClientData(Class<T> c, List<T> storage, List<? extends com.finalproject.game.server.entity.Entity> bodies) {
        for (com.finalproject.game.server.entity.Entity p : bodies) {
            Body body = p.getBoxBody();

            try {
                Constructor<T> constructor = c.getConstructor(EntityBuilder.class);
                storage.add(constructor.newInstance(new EntityBuilder().setPos(body.getPosition()).setSizeX(p.getSizeX()).setSizeY(p.getSizeY())));
            } catch (NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }

        }
    }

    public GameInstanceSnapshot(GameInstanceServer gameInstance) {
        getClientData(com.finalproject.game.client.entity.live.player.Player.class, players, gameInstance.players);
        getClientData(com.finalproject.game.client.entity.live.enemy.Enemy.class, enemies, gameInstance.enemies);
        getClientData(com.finalproject.game.client.entity.projectile.Projectile.class, projectiles, gameInstance.projectiles);
    }


}
