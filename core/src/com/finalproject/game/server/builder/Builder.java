package com.finalproject.game.server.builder;

import com.badlogic.gdx.physics.box2d.World;
import com.finalproject.game.server.GameInstanceServer;
import com.finalproject.game.server.RemoteClient;

public class Builder {

    protected GameInstanceServer gameInstanceServer;
    protected RemoteClient remoteClient;
    protected World currentWorld;

    public GameInstanceServer getGameInstanceServer() {
        return gameInstanceServer;
    }

    public Builder setGameInstanceServer(GameInstanceServer gameInstanceServer) {
        this.gameInstanceServer = gameInstanceServer;
        return this;
    }

    public RemoteClient getRemoteClient() {
        return remoteClient;
    }

    public Builder setRemoteClient(RemoteClient remoteClient) {
        this.remoteClient = remoteClient;
        return this;
    }


    public World getCurrentWorld() {
        return currentWorld;
    }

    public Builder setCurrentWorld(World currentWorld) {
        this.currentWorld = currentWorld;
        return this;
    }

}
