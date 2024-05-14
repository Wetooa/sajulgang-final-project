package com.finalproject.game.server.builder.entity;

import com.finalproject.game.server.builder.Builder;

public class ProjectileBuilder extends EntityBuilder {

    protected int damage;
    protected float speed;

    public float getSpeed() {
        return speed;
    }

    public Builder setSpeed(float speed) {
        this.speed = speed;
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
