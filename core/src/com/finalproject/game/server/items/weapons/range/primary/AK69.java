package com.finalproject.game.server.items.weapons.range.primary;

import com.finalproject.game.server.builder.item.WeaponBuilder;
import com.finalproject.game.server.entity.projectile.range.Bullet;

public class AK69 extends PrimaryGun {


    public AK69() {
        this(new WeaponBuilder());
    }

    public AK69(WeaponBuilder builder) {
        super((WeaponBuilder) builder.setAccuracy(0.80f).setRange(1).setFireRate(0.4f).setName("AK69").setDescription("Will penetrate deeply").setItemType(ItemType.AK69));
    }

    @Override
    public void doAction() {
        shoot(Bullet.class);
    }
}
