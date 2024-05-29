package com.finalproject.game.server.items.weapons.range.primary;

import com.finalproject.game.server.builder.item.WeaponBuilder;
import com.finalproject.game.server.entity.projectile.range.Bolt;

public class CrossBow extends PrimaryGun {

    public CrossBow() {
        this(new WeaponBuilder());
    }

    public CrossBow(WeaponBuilder builder) {
        super((WeaponBuilder) builder.setAccuracy(0.99f).setRange(5).setFireRate(0.5f).setName("Crossbow").setDescription("A crossbow").setItemType(ItemType.CROSSBOW));
    }

    @Override
    public void doAction() {
        shoot(Bolt.class);
    }
}
