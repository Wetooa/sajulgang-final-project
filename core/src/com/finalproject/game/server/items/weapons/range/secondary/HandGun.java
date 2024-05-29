package com.finalproject.game.server.items.weapons.range.secondary;

import com.finalproject.game.server.builder.item.WeaponBuilder;
import com.finalproject.game.server.entity.projectile.range.Bullet;
import com.finalproject.game.server.items.weapons.range.RangeWeapon;

// TODO: to be removed, this weapon is just a basic template
public class HandGun extends RangeWeapon {

    public HandGun() {
        this(new WeaponBuilder());
    }

    public HandGun(WeaponBuilder builder) {
        super((WeaponBuilder) builder.setItemType(ItemType.HANDGUN).setName("Handgun").setDescription("A basic handgun").setFireRate(0.1f));
    }

    @Override
    public void doAction() {
        shoot(Bullet.class);
    }

}
