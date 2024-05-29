package com.finalproject.game.server.entity.projectile.melee;

import com.badlogic.gdx.math.Vector2;
import com.finalproject.game.server.builder.entity.ProjectileBuilder;
import com.finalproject.game.server.entity.projectile.Projectile;

public class Thrust extends Projectile {

    public Thrust() {
        this(new ProjectileBuilder());
    }

    public Thrust(ProjectileBuilder builder) {
        super((ProjectileBuilder) builder.setRange(0.1f).setSize(new Vector2(2f, 2f)).setMaxSpeed(10).setDamage(50));
    }


}
