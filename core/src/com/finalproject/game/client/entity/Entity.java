package com.finalproject.game.client.entity;

import com.badlogic.gdx.math.Vector2;
import com.finalproject.game.client.builder.EntityBuilder;

public class Entity  {

    protected Vector2 pos;

    public  Entity(EntityBuilder builder) {
        this.pos = builder.getPos();
    }

    public Vector2 getPos() {
        return pos;
    }

    public void setPos(Vector2 pos) {
        this.pos = pos;
    }


}
