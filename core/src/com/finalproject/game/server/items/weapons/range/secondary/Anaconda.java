package com.finalproject.game.server.items.weapons.range.secondary;

import com.finalproject.game.server.builder.item.WeaponBuilder;
import com.finalproject.game.server.entity.projectile.range.Bullet;

public class Anaconda extends SecondaryGun {

    public Anaconda() {
        this(new WeaponBuilder());
    }

    public Anaconda(WeaponBuilder builder) {
        super((WeaponBuilder) builder.setAccuracy(0.95f).setRange(0.5f).setFireRate(0.7f).setName("Anaconda").setDescription("Slither'in").setItemType(ItemType.ANACONDA));
    }


    @Override
    public void doAction() {
        shoot(Bullet.class);
    }


}
