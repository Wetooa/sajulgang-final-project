package com.finalproject.game.server.entity.live;

import com.finalproject.game.server.builder.entity.LiveEntityBuilder;
import com.finalproject.game.server.entity.Entity;

public abstract class LiveEntity extends Entity {

    protected float currentHealth;
    protected float maxHealth;
    protected FacingDirection facingDirection = FacingDirection.DOWN;

    public LiveEntity() {
        this(new LiveEntityBuilder());
    }

    public LiveEntity(LiveEntityBuilder builder) {
        super(builder);

        this.maxHealth = builder.getMaxHealth();
        this.currentHealth = builder.getCurrentHealth();
    }

    public FacingDirection getFacingDirection() {
        return facingDirection;
    }

    public void setFacingDirection(FacingDirection facingDirection) {
        this.facingDirection = facingDirection;
    }

    public float getCurrentHealth() {
        return currentHealth;
    }

    public void setCurrentHealth(float currentHealth) {
        this.currentHealth = currentHealth;
    }

    public float getMaxHealth() {
        return maxHealth;
    }

    public void setMaxHealth(int maxHealth) {
        this.maxHealth = maxHealth;
    }


    public void increaseCurrentHealth(int increase) {
        currentHealth = Math.max(maxHealth, currentHealth + increase);
    }

    public void takeDamage(float damage) {
        setCurrentHealth(getCurrentHealth() - damage);
    }

    public boolean isDead() {
        return getCurrentHealth() <= 0;
    }

    private LiveEntity.FacingDirection calculateFacingDirection() {
        float angle = getAngleFromMouse();

        if (angle >= -Math.PI / 4 && angle <= Math.PI / 4) {
            return LiveEntity.FacingDirection.RIGHT;
        } else if (angle > Math.PI / 4 && angle < 3 * Math.PI / 4) {
            return LiveEntity.FacingDirection.UP;
        } else if (angle >= 3 * Math.PI / 4 || angle <= -3 * Math.PI / 4) {
            return LiveEntity.FacingDirection.LEFT;
        } else {
            return LiveEntity.FacingDirection.DOWN;
        }
    }

    @Override
    public void update(float delta) {
        if (getCurrentHealth() <= 0) {
            removeBody();
        }

        setFacingDirection(calculateFacingDirection());

        super.update(delta);
    }


    public enum FacingDirection {
        UP, DOWN, LEFT, RIGHT
    }
}
