package com.finalproject.game.server.entities;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.Body;
import com.finalproject.game.server.builder.EntityBuilder;
import com.finalproject.game.server.GameInstanceServer;
import com.finalproject.game.server.entities.status.Status;

import java.util.ArrayList;

public abstract class Entity extends Sprite {

    private ArrayList<Status> statuses;

    protected float maxSpeed;

    protected int currentHealth;
    protected int maxHealth;

    protected float posX;
    protected float posY;

    protected Body boxBody;

    protected GameInstanceServer gameInstance;

    public Entity(EntityBuilder builder) {

        this.maxSpeed = builder.getMaxSpeed();
        this.maxHealth = builder.getMaxHealth();
        this.currentHealth = builder.getCurrentHealth();
        this.posX = builder.getPosX();
        this.posY = builder.getPosY();

        this.gameInstance = builder.getGameInstance();

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

    public int getCurrentHealth() {
        return currentHealth;
    }

    public void setCurrentHealth(int currentHealth) {
        this.currentHealth = currentHealth;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public void setMaxHealth(int maxHealth) {
        this.maxHealth = maxHealth;
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



}


