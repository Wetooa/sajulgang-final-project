package com.finalproject.game.server.items.weapons.range.primary;

import com.finalproject.game.server.builder.item.WeaponBuilder;
import com.finalproject.game.server.entity.projectile.range.Energy;
import com.finalproject.game.server.items.weapons.range.RangeWeapon;

public class LaserGun extends RangeWeapon {

    public LaserGun() {
        this(new WeaponBuilder());
    }

    public LaserGun(WeaponBuilder builder) {
        super((WeaponBuilder) builder.setRange(1000000).setFireRate(0.05f));
    }

    @Override
    public void doAction() {
        shoot(Energy.class);
    }
}
