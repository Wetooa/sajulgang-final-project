package com.finalproject.game.server.items;

import com.finalproject.game.server.GameInstanceServer;
import com.finalproject.game.server.RemoteClient;
import com.finalproject.game.server.builder.item.ItemBuilder;

public abstract class Item {

    protected final String name;
    protected final String description;

    protected final float weight;

    protected GameInstanceServer gameInstanceServer;
    protected RemoteClient remoteClient;

    protected Item(ItemBuilder builder) {
        this.name = builder.getName();
        this.description = builder.getDescription();

        this.weight = builder.getWeight();

        this.gameInstanceServer = builder.getGameInstanceServer();
        this.remoteClient = builder.getRemoteClient();
    }

    public abstract void activate();

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
