package com.finalproject.game.server.items.weapons.range;

import com.finalproject.game.server.builder.item.WeaponBuilder;
import com.finalproject.game.server.entity.projectile.range.Energy;

public class LaserGun extends RangeWeapon {

    public LaserGun() {
        this(new WeaponBuilder());
    }

    public LaserGun(WeaponBuilder builder) {
        super((WeaponBuilder) builder.setFireRate(0.01f));
    }

    @Override
    public void doAction() {
        shoot(Energy.class);
    }
}
