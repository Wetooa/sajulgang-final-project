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
        this.remoteClient = remoteClient;
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
