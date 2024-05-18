package com.finalproject.game.server.items.weapons.instant;

import com.finalproject.game.server.builder.item.WeaponBuilder;

public class Grenade extends InstantWeapon {

    public Grenade() {
        this(new WeaponBuilder());
    }

    public Grenade(WeaponBuilder builder) {
        super(builder);
    }

    @Override
    public void doAction() {

    }
}
