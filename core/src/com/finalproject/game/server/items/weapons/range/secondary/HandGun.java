package com.finalproject.game.server.items.weapons.range.secondary;

import com.finalproject.game.server.builder.item.WeaponBuilder;
import com.finalproject.game.server.entity.projectile.range.Bullet;

// TODO: to be removed, this weapon is just a basic template
public class HandGun extends SecondaryGun {

    public HandGun() {
        this(new WeaponBuilder());
    }

    public HandGun(WeaponBuilder builder) {
        super((WeaponBuilder) builder.setAccuracy(0.80f).setRange(0.25f).setFireRate(0.6f).setItemType(ItemType.HANDGUN).setName("Handgun").setDescription("A basic handgun"));
    }

    @Override
    public void doAction() {
        shoot(Bullet.class);
    }

}
