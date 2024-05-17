package com.finalproject.game.server.items.weapons.melee;

import com.finalproject.game.server.builder.entity.ProjectileBuilder;
import com.finalproject.game.server.builder.item.WeaponBuilder;
import com.finalproject.game.server.entity.projectile.Projectile;
import com.finalproject.game.server.entity.projectile.melee.Slash;

public class Katana extends MeleeWeapon {

    public Katana() {
        this(new WeaponBuilder());
    }

    public Katana(WeaponBuilder builder) {
        super((WeaponBuilder) builder.setFireRate(0.3f));
    }

    @Override
    public void doAction() {
        shoot(Slash.class);
    }
}
