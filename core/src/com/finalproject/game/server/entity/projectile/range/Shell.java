package com.finalproject.game.server.entity.projectile.range;

import com.badlogic.gdx.math.Vector2;
import com.finalproject.game.server.builder.entity.ProjectileBuilder;
import com.finalproject.game.server.entity.projectile.Projectile;

public class Shell extends Projectile {

    public Shell() {
        this(new ProjectileBuilder());
    }

    public Shell(ProjectileBuilder builder) {
        super((ProjectileBuilder) builder.setProjectileType(ProjectileType.SHELL).setMaxSpeed(100).setSize(new Vector2(0.5f, 0.5f)));
    }
}