package com.finalproject.game.server.packet.server;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.finalproject.game.client.builder.EntityBuilder;
import com.finalproject.game.client.entity.Entity;
import com.finalproject.game.server.GameInstanceServer;
import com.finalproject.game.server.RemoteClient;
import com.finalproject.game.server.entity.live.player.Player;
import com.finalproject.game.server.items.ItemBox;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

public class GameInstanceSnapshot {

    public Vector2 playerPos = new Vector2(0, 0);
    public ItemBox itemBox = new ItemBox();

    public List<com.finalproject.game.client.entity.live.player.Player> players = new ArrayList<>();
    public List<com.finalproject.game.client.entity.live.enemy.Enemy> enemies = new ArrayList<>();
    public List<com.finalproject.game.client.entity.projectile.Projectile> projectiles = new ArrayList<>();

    public GameInstanceSnapshot() {
    }

    public GameInstanceSnapshot(GameInstanceServer gameInstance, RemoteClient remoteClient) {
        getClientData(com.finalproject.game.client.entity.live.player.Player.class, players, gameInstance.players);
        getClientData(com.finalproject.game.client.entity.live.enemy.Enemy.class, enemies, gameInstance.enemies);
        getClientData(com.finalproject.game.client.entity.projectile.Projectile.class, projectiles, gameInstance.projectiles);

        Player p = remoteClient.getPlayer();

        playerPos = p.getBoxBody().getPosition();
        itemBox = p.getItemBox();

    }

    public <T extends Entity> void getClientData(Class<T> c, List<T> storage, List<? extends com.finalproject.game.server.entity.Entity> bodies) {
        for (com.finalproject.game.server.entity.Entity p : bodies) {
            Body body = p.getBoxBody();

            try {
                Constructor<T> constructor = c.getConstructor(EntityBuilder.class);
                storage.add(constructor.newInstance(new EntityBuilder().setPos(body.getPosition()).setSizeX(p.getSizeX()).setSizeY(p.getSizeY())));
            } catch (NoSuchMethodException | InstantiationException | IllegalAccessException |
                     InvocationTargetException e) {
                e.printStackTrace();
            }

        }
    }


}
