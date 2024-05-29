package com.finalproject.game.server.items.weapons.range.primary;

import com.finalproject.game.server.builder.item.WeaponBuilder;
import com.finalproject.game.server.entity.projectile.range.Bullet;

public class MachineGun extends PrimaryGun {

    public MachineGun() {
        this(new WeaponBuilder());
    }

    public MachineGun(WeaponBuilder builder) {
        super((WeaponBuilder) builder.setAccuracy(0.60f).setRange(0.8f).setFireRate(0.05f).setName("Machine gun").setDescription("A machine gun").setItemType(ItemType.MACHINE_GUN));
    }

    @Override
    public void doAction() {
        shoot(Bullet.class);
    }
}
