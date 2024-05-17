package com.finalproject.game.server.entity;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.physics.box2d.*;
import com.finalproject.game.server.RemoteClient;
import com.finalproject.game.server.builder.entity.EntityBuilder;
import com.finalproject.game.server.GameInstanceServer;
import com.finalproject.game.server.statusEffect.Status;

import java.util.ArrayList;

public abstract class Entity extends Sprite {

    private ArrayList<Status> statuses;

    public ArrayList<Status> getStatuses() {
        return statuses;
    }

    public void setStatuses(ArrayList<Status> statuses) {
        this.statuses = statuses;
    }

    protected float maxSpeed;

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    protected int damage;

    protected float posX;
    protected float posY;

    protected float sizeX;
    protected float sizeY;



    public float getSizeX() {
        return sizeX;
    }

    public void setSizeX(float sizeX) {
        this.sizeX = sizeX;
    }

    public float getSizeY() {
        return sizeY;
    }

    public void setSizeY(float sizeY) {
        this.sizeY = sizeY;
    }

    public BodyDef getBodyDef() {
        return bodyDef;
    }

    public void setBodyDef(BodyDef bodyDef) {
        this.bodyDef = bodyDef;
    }

    public CircleShape getDynamicBox() {
        return dynamicBox;
    }

    public void setDynamicBox(CircleShape dynamicBox) {
        this.dynamicBox = dynamicBox;
    }

    public FixtureDef getFixtureDef() {
        return fixtureDef;
    }

    public void setFixtureDef(FixtureDef fixtureDef) {
        this.fixtureDef = fixtureDef;
    }

    protected BodyDef bodyDef;
    protected Body boxBody;
    protected CircleShape dynamicBox;
    protected FixtureDef  fixtureDef;

    protected transient GameInstanceServer gameInstanceServer;
    protected transient RemoteClient remoteClient;

    public Entity(EntityBuilder builder) {
        this.maxSpeed = builder.getMaxSpeed();
        this.posX = builder.getPosX();
        this.posY = builder.getPosY();
        this.sizeX = builder.getSizeX();
        this.sizeY = builder.getSizeY();
        this.damage = builder.getDamage();

        this.gameInstanceServer = builder.getGameInstanceServer();
        this.remoteClient = builder.getRemoteClient();

        bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(posX, posY);
        boxBody = gameInstanceServer.world.createBody(bodyDef);

        dynamicBox = new CircleShape();
        dynamicBox.setRadius(sizeX);

        fixtureDef = new FixtureDef();
        fixtureDef.shape = dynamicBox;
        fixtureDef.density = 1.0f;
        fixtureDef.friction = 0.5f;
        fixtureDef.restitution = 0.5f; // Bounciness

        boxBody.createFixture(fixtureDef);
        boxBody.setLinearDamping(5f);
        boxBody.setUserData(this);

        dynamicBox.dispose();
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

    public void update(float delta) {}

    public void removeBody() {
        gameInstanceServer.removeObject(boxBody);
    }


}


