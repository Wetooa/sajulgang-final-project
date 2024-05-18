package com.finalproject.game.server.entity.projectile.melee;

import com.finalproject.game.server.builder.entity.ProjectileBuilder;
import com.finalproject.game.server.entity.projectile.Projectile;

public class Slash extends Projectile {
    public Slash() {
        this(new ProjectileBuilder());
    }

    public Slash(ProjectileBuilder builder) {
        super((ProjectileBuilder) builder.setRange(0.2f).setSize(4f).setMaxSpeed(10).setDamage(100));
    }
}
