package com.finalproject.game.server.items.weapons.range.primary;

import com.finalproject.game.server.builder.item.WeaponBuilder;
import com.finalproject.game.server.entity.projectile.range.Bullet;

public class SubmachineGun extends PrimaryGun {


    public SubmachineGun() {
        this(new WeaponBuilder());
    }

    public SubmachineGun(WeaponBuilder builder) {
        super((WeaponBuilder) builder.setAccuracy(0.40f).setRange(0.4f).setFireRate(0.2f).setName("Submachine Gun").setDescription("A submachine gun").setItemType(ItemType.SUBMACHINE_GUN));
    }

    @Override
    public void doAction() {
        shoot(Bullet.class);
        shoot(Bullet.class);
    }
}
