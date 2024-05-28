package com.finalproject.game.client.entity.live.player;

import com.finalproject.game.client.builder.EntityBuilder;
import com.finalproject.game.client.entity.Entity;

public class Player extends Entity {


    public Player() {
        super(new EntityBuilder());
    }

    public Player(EntityBuilder builder) {
        super(builder);
    }

}
