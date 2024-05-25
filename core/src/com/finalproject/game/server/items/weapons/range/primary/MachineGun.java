package com.finalproject.game.server.items.weapons.range.primary;

import com.finalproject.game.server.builder.item.WeaponBuilder;
import com.finalproject.game.server.entity.projectile.range.Bullet;

public class MachineGun extends PrimaryGun {

    public MachineGun() {
        this(new WeaponBuilder());
    }

    public MachineGun(WeaponBuilder builder) {
        super((WeaponBuilder) builder.setAccuracy(0.70f).setRange(30).setFireRate(0.1f).setName("Machine gun").setDescription("A machine gun"));
    }

    @Override
    public void doAction() {
        shoot(Bullet.class);
    }
}
