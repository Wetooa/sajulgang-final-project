package com.finalproject.game.entities;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.physics.box2d.Body;
import com.finalproject.game.builder.EntityBuilder;
import com.finalproject.game.status.Status;

import java.util.ArrayList;

public abstract class Entity extends Game  {

    public Entity(EntityBuilder builder) {
        this.create();

        this.maxSpeed = builder.getMaxSpeed();
        this.maxHealth = builder.getMaxHealth();
        this.currentHealth = builder.getCurrentHealth();
        this.posX = builder.getPosX();
        this.posY = builder.getPosY();
    }


    protected float maxSpeed;

    protected int currentHealth;
    protected int maxHealth;

    protected float posX;
    protected float posY;

    protected Body boxBody;

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

    private ArrayList<Status> statuses;



}


