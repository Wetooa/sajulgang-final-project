package com.finalproject.game.server.items.weapons.melee;

import com.finalproject.game.server.builder.item.WeaponBuilder;
import com.finalproject.game.server.entity.projectile.melee.Slash;

public class Katana extends MeleeWeapon {

    public Katana() {
        this(new WeaponBuilder());
    }

    public Katana(WeaponBuilder builder) {
        super((WeaponBuilder) builder.setAccuracy(0.99f).setRange(0.1f).setFireRate(0.3f).setName("Katana").setDescription("Katana nalang").setItemType(ItemType.KATANA));
    }

    @Override
    public void doAction() {
        shoot(Slash.class);
    }
}
