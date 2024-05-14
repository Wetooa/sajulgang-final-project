package com.finalproject.game.server.items.weapons;

import com.finalproject.game.server.builder.item.WeaponBuilder;
import com.finalproject.game.server.items.Item;

public abstract class Weapon extends Item {

    protected final float accuracy;
    protected final float range;
    protected final float fireRate;

    protected Weapon(WeaponBuilder builder) {
        super(builder);

        this.accuracy = builder.getAccuracy();
        this.range = builder.getRange();
        this.fireRate = builder.getFireRate();
    }
}
