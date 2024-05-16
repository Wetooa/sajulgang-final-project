package com.finalproject.game.server.items.weapons.range;

import com.finalproject.game.server.builder.item.WeaponBuilder;
import com.finalproject.game.server.entity.projectile.Energy;

public class LaserGun extends RangeWeapon {
    public LaserGun(WeaponBuilder builder) {
        super((WeaponBuilder) builder.setFireRate(0.01f));
    }

    @Override
    public void doAction() {
        shoot(Energy.class);
    }
}