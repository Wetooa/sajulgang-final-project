package com.finalproject.game.server.entity.projectile;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.finalproject.game.server.builder.entity.ProjectileBuilder;
import com.finalproject.game.server.entity.Entity;

import java.util.Random;

public abstract class Projectile extends Entity {


    protected float accuracy;
    protected float range;
    protected float expiration = 0;
    protected ProjectileType projectileType;

    public Projectile() {
        this(new ProjectileBuilder());
    }

    public Projectile(ProjectileBuilder builder) {
        super(builder);

        this.projectileType = builder.getProjectileType();

        if (remoteClient == null) return;

        float playerX = remoteClient.getPlayer().getBoxBody().getPosition().x;
        float playerY = remoteClient.getPlayer().getBoxBody().getPosition().y;

        float mouseX = remoteClient.getMouseX();
        float mouseY = remoteClient.getMouseY();

        Random rand = new Random();

        this.getBoxBody().setLinearDamping(0.5f);
        this.getFixtureDef().restitution = 0f;
        this.getFixtureDef().friction = 0f;

        this.accuracy = 1 - builder.getAccuracy();
        this.angle = MathUtils.atan2(mouseY - playerY, mouseX - playerX) + rand.nextFloat(-this.accuracy, this.accuracy);

        this.range = builder.getRange();
        this.expiration = range;

        boxBody.setTransform(new Vector2(playerX + MathUtils.cos(angle) * this.getSize().x, playerY + MathUtils.sin(angle) * this.getSize().y), 0);

        float impulseStrength = getMaxSpeed(); // Adjust as needed
        Vector2 impulse = new Vector2(MathUtils.cos(angle) * impulseStrength, MathUtils.sin(angle) * impulseStrength);
        boxBody.applyLinearImpulse(impulse, boxBody.getWorldCenter(), true);

    }

    public float getAccuracy() {
        return accuracy;
    }

    public void setAccuracy(float accuracy) {
        this.accuracy = accuracy;
    }

    public float getRange() {
        return range;
    }

    public void setRange(float range) {
        this.range = range;
    }

    public float getExpiration() {
        return expiration;
    }

    public void setExpiration(float expiration) {
        this.expiration = expiration;
    }

    public ProjectileType getProjectileType() {
        return projectileType;
    }

    public void setProjectileType(ProjectileType projectileType) {
        this.projectileType = projectileType;
    }


    @Override
    public void update(float delta) {
        super.update(delta);

        expiration -= delta;
        if (expiration <= 0) {
            removeBody();
        }
    }

    public enum ProjectileType {
        ENERGY, SHELL, BOLT, BULLET
    }
}
