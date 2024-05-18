package com.finalproject.game.server.entity.live;

import com.finalproject.game.server.builder.entity.LiveEntityBuilder;
import com.finalproject.game.server.entity.Entity;

public abstract class LiveEntity extends Entity {

    protected int currentHealth;
    protected int maxHealth;


    public LiveEntity() {
        this(new LiveEntityBuilder());
    }

    public LiveEntity(LiveEntityBuilder builder) {
        super(builder);

        this.maxHealth = builder.getMaxHealth();
        this.currentHealth = builder.getCurrentHealth();
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

    public void takeDamage(int damage) {
        setCurrentHealth(getCurrentHealth() - damage);
    }


    @Override
    public void update(float delta) {
        if (getCurrentHealth() <= 0) {
            removeBody();
        }

        super.update(delta);
    }
}
