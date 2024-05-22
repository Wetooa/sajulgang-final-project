package com.finalproject.game.server.items.weapons.range.secondary;

import com.finalproject.game.server.builder.item.WeaponBuilder;

public class DesertEagle extends SecondaryGun {

    public DesertEagle() {
        this(new WeaponBuilder());
    }

    public DesertEagle(WeaponBuilder builder) {
        super(builder);
    }


    @Override
    public void doAction() {

    }
}
