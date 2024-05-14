package com.finalproject.game.server.builder.entity;

import com.finalproject.game.server.builder.Builder;

public class LiveEntityBuilder extends EntityBuilder {

    int maxHealth = 100;
    int currentHealth = 100;

    float runningMultiplier = 1.5F;
    int currentStamina = 100;
    int maxStamina = 100;

    public float getRunningMultiplier() {
        return runningMultiplier;
    }

    public Builder setRunningMultiplier(int runningMultiplier) {
        this.runningMultiplier = runningMultiplier;
        return this;
    }

    public int getMaxStamina() {
        return maxStamina;
    }

    public Builder setMaxStamina(int maxStamina) {
        this.maxStamina = maxStamina;
        return this;
    }

    public int getCurrentStamina() {
        return currentStamina;
    }

    public Builder setCurrentStamina(int currentStamina) {
        this.currentStamina = currentStamina;
        return this;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public Builder setMaxHealth(int maxHealth) {
        this.maxHealth = maxHealth;
        return this;
    }

    public int getCurrentHealth() {
        return currentHealth;
    }

    public Builder setCurrentHealth(int currentHealth) {
        this.currentHealth = currentHealth;
        return this;
    }

}
