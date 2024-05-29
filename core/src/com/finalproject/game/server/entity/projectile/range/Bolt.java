package com.finalproject.game.server.entity.projectile.range;

import com.badlogic.gdx.math.Vector2;
import com.finalproject.game.server.builder.entity.ProjectileBuilder;
import com.finalproject.game.server.entity.projectile.Projectile;

public class Bolt extends Projectile {
    public Bolt() {
        this(new ProjectileBuilder());
    }

    public Bolt(ProjectileBuilder builder) {
        super((ProjectileBuilder) builder.setProjectileType(ProjectileType.BOLT).setMaxSpeed(1000f).setSize(new Vector2(1, 1)).setDamage(15));
    }
}
