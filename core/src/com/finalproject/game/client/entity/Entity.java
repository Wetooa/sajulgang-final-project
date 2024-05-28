package com.finalproject.game.client.entity;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.finalproject.game.client.builder.EntityBuilder;
import com.finalproject.game.server.entity.live.LiveEntity;

import java.util.HashMap;

public class Entity {

    protected Vector2 pos;
    protected float sizeX;
    protected float sizeY;

    protected LiveEntity.FacingDirection facingDirection = LiveEntity.FacingDirection.UP;
    protected HashMap<LiveEntity.FacingDirection, Animation<TextureRegion>> animations;
    protected TextureRegion currentFrame;

    public Entity() {
    }

    public Entity(EntityBuilder builder) {
        this.pos = builder.getPos();
        this.sizeX = builder.getSizeX();
        this.sizeY = builder.getSizeY();
    }

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

    public Vector2 getPos() {
        return pos;
    }

    public void setPos(Vector2 pos) {
        this.pos = pos;
    }


    public float getSize() {
        return getSizeX();
    }

}
