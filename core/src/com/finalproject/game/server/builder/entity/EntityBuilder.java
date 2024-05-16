package com.finalproject.game.server.builder.entity;

import com.finalproject.game.server.builder.Builder;

public class EntityBuilder extends Builder {
    float maxSpeed = 5;
    int damage = 20;

    float posX = 0;
    float posY = 0;

    float sizeX = 1f;
    float sizeY = 1f;

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

    public EntityBuilder setSize(float size) {
        this.sizeX = size;
        this.sizeY = size;
        return this;
    }

    public float getMaxSpeed() {
        return maxSpeed;
    }

    public EntityBuilder setMaxSpeed(float maxSpeed) {
        this.maxSpeed = maxSpeed;
        return this;
    }

    public float getPosX() {
        return posX;
    }

    public EntityBuilder setPosX(float posX) {
        this.posX = posX;
        return this;
    }

    public float getPosY() {
        return posY;
    }

    public EntityBuilder setPosY(float posY) {
        this.posY = posY;
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
