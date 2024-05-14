package com.finalproject.game.server.items.weapons.range;

import com.finalproject.game.server.GameInstanceServer;
import com.finalproject.game.server.RemoteClient;
import com.finalproject.game.server.builder.item.WeaponBuilder;
import com.finalproject.game.server.entity.projectile.Bullet;

public class HandGun extends RangeWeapon {

    public HandGun(GameInstanceServer gameInstanceServer, RemoteClient remoteClient) {
        super((WeaponBuilder) new WeaponBuilder().setGameInstanceServer(gameInstanceServer).setRemoteClient(remoteClient));
    }

    @Override
    public void activate() {
        Bullet b = new Bullet(getGameInstanceServer(), getRemoteClient());
        gameInstanceServer.projectiles.add(b);
    }
}
