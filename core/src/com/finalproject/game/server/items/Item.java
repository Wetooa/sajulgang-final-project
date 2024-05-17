package com.finalproject.game.server.items;

import com.finalproject.game.server.GameInstanceServer;
import com.finalproject.game.server.RemoteClient;
import com.finalproject.game.server.builder.item.ItemBuilder;

public abstract class Item {

    protected final String name;
    protected final String description;

    protected final float weight;

    protected final float fireRate;
    protected float reload;

    protected transient GameInstanceServer gameInstanceServer;
    protected transient RemoteClient remoteClient;


    protected Item() {
        this(new ItemBuilder());
    }

    protected Item(ItemBuilder builder) {
        this.name = builder.getName();
        this.description = builder.getDescription();

        this.weight = builder.getWeight();
        this.fireRate = builder.getFireRate();

        this.gameInstanceServer = builder.getGameInstanceServer();
        this.remoteClient = builder.getRemoteClient();
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

    public GameInstanceServer getGameInstanceServer() {
        return gameInstanceServer;
    }

    public void setGameInstanceServer(GameInstanceServer gameInstanceServer) {
        this.gameInstanceServer = gameInstanceServer;
    }

    public RemoteClient getRemoteClient() {
        return remoteClient;
    }

    public void setRemoteClient(RemoteClient remoteClient) {
        this.remoteClient = remoteClient;
    }

}
