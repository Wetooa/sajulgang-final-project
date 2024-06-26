package com.finalproject.game.server.items.weapons.melee;

import com.finalproject.game.server.builder.item.WeaponBuilder;
import com.finalproject.game.server.items.weapons.Weapon;

public abstract class MeleeWeapon extends Weapon {

    public MeleeWeapon() {
        this(new WeaponBuilder());
    }

    protected MeleeWeapon(WeaponBuilder builder) {
        super(builder);
    }
}
