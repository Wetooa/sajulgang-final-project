package com.finalproject.game.server.items.weapons.range;

import com.finalproject.game.server.builder.item.WeaponBuilder;
import com.finalproject.game.server.entity.projectile.range.Shell;

import java.util.stream.IntStream;

public class ShotGun extends RangeWeapon {

    public static final int SHELLS_PER_SHOT = 10;

    public ShotGun(WeaponBuilder builder) {
        super((WeaponBuilder) builder.setFireRate(0.3f));
    }

    @Override
    public void doAction() {
        IntStream.range(0, SHELLS_PER_SHOT).mapToObj(i -> Shell.class).forEach(this::shoot);
    }
}
