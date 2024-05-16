package com.finalproject.game.server.builder.entity;

import com.finalproject.game.server.builder.Builder;

public class ProjectileBuilder extends EntityBuilder {

    protected float angle = 0;

    public float getRange() {
        return range;
    }

    public ProjectileBuilder setRange(float range) {
        this.range = range;
        return  this;
    }

    protected float range = 100;

    public float getAngle() {
        return angle;
    }

    public ProjectileBuilder setAngle(float angle) {
        this.angle = angle;
        return this;
    }



}
