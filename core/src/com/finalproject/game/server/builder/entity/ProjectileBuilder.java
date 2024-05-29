package com.finalproject.game.server.builder.entity;

import com.finalproject.game.server.entity.projectile.Projectile;

public class ProjectileBuilder extends EntityBuilder {

    protected float angle = 0;
    protected float range = 1;

    protected float accuracy = 0;
    protected Projectile.ProjectileType projectileType = Projectile.ProjectileType.BULLET;

    public Projectile.ProjectileType getProjectileType() {
        return projectileType;
    }

    public ProjectileBuilder setProjectileType(Projectile.ProjectileType projectileType) {
        this.projectileType = projectileType;
        return this;
    }

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
