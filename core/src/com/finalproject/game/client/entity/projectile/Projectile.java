package com.finalproject.game.client.entity.projectile;

import com.finalproject.game.client.builder.EntityBuilder;
import com.finalproject.game.client.entity.Entity;

public class Projectile extends Entity {

    public com.finalproject.game.server.entity.projectile.Projectile.ProjectileType projectileType = com.finalproject.game.server.entity.projectile.Projectile.ProjectileType.BULLET;

    public Projectile() {
        super(new EntityBuilder());
    }

    public Projectile(EntityBuilder builder) {
        super(builder);
    }
}
