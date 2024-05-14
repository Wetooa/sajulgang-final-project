package com.finalproject.game.client.builder;

import com.badlogic.gdx.math.Vector2;

public class EntityBuilder {


    Vector2 pos = new Vector2(0, 0);

    public Vector2 getPos() {
        return pos;
    }

    public EntityBuilder setPos(Vector2 pos) {
        this.pos = pos;
        return this;
    }


}
