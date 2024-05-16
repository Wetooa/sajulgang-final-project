package com.finalproject.game.client.builder;

import com.badlogic.gdx.math.Vector2;

public class EntityBuilder {

    protected Vector2 pos = new Vector2(0, 0);
    protected float sizeX = 0.5f;
    protected float sizeY = 0.5f;

    public float getSizeX() {
        return sizeX;
    }

    public EntityBuilder setSizeX(float sizeX) {
        this.sizeX = sizeX;
        return this;
    }

    public float getSizeY() {
        return sizeY;
    }

    public EntityBuilder setSizeY(float sizeY) {
        this.sizeY = sizeY;
        return this;
    }

    public Vector2 getPos() {
        return pos;
    }

    public EntityBuilder setPos(Vector2 pos) {
        this.pos = pos;
        return this;
    }


}
