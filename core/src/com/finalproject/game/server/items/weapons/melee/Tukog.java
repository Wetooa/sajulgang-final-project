package com.finalproject.game.server.items.weapons.melee;

import com.finalproject.game.server.builder.item.WeaponBuilder;
import com.finalproject.game.server.entity.projectile.melee.Thrust;

public class Tukog extends MeleeWeapon {

    public Tukog(WeaponBuilder builder) {
        super((WeaponBuilder) builder.setFireRate(0.1f));
    }

    @Override
    public void doAction() {
        shoot(Thrust.class);
    }
}
