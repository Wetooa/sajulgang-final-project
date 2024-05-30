package com.finalproject.game.server.items.weapons.range.secondary;

import com.finalproject.game.server.builder.item.WeaponBuilder;
import com.finalproject.game.server.entity.projectile.range.Bullet;

public class TwinPistols extends SecondaryGun {
    public TwinPistols() {
        this(new WeaponBuilder());
    }

    public TwinPistols(WeaponBuilder builder) {
        super((WeaponBuilder) builder.setAccuracy(0.95f).setRange(0.5f).setFireRate(0.7f).setName("Twin Pistols").setDescription("Mista").setItemType(ItemType.ANACONDA));
    }

    @Override
    public void doAction() {
        shoot(Bullet.class);
        shoot(Bullet.class);
    }
}
