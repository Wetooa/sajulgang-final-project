package com.finalproject.game.server.packet.server;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.finalproject.game.client.builder.EntityBuilder;
import com.finalproject.game.client.builder.PlayerBuilder;
import com.finalproject.game.server.GameInstanceServer;
import com.finalproject.game.server.RemoteClient;
import com.finalproject.game.server.entity.live.player.Player;
import com.finalproject.game.server.items.ItemBox;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class GameInstanceSnapshot {

    public Vector2 playerPos = new Vector2(0, 0);
    public ItemBox itemBox = new ItemBox();

    public int playerHealth;
    public int playerMaxHealth;

    public int playerStamina;
    public int playerMaxStamina;

    public RemoteClient.ClientState clientState;
    public RemoteClient.ClientGameState clientGameState;
    public int respawnTimer = 0;

    public List<com.finalproject.game.client.entity.live.player.Player> players = new ArrayList<>();
    public List<com.finalproject.game.client.entity.live.enemy.Enemy> enemies = new ArrayList<>();
    public List<com.finalproject.game.client.entity.projectile.Projectile> projectiles = new ArrayList<>();

    public GameInstanceSnapshot() {
    }

    public GameInstanceSnapshot(GameInstanceServer gameInstance, RemoteClient remoteClient) {

        players = gameInstance.players.stream().map(p -> {
            Body body = p.getBoxBody();
            return new com.finalproject.game.client.entity.live.player.Player((PlayerBuilder) new PlayerBuilder().setPlayerState(p.getPlayerState()).setPos(body.getPosition()).setSizeX(p.getSizeX()).setSizeY(p.getSizeY()).setFacingDirection(p.getFacingDirection()).setStateTime(p.getStateTime()));
        }).collect(Collectors.toList());

        projectiles = gameInstance.projectiles.stream().map(p -> {
            Body body = p.getBoxBody();
            return new com.finalproject.game.client.entity.projectile.Projectile(new EntityBuilder().setPos(body.getPosition()).setSizeX(p.getSizeX()).setSizeY(p.getSizeY()).setStateTime(p.getStateTime()));
        }).collect(Collectors.toList());

        Player p = remoteClient.getPlayer();

        clientState = remoteClient.getClientState();
        clientGameState = remoteClient.getClientGameState();

        if (p != null) {
            playerPos = clientGameState.equals(RemoteClient.ClientGameState.ALIVE) ? p.getBoxBody().getPosition() : null;

            itemBox = p.getItemBox();

            playerHealth = p.getCurrentHealth();
            playerMaxHealth = p.getMaxHealth();
            playerStamina = p.getCurrentStamina();
            playerMaxStamina = p.getMaxStamina();

        }


    }

//    public <T extends Entity> void getClientData(Class<T> c, List<T> storage, List<? extends com.finalproject.game.server.entity.Entity> bodies) {
//        for (com.finalproject.game.server.entity.Entity p : bodies) {
//            Body body = p.getBoxBody();
//
//            try {
//                Constructor<T> constructor = c.getConstructor(EntityBuilder.class);
//                storage.add(constructor.newInstance(new EntityBuilder().setPos(body.getPosition()).setSizeX(p.getSizeX()).setSizeY(p.getSizeY())).setFacingDirection(p.get));
//            } catch (NoSuchMethodException | InstantiationException | IllegalAccessException |
//                     InvocationTargetException e) {
//                e.printStackTrace();
//            }
//
//        }
//    }


}
