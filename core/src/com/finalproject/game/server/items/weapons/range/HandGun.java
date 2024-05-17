package com.finalproject.game.server.items.weapons.range;

import com.finalproject.game.server.builder.item.WeaponBuilder;
import com.finalproject.game.server.entity.projectile.range.Bullet;

public class HandGun extends RangeWeapon {

    public HandGun(WeaponBuilder builder) {
        super((WeaponBuilder) builder.setFireRate(0.1f));
    }

    @Override
    public void doAction() {
        shoot(Bullet.class);
    }

}
