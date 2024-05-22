package com.finalproject.game.server.items;

import com.badlogic.gdx.physics.box2d.World;
import com.finalproject.game.server.GameInstanceServer;
import com.finalproject.game.server.GameObject;
import com.finalproject.game.server.RemoteClient;
import com.finalproject.game.server.builder.item.ItemBuilder;

public abstract class Item extends GameObject {

    protected final String name;
    protected final String description;

    protected final float weight;

    protected final float fireRate;
    protected float reload;

    protected Item() {
        this(new ItemBuilder());
    }

    protected Item(ItemBuilder builder) {
        super(builder);

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


}
