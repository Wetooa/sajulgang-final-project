package com.finalproject.game.server.entity.projectile.melee;

import com.badlogic.gdx.math.Vector2;
import com.finalproject.game.server.builder.entity.ProjectileBuilder;
import com.finalproject.game.server.entity.projectile.Projectile;

public class Slash extends Projectile {
    public Slash() {
        this(new ProjectileBuilder());
    }

    public Slash(ProjectileBuilder builder) {
        super((ProjectileBuilder) builder.setProjectileType(ProjectileType.SLASH).setRange(0.2f).setSize(new Vector2(3f, 3f)).setMaxSpeed(10f).setDamage(50));
    }
}
