package com.finalproject.game.server.builder.item;

import com.finalproject.game.server.builder.Builder;

public class WeaponBuilder extends ItemBuilder {

    protected float accuracy = 1;
    protected float range = 10;

    protected int maxAmmo = 100;
    protected int currentAmmo = 100;

    public float getAccuracy() {
        return accuracy;
    }

    public ItemBuilder setAccuracy(float accuracy) {
        this.accuracy = accuracy;
        return this;
    }

    public float getRange() {
        return range;
    }

    public ItemBuilder setRange(float range) {
        this.range = range;
        return this;
    }

    public int getMaxAmmo() {
        return maxAmmo;
    }

    public ItemBuilder setMaxAmmo(int maxAmmo) {
        this.maxAmmo = maxAmmo;
        return this;
    }

    public int getCurrentAmmo() {
        return currentAmmo;
    }

    public ItemBuilder setCurrentAmmo(int currentAmmo) {
        this.currentAmmo = currentAmmo;
        return this;
    }
}
