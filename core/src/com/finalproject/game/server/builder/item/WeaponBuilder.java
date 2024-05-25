package com.finalproject.game.server.builder.item;

public class WeaponBuilder extends ItemBuilder {

    protected float accuracy = 1;
    protected float range = 10;

    protected int maxAmmo = 100;
    protected int currentAmmo = 100;

    public float getAccuracy() {
        return accuracy;
    }

    public WeaponBuilder setAccuracy(float accuracy) {
        this.accuracy = accuracy;
        return this;
    }

    public float getRange() {
        return range;
    }

    public WeaponBuilder setRange(float range) {
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

    public WeaponBuilder setCurrentAmmo(int currentAmmo) {
        this.currentAmmo = currentAmmo;
        return this;
    }
}
