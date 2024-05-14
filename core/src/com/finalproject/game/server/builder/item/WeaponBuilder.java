package com.finalproject.game.server.builder.item;

import com.finalproject.game.server.builder.Builder;

public class WeaponBuilder extends ItemBuilder {

    protected float accuracy;
    protected float range;
    protected float fireRate;

    protected int maxAmmo;
    protected int currentAmmo;

    public float getAccuracy() {
        return accuracy;
    }

    public Builder setAccuracy(float accuracy) {
        this.accuracy = accuracy;
        return this;
    }

    public float getRange() {
        return range;
    }

    public Builder setRange(float range) {
        this.range = range;
        return this;
    }

    public float getFireRate() {
        return fireRate;
    }

    public Builder setFireRate(float fireRate) {
        this.fireRate = fireRate;
        return this;
    }

    public int getMaxAmmo() {
        return maxAmmo;
    }

    public Builder setMaxAmmo(int maxAmmo) {
        this.maxAmmo = maxAmmo;
        return this;
    }

    public int getCurrentAmmo() {
        return currentAmmo;
    }

    public Builder setCurrentAmmo(int currentAmmo) {
        this.currentAmmo = currentAmmo;
        return this;
    }
}
