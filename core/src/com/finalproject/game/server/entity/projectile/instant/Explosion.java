package com.finalproject.game.server.entity.projectile.instant;

import com.badlogic.gdx.math.Vector2;
import com.finalproject.game.server.builder.entity.ProjectileBuilder;
import com.finalproject.game.server.entity.projectile.Projectile;

public class Explosion extends Projectile {

    public Explosion() {
        this(new ProjectileBuilder());
    }

    public Explosion(ProjectileBuilder builder) {
        super((ProjectileBuilder) builder.setRange(0.1f).setSize(new Vector2(3f, 3f)).setMaxSpeed(0).setDamage(100));
    }
}
