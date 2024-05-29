package com.finalproject.game.server.items.weapons.range.primary;

import com.finalproject.game.server.builder.item.WeaponBuilder;
import com.finalproject.game.server.entity.projectile.range.Energy;

public class LaserGun extends PrimaryGun {

    public LaserGun() {
        this(new WeaponBuilder());
    }

    public LaserGun(WeaponBuilder builder) {
        super((WeaponBuilder) builder.setAccuracy(0.95f).setRange(1).setFireRate(0.03f).setName("Laser gun").setDescription("A laser gun").setItemType(ItemType.LASER_GUN));
    }

    @Override
    public void doAction() {
        shoot(Energy.class);
    }
}
