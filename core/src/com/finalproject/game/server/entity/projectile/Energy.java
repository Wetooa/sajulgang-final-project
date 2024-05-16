package com.finalproject.game.server.entity.projectile;

import com.finalproject.game.server.builder.entity.ProjectileBuilder;

public class Energy  extends Projectile {

    public Energy(ProjectileBuilder builder) {
        super((ProjectileBuilder) builder.setMaxSpeed(60f));
    }

}
