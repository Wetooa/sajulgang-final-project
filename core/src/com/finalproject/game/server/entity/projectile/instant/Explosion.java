package com.finalproject.game.server.entity.projectile.instant;

import com.finalproject.game.server.builder.entity.ProjectileBuilder;
import com.finalproject.game.server.entity.projectile.Projectile;

public class Explosion extends Projectile {

    public Explosion() {
        this(new ProjectileBuilder());
    }

    public Explosion(ProjectileBuilder builder) {
        super((ProjectileBuilder) builder.setRange(0.1f).setSize(3f).setMaxSpeed(0).setDamage(100));
    }
}
