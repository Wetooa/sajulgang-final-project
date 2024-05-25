package com.finalproject.game.server.items.weapons.range.primary;

import com.finalproject.game.server.builder.item.WeaponBuilder;
import com.finalproject.game.server.entity.projectile.range.Bolt;
import com.finalproject.game.server.items.weapons.range.RangeWeapon;

public class CrossBow extends RangeWeapon {

    public CrossBow() {
        this(new WeaponBuilder());
    }

    public CrossBow(WeaponBuilder builder) {
        super((WeaponBuilder) builder.setAccuracy(1).setRange(500).setFireRate(0.5f).setName("Crossbox").setDescription("A crossbow"));
    }

    @Override
    public void doAction() {
        shoot(Bolt.class);
    }
}
