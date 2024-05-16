package com.finalproject.game.server.builder.item;

import com.finalproject.game.server.builder.Builder;

public abstract class ItemBuilder extends Builder {


    protected String name = "Weapon";
    protected String description = "A nice weapon";

    protected float weight = 1;
    protected float fireRate = 0.5f;

    public float getFireRate() {
        return fireRate;
    }

    public ItemBuilder setFireRate(float fireRate) {
        this.fireRate = fireRate;
        return this;
    }

    public float getWeight() {
        return weight;
    }

    public ItemBuilder setWeight(float weight) {
        this.weight = weight;
        return this;
    }

    public String getName() {
        return name;
    }

    public ItemBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public ItemBuilder setDescription(String description) {
        this.description = description;
        return this;
    }


}
