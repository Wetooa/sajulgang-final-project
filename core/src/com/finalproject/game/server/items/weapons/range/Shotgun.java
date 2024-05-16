package com.finalproject.game.server.items.weapons.range;

import com.finalproject.game.server.GameInstanceServer;
import com.finalproject.game.server.RemoteClient;
import com.finalproject.game.server.builder.item.WeaponBuilder;
import com.finalproject.game.server.entity.projectile.Bullet;
import com.finalproject.game.server.entity.projectile.Shell;

import java.util.stream.IntStream;

public class Shotgun extends RangeWeapon {

    public static final int SHELLS_PER_SHOT = 10;

    public Shotgun(WeaponBuilder builder) {
        super((WeaponBuilder) builder.setFireRate(0.3f));
    }

    @Override
    public void doAction() {
        IntStream.range(0, SHELLS_PER_SHOT).mapToObj(i -> Shell.class).forEach(this::shoot);
    }
}
