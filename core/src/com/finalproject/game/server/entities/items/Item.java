package com.finalproject.game.server.entities.items;

public abstract class Item {

    private final String name;
    private final String description;

    protected Item(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public abstract void activate();

}
