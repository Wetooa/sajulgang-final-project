package com.finalproject.game.server.entity.live.enemy;


import com.finalproject.game.server.builder.entity.LiveEntityBuilder;
import com.finalproject.game.server.entity.live.LiveEntity;

public abstract class Enemy extends LiveEntity {

    public Enemy(LiveEntityBuilder builder) {
        super(builder);
    }

    public Enemy() {
        this(new LiveEntityBuilder());
    }
}
