package com.finalproject.game.server.items.weapons.range.secondary;

import com.finalproject.game.server.builder.item.WeaponBuilder;
import com.finalproject.game.server.entity.projectile.range.Bullet;

public class DesertEagle extends SecondaryGun {

    public DesertEagle() {
        this(new WeaponBuilder());
    }

    public DesertEagle(WeaponBuilder builder) {
        super((WeaponBuilder) builder.setAccuracy(0.99f).setRange(1f).setFireRate(0.5f).setName("Desert Eagle").setDescription("Fuck").setItemType(ItemType.ANACONDA));
    }


    @Override
    public void doAction() {
        shoot(Bullet.class);
    }
}
