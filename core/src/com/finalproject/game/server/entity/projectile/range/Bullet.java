package com.finalproject.game.server.entity.projectile.range;

import com.finalproject.game.server.builder.entity.ProjectileBuilder;
import com.finalproject.game.server.entity.projectile.Projectile;

public class Bullet extends Projectile {

    public Bullet(ProjectileBuilder builder) {
        super((ProjectileBuilder) builder.setProjectileType(ProjectileType.BULLET).setMaxSpeed(10f));
    }
}
