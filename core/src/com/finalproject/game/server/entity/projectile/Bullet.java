package com.finalproject.game.server.entity.projectile;

import com.finalproject.game.server.GameInstanceServer;
import com.finalproject.game.server.RemoteClient;
import com.finalproject.game.server.builder.entity.ProjectileBuilder;

public class Bullet extends Projectile {

    public Bullet(GameInstanceServer gameInstanceServer, RemoteClient remoteClient) {
        super((ProjectileBuilder) new ProjectileBuilder().setGameInstanceServer(gameInstanceServer).setRemoteClient(remoteClient));
    }
}
