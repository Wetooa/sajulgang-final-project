package com.finalproject.game.server.builder.entity;

import com.finalproject.game.server.builder.Builder;

public class EntityBuilder extends Builder {
    float maxSpeed = 10;

    float posX = 0;
    float posY = 0;

    public float getMaxSpeed() {
        return maxSpeed;
    }

    public Builder setMaxSpeed(float maxSpeed) {
        this.maxSpeed = maxSpeed;
        return this;
    }

    public float getPosX() {
        return posX;
    }

    public Builder setPosX(float posX) {
        this.posX = posX;
        return this;
    }

    public float getPosY() {
        return posY;
    }

    public Builder setPosY(float posY) {
        this.posY = posY;
        return this;
    }
}
