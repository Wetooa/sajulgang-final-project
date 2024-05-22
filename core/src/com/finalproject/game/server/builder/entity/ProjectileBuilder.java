package com.finalproject.game.server.builder.entity;

public class ProjectileBuilder extends EntityBuilder {

    protected float angle = 0;
    protected float range = 1;

    protected float accuracy = 0;

    public float getAccuracy() {
        return accuracy;
    }

    public ProjectileBuilder setAccuracy(float accuracy) {
        this.accuracy = accuracy;
        return this;
    }

    public float getRange() {
        return range;
    }

    public ProjectileBuilder setRange(float range) {
        this.range = range;
        return this;
    }


    public float getAngle() {
        return angle;
    }

    public ProjectileBuilder setAngle(float angle) {
        this.angle = angle;
        return this;
    }


}
