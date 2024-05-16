package com.finalproject.game.server.items.weapons.melee;

import com.finalproject.game.server.builder.item.WeaponBuilder;

public class Tukog extends MeleeWeapon {

    protected Tukog(WeaponBuilder builder) {
        super(builder);
    }

    @Override
    public void doAction() {
        System.out.println("Using Tukog");
    }
}
