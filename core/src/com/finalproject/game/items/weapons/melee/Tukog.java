package com.finalproject.game.items.weapons.melee;

import com.finalproject.game.items.weapons.Weapon;

public class Tukog extends MeleeWeapon {

    protected Tukog(String name, String description) {
        super(name, description);
    }

    @Override
    public void activate() {
        System.out.println("Using Tukog");
    }
}
