package com.finalproject.game.server.entity.live.enemy;


import com.finalproject.game.server.builder.entity.EntityBuilder;
import com.finalproject.game.server.builder.entity.LiveEntityBuilder;
import com.finalproject.game.server.entity.Entity;
import com.finalproject.game.server.entity.live.LiveEntity;

public abstract class Enemy extends LiveEntity {

    public Enemy(LiveEntityBuilder builder) {
        super(builder);
    }

    @Override
    public void update(float delta) {
        super.update(delta);
    }
}
