package com.finalproject.game.server.items.weapons.instant;

import com.finalproject.game.server.builder.item.WeaponBuilder;
import com.finalproject.game.server.items.weapons.Weapon;

public abstract class InstantWeapon extends Weapon {
    public InstantWeapon() {
        this(new WeaponBuilder());
    }

    public InstantWeapon(WeaponBuilder builder) {
        super(builder);
    }
}
