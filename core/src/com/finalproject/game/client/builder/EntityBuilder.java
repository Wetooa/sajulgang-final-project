package com.finalproject.game.client.builder;

import com.badlogic.gdx.math.Vector2;
import com.finalproject.game.server.entity.live.LiveEntity;

public class EntityBuilder {

    protected Vector2 pos = new Vector2(0, 0);
    protected float sizeX = 0.5f;
    protected float sizeY = 0.5f;
    protected float angle = 0;
    protected LiveEntity.FacingDirection facingDirection = LiveEntity.FacingDirection.UP;
    protected float stateTime = 0;

    public float getAngle() {
        return angle;
    }

    public EntityBuilder setAngle(float angle) {
        this.angle = angle;
        return this;
    }

    public LiveEntity.FacingDirection getFacingDirection() {
        return facingDirection;
    }

    public EntityBuilder setFacingDirection(LiveEntity.FacingDirection facingDirection) {
        this.facingDirection = facingDirection;
        return this;
    }

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

    public float getStateTime() {
        return stateTime;
    }

    public EntityBuilder setStateTime(float stateTime) {
        this.stateTime = stateTime;
        return this;
    }
}
