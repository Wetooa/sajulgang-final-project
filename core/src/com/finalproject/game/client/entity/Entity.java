package com.finalproject.game.client.entity;

import com.badlogic.gdx.math.Vector;
import com.badlogic.gdx.math.Vector2;
import com.finalproject.game.client.builder.EntityBuilder;

public class Entity {

    protected Vector2 pos;
    protected float sizeX;
    protected float sizeY;

    public float getSizeX() {
        return sizeX;
    }

    public void setSizeX(float sizeX) {
        this.sizeX = sizeX;
    }

    public float getSizeY() {
        return sizeY;
    }

    public void setSizeY(float sizeY) {
        this.sizeY = sizeY;
    }

    public Entity() {}

    public Entity(EntityBuilder builder) {
        this.pos = builder.getPos();
        this.sizeX = builder.getSizeX();
        this.sizeY = builder.getSizeY();
    }

    public Vector2 getPos() {
        return pos;
    }

    public void setPos(Vector2 pos) {
        this.pos = pos;
    }


}
