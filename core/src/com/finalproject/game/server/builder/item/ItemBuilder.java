package com.finalproject.game.server.builder.item;

import com.finalproject.game.server.builder.Builder;

public abstract class ItemBuilder extends Builder {


    protected String name;
    protected String description;

    protected float weight;


    public float getWeight() {
        return weight;
    }

    public Builder setWeight(float weight) {
        this.weight = weight;
        return this;
    }

    public String getName() {
        return name;
    }

    public Builder setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Builder setDescription(String description) {
        this.description = description;
        return this;
    }
}
