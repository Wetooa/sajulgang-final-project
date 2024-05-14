package com.finalproject.game.client.entity.projectile;

import com.finalproject.game.client.builder.EntityBuilder;

public class Bullet extends Projectile {
    public Bullet() {
        super(new EntityBuilder());
    }

    public Bullet(EntityBuilder builder) {
        super(builder);
    }
}
