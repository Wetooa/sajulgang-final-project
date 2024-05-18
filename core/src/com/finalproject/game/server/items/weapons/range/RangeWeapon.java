package com.finalproject.game.server.items.weapons.range;

import com.finalproject.game.server.builder.item.WeaponBuilder;
import com.finalproject.game.server.items.weapons.Weapon;

public abstract class RangeWeapon extends Weapon {


    public RangeWeapon() {
        this(new WeaponBuilder());
    }

    public RangeWeapon(WeaponBuilder builder) {
        super(builder);
    }


}
