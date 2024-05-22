package com.finalproject.game.server.items.weapons.range.secondary;

import com.finalproject.game.server.builder.item.WeaponBuilder;

public class TwinPistols extends SecondaryGun {
    public TwinPistols() {
        this(new WeaponBuilder());
    }

    public TwinPistols(WeaponBuilder builder) {
        super(builder);
    }

    @Override
    public void doAction() {

    }
}
