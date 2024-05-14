package com.finalproject.game.server.entity;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.Body;
import com.finalproject.game.server.RemoteClient;
import com.finalproject.game.server.builder.entity.EntityBuilder;
import com.finalproject.game.server.GameInstanceServer;
import com.finalproject.game.server.statusEffect.Status;

import java.util.ArrayList;

public abstract class Entity extends Sprite {

    private ArrayList<Status> statuses;

    protected float maxSpeed;

    protected float posX;
    protected float posY;

    protected Body boxBody;

    protected GameInstanceServer gameInstanceServer;
    protected RemoteClient remoteClient;

    public Entity(EntityBuilder builder) {
        this.maxSpeed = builder.getMaxSpeed();
        this.posX = builder.getPosX();
        this.posY = builder.getPosY();

        this.gameInstanceServer = builder.getGameInstanceServer();
        this.remoteClient = builder.getRemoteClient();

    }

    public Body getBoxBody() {
        return boxBody;
    }

    public void setBoxBody(Body boxBody) {
        this.boxBody = boxBody;
    }

    public float getMaxSpeed() {
        return maxSpeed;
    }

    public void setMaxSpeed(float maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    public float getPosX() {
        return posX;
    }

    public void setPosX(float posX) {
        this.posX = posX;
    }

    public float getPosY() {
        return posY;
    }

    public void setPosY(float posY) {
        this.posY = posY;
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


}


