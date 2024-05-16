package com.finalproject.game.client.entity.projectile;

import com.finalproject.game.client.builder.EntityBuilder;
import com.finalproject.game.client.entity.Entity;

public class Projectile extends Entity {

    public Projectile() {
        super(new EntityBuilder());
    }

    public Projectile(EntityBuilder builder) {
        super(builder);
    }
}
