package com.finalproject.game.server.items.weapons.range.primary;

import com.finalproject.game.server.builder.item.WeaponBuilder;

public class AK69 extends PrimaryGun {


    public AK69() {
        this(new WeaponBuilder());
    }

    public AK69(WeaponBuilder builder) {
        super((WeaponBuilder) builder.setAccuracy(0.80f).setRange(100).setFireRate(0.3f).setName("AK69").setDescription("Will penetrate deeply"));
    }

    @Override
    public void doAction() {

    }
}
