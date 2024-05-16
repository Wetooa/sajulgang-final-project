package com.finalproject.game.server.items.weapons.range;

import com.finalproject.game.server.GameInstanceServer;
import com.finalproject.game.server.RemoteClient;
import com.finalproject.game.server.builder.item.WeaponBuilder;
import com.finalproject.game.server.entity.projectile.Bullet;

public class Handgun extends RangeWeapon {

    public Handgun(WeaponBuilder builder) {
        super((WeaponBuilder) builder.setFireRate(0.1f));
    }

    @Override
    public void doAction() {
        shoot(Bullet.class);
    }

}
