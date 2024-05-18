package com.finalproject.game.server.entity.projectile.range;

import com.finalproject.game.server.builder.entity.ProjectileBuilder;
import com.finalproject.game.server.entity.projectile.Projectile;

public class Shell extends Projectile {

    public Shell() {
        this(new ProjectileBuilder());
    }

    public Shell(ProjectileBuilder builder) {
        super((ProjectileBuilder) builder.setMaxSpeed(15f));
    }
}