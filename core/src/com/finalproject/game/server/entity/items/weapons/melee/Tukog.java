package com.finalproject.game.server.entity.items.weapons.melee;

public class Tukog extends MeleeWeapon {

    protected Tukog(String name, String description) {
        super(name, description);
    }

    @Override
    public void activate() {
        System.out.println("Using Tukog");
    }
}
