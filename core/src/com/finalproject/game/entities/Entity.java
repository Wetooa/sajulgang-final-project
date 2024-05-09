package com.finalproject.game.entities;

import com.badlogic.gdx.Game;
import com.finalproject.game.status.Status;

import java.util.ArrayList;

public abstract class Entity extends Game  {

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    private float speed;

    int currentHealth;
    int maxHealth;

    float posX;
    float posY;


    private ArrayList<Status> statuses;



}


