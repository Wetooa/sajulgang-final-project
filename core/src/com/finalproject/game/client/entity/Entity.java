package com.finalproject.game.client.entity;

import com.badlogic.gdx.math.Vector2;
import com.finalproject.game.client.builder.EntityBuilder;
import com.finalproject.game.server.entity.live.LiveEntity;

public class Entity {

    protected Vector2 pos;
    protected float sizeX;
    protected float sizeY;

    protected LiveEntity.FacingDirection facingDirection = LiveEntity.FacingDirection.UP;
    protected float stateTime;

    public Entity() {
        this(new EntityBuilder());
    }

    public Entity(EntityBuilder builder) {
        this.pos = builder.getPos();
        this.sizeX = builder.getSizeX();
        this.sizeY = builder.getSizeY();
        this.facingDirection = builder.getFacingDirection();
        this.stateTime = builder.getStateTime();
    }

    public float getStateTime() {
        return stateTime;
    }

    public void setStateTime(float stateTime) {
        this.stateTime = stateTime;
    }

    public LiveEntity.FacingDirection getFacingDirection() {
        return facingDirection;
    }

    public void setFacingDirection(LiveEntity.FacingDirection facingDirection) {
        this.facingDirection = facingDirection;
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
