package com.finalproject.game.server.entity.projectile.range;

import com.finalproject.game.server.builder.entity.ProjectileBuilder;
import com.finalproject.game.server.entity.projectile.Projectile;

public class Bolt extends Projectile {
    public Bolt(ProjectileBuilder builder) {
        super((ProjectileBuilder) builder.setMaxSpeed(40f).setSize(1).setDamage(15));
    }
}
