package com.finalproject.game.server.builder;

import com.finalproject.game.server.GameInstanceServer;

public class EntityBuilder {
    float maxSpeed = 10;

    int maxHealth = 100;
    int currentHealth = 100;

    int posX = 0;
    int posY = 0;

    float runningMultiplier = 1.5F;
    int currentStamina = 100;
    int maxStamina = 100;

    GameInstanceServer gameInstance;

    public GameInstanceServer getGameInstance() {
        return gameInstance;
    }

    public EntityBuilder setGameInstance(GameInstanceServer gameInstance) {
        this.gameInstance = gameInstance;
        return this;
    }

    public float getRunningMultiplier() {
        return runningMultiplier;
    }

    public EntityBuilder setRunningMultiplier(int runningMultiplier) {
        this.runningMultiplier = runningMultiplier;
        return this;
    }

    public int getMaxStamina() {
        return maxStamina;
    }

    public EntityBuilder setMaxStamina(int maxStamina) {
        this.maxStamina = maxStamina;
        return this;
    }

    public int getCurrentStamina() {
        return currentStamina;
    }

    public EntityBuilder setCurrentStamina(int currentStamina) {
        this.currentStamina = currentStamina;
        return this;
    }

    public float getMaxSpeed() {
        return maxSpeed;
    }

    public EntityBuilder setMaxSpeed(float maxSpeed) {
        this.maxSpeed = maxSpeed;
        return this;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public EntityBuilder setMaxHealth(int maxHealth) {
        this.maxHealth = maxHealth;
        return this;
    }

    public int getCurrentHealth() {
        return currentHealth;
    }

    public EntityBuilder setCurrentHealth(int currentHealth) {
        this.currentHealth = currentHealth;
        return this;
    }

    public int getPosX() {
        return posX;
    }

    public EntityBuilder setPosX(int posX) {
        this.posX = posX;
        return this;
    }

    public int getPosY() {
        return posY;
    }

    public EntityBuilder setPosY(int posY) {
        this.posY = posY;
        return this;
    }
}
