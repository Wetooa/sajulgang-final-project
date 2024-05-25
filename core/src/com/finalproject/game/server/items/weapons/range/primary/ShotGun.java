package com.finalproject.game.server.items.weapons.range.primary;

import com.finalproject.game.server.builder.item.WeaponBuilder;
import com.finalproject.game.server.entity.projectile.range.Shell;
import com.finalproject.game.server.items.weapons.range.RangeWeapon;

import java.util.stream.IntStream;

public class ShotGun extends RangeWeapon {

    public static final int SHELLS_PER_SHOT = 10;

    public ShotGun() {
        this(new WeaponBuilder());
    }

    public ShotGun(WeaponBuilder builder) {
        super((WeaponBuilder) builder.setAccuracy(0.5f).setRange(50).setFireRate(0.3f).setName("Shotgun").setDescription("A shotgun"));
    }

    @Override
    public void doAction() {
        IntStream.range(0, SHELLS_PER_SHOT).mapToObj(i -> Shell.class).forEach(this::shoot);
    }
}
