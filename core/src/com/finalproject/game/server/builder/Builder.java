package com.finalproject.game.server.builder;

import com.finalproject.game.server.GameInstanceServer;
import com.finalproject.game.server.RemoteClient;

public class Builder {

    protected GameInstanceServer gameInstanceServer;
    protected RemoteClient remoteClient;

    public GameInstanceServer getGameInstanceServer() {
        return gameInstanceServer;
    }

    public Builder setGameInstanceServer(GameInstanceServer gameInstanceServer) {
        this.gameInstanceServer = gameInstanceServer;
        return  this;
    }

    public RemoteClient getRemoteClient() {
        return remoteClient;
    }

    public Builder setRemoteClient(RemoteClient remoteClient) {
        this.remoteClient = remoteClient;
        return this;
    }

}
