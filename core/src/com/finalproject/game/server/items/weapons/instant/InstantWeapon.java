package com.finalproject.game.server.items.weapons.instant;

import com.finalproject.game.server.builder.item.WeaponBuilder;
import com.finalproject.game.server.items.weapons.Weapon;

public abstract class InstantWeapon extends Weapon {
    protected InstantWeapon(WeaponBuilder builder) {
        super(builder);
    }
}
