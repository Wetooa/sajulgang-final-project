package com.finalproject.game.server.items.weapons.range;

import com.finalproject.game.server.builder.item.WeaponBuilder;
import com.finalproject.game.server.entity.projectile.range.Bolt;

public class CrossBow extends RangeWeapon {

    public CrossBow() {
        this(new WeaponBuilder());
    }

    public CrossBow(WeaponBuilder builder) {
        super((WeaponBuilder) builder.setFireRate(0.5f));
    }

    @Override
    public void doAction() {
        shoot(Bolt.class);
    }
}
