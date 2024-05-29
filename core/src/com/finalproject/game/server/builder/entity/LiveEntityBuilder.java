package com.finalproject.game.server.builder.entity;

import com.finalproject.game.server.entity.live.player.Player;

public class LiveEntityBuilder extends EntityBuilder {

    float maxHealth = 100;
    float currentHealth = 100;

    float runningMultiplier = 3f;
    float currentStamina = 100;
    float maxStamina = 100;

    Player.PlayerType playerType = Player.PlayerType.FRIA;

    float specialSkillTimer = 2;
    float specialSkillStaminaCost = 30;

    public float getSpecialSkillStaminaCost() {
        return specialSkillStaminaCost;
    }

    public LiveEntityBuilder setSpecialSkillStaminaCost(float specialSkillStaminaCost) {
        this.specialSkillStaminaCost = specialSkillStaminaCost;
        return this;
    }

    public float getSpecialSkillTimer() {
        return specialSkillTimer;
    }

    public LiveEntityBuilder setSpecialSkillTimer(float specialSkillTimer) {
        this.specialSkillTimer = specialSkillTimer;
        return this;
    }

    public Player.PlayerType getPlayerType() {
        return playerType;
    }

    public LiveEntityBuilder setPlayerType(Player.PlayerType playerType) {
        this.playerType = playerType;
        return this;
    }

    public float getRunningMultiplier() {
        return runningMultiplier;
    }

    public LiveEntityBuilder setRunningMultiplier(float runningMultiplier) {
        this.runningMultiplier = runningMultiplier;
        return this;
    }

    public LiveEntityBuilder setRunningMultiplier(int runningMultiplier) {
        this.runningMultiplier = runningMultiplier;
        return this;
    }

    public float getMaxStamina() {
        return maxStamina;
    }

    public LiveEntityBuilder setMaxStamina(float maxStamina) {
        this.maxStamina = maxStamina;
        return this;
    }

    public float getCurrentStamina() {
        return currentStamina;
    }

    public LiveEntityBuilder setCurrentStamina(float currentStamina) {
        this.currentStamina = currentStamina;
        return this;
    }

    public float getMaxHealth() {
        return maxHealth;
    }

    public LiveEntityBuilder setMaxHealth(int maxHealth) {
        this.maxHealth = maxHealth;
        return this;
    }

    public float getCurrentHealth() {
        return currentHealth;
    }

    public LiveEntityBuilder setCurrentHealth(int currentHealth) {
        this.currentHealth = currentHealth;
        return this;
    }

}
