package com.finalproject.game.server.builder.entity;

public class LiveEntityBuilder extends EntityBuilder {

    int maxHealth = 100;
    int currentHealth = 100;

    float runningMultiplier = 1.5f;
    int currentStamina = 100;
    int maxStamina = 100;

    public float getRunningMultiplier() {
        return runningMultiplier;
    }

    public LiveEntityBuilder setRunningMultiplier(int runningMultiplier) {
        this.runningMultiplier = runningMultiplier;
        return this;
    }

    public int getMaxStamina() {
        return maxStamina;
    }

    public LiveEntityBuilder setMaxStamina(int maxStamina) {
        this.maxStamina = maxStamina;
        return this;
    }

    public int getCurrentStamina() {
        return currentStamina;
    }

    public LiveEntityBuilder setCurrentStamina(int currentStamina) {
        this.currentStamina = currentStamina;
        return this;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public LiveEntityBuilder setMaxHealth(int maxHealth) {
        this.maxHealth = maxHealth;
        return this;
    }

    public int getCurrentHealth() {
        return currentHealth;
    }

    public LiveEntityBuilder setCurrentHealth(int currentHealth) {
        this.currentHealth = currentHealth;
        return this;
    }

}
