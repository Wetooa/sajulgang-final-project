package com.finalproject.game.server.entity.projectile.range;

import com.badlogic.gdx.math.Vector2;
import com.finalproject.game.server.builder.entity.ProjectileBuilder;
import com.finalproject.game.server.entity.projectile.Projectile;

public class Energy extends Projectile {

    public Energy() {
        this(new ProjectileBuilder());
    }

    public Energy(ProjectileBuilder builder) {
        super((ProjectileBuilder) builder.setProjectileType(ProjectileType.ENERGY).setMaxSpeed(500).setSize(new Vector2(0.5f, 0.5f)));
    }

}