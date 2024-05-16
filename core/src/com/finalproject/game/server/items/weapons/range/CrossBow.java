package com.finalproject.game.server.items.weapons.range;

import com.finalproject.game.server.GameInstanceServer;
import com.finalproject.game.server.RemoteClient;
import com.finalproject.game.server.builder.entity.ProjectileBuilder;
import com.finalproject.game.server.builder.item.WeaponBuilder;
import com.finalproject.game.server.entity.projectile.Bolt;
import com.finalproject.game.server.entity.projectile.Bullet;

public class CrossBow extends RangeWeapon {

    public CrossBow(WeaponBuilder builder) {
        super((WeaponBuilder) builder.setFireRate(0.5f));
    }

    @Override
    public void doAction() {
        shoot(Bolt.class);
    }
}
