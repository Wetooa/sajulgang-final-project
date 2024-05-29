package com.finalproject.game.server.builder.entity;

import com.finalproject.game.server.entity.live.player.Player;

public class LiveEntityBuilder extends EntityBuilder {

    int maxHealth = 100;
    int currentHealth = 100;

    float runningMultiplier = 1.5f;
    int currentStamina = 100;
    int maxStamina = 100;

    Player.PlayerType playerType = Player.PlayerType.FRIA;
    float specialSkillTimer = 2;

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
