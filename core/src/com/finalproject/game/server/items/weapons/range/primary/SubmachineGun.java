package com.finalproject.game.server.items.weapons.range.primary;

import com.finalproject.game.server.builder.item.WeaponBuilder;

public class SubmachineGun extends PrimaryGun {


    public SubmachineGun() {
        this(new WeaponBuilder());
    }

    public SubmachineGun(WeaponBuilder builder) {
        super((WeaponBuilder) builder.setAccuracy(0.40f).setRange(20).setFireRate(0.1f).setName("Submachine Gun").setDescription("A submachine gun"));
    }

    @Override
    public void doAction() {

    }
}
