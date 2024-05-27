package com.finalproject.game.server.entity.projectile.range;

import com.finalproject.game.server.builder.entity.ProjectileBuilder;
import com.finalproject.game.server.entity.projectile.Projectile;

public class Energy extends Projectile {

    public Energy() {
        this(new ProjectileBuilder());
    }

    public Energy(ProjectileBuilder builder) {
        super((ProjectileBuilder) builder.setMaxSpeed(50).setSize(0.5f));
    }

}