package com.finalproject.game.server;

import com.badlogic.gdx.physics.box2d.World;
import com.finalproject.game.server.builder.Builder;

public class GameObject {


    protected transient GameInstanceServer gameInstanceServer;
    protected transient RemoteClient remoteClient;
    protected transient World currentWorld;


    public GameObject(Builder builder) {
        this.gameInstanceServer = builder.getGameInstanceServer();
        this.remoteClient = builder.getRemoteClient();
        this.currentWorld = builder.getCurrentWorld();
    }

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

    public World getCurrentWorld() {
        return currentWorld;
    }

    public void setCurrentWorld(World currentWorld) {
        this.currentWorld = currentWorld;
    }
}
