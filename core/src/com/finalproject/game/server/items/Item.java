package com.finalproject.game.server.items;

import com.finalproject.game.server.GameObject;
import com.finalproject.game.server.builder.item.ItemBuilder;

public abstract class Item extends GameObject {

    protected final String name, description;
    protected final ItemType itemType;
    protected final float weight;
    protected final float fireRate;
    protected float reload;

    protected Item() {
        this(new ItemBuilder());
    }

    protected Item(ItemBuilder builder) {
        super(builder);

        this.itemType = builder.getItemType();
        this.name = builder.getName();
        this.description = builder.getDescription();


        this.weight = builder.getWeight();
        this.fireRate = builder.getFireRate();
    }

    public void activate(float delta) {
        this.reload = Math.max(0, this.reload - delta);

        if (this.reload > 0) {
            return;
        }

        doAction();
        this.reload = this.fireRate;
    }

    public abstract void doAction();

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public ItemType getItemType() {
        return itemType;
    }

    public float getWeight() {
        return weight;
    }

    public float getFireRate() {
        return fireRate;
    }

    public float getReload() {
        return reload;
    }

    public void setReload(float reload) {
        this.reload = reload;
    }

    public enum ItemType {
        HANDGUN, ANACONDA, DESERT_EAGLE, TWIN_PISTOLS, LASER_GUN, CROSSBOW, MACHINE_GUN, SUBMACHINE_GUN, AK69, SHOTGUN, KATANA
    }
}
