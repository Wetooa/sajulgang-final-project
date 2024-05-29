package com.finalproject.game.server.builder.entity;

import com.badlogic.gdx.math.Vector2;
import com.finalproject.game.server.builder.Builder;

public class EntityBuilder extends Builder {

    protected float maxSpeed = 100;
    protected int damage = 20;

    protected Vector2 pos = new Vector2(0, 0);
    protected Vector2 size = new Vector2(1f, 1f);
    protected float angle = 0;

    public Vector2 getSize() {
        return size;
    }

    public EntityBuilder setSize(Vector2 size) {
        this.size = size;
        return this;
    }

    public float getAngle() {
        return angle;
    }

    public EntityBuilder setAngle(float angle) {
        this.angle = angle;
        return this;
    }

    public float getMaxSpeed() {
        return maxSpeed;
    }

    public EntityBuilder setMaxSpeed(float maxSpeed) {
        this.maxSpeed = maxSpeed;
        return this;
    }

    public Vector2 getPos() {
        return pos;
    }

    public EntityBuilder setPos(Vector2 pos) {
        this.pos = pos;
        return this;
    }

    public int getDamage() {
        return damage;
    }

    public Builder setDamage(int damage) {
        this.damage = damage;
        return this;
    }
}
