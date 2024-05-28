package com.finalproject.game.server.entity.live.player;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;
import com.finalproject.game.server.RemoteClient;
import com.finalproject.game.server.builder.entity.LiveEntityBuilder;
import com.finalproject.game.server.builder.item.WeaponBuilder;
import com.finalproject.game.server.entity.live.LiveEntity;
import com.finalproject.game.server.items.Item;
import com.finalproject.game.server.items.ItemBox;
import com.finalproject.game.server.items.weapons.range.primary.LaserGun;

public class Player extends LiveEntity {

    protected boolean isRunning = false;
    protected float runningMultiplier;
    protected int currentStamina;
    protected int maxStamina;

    protected ItemBox itemBox = new ItemBox();

    public Player() {
        this(new LiveEntityBuilder());
    }

    public Player(LiveEntityBuilder builder) {
        super(builder);

        this.runningMultiplier = builder.getRunningMultiplier();
        this.currentStamina = builder.getCurrentStamina();
        this.maxStamina = builder.getMaxStamina();

        itemBox.addItem(new LaserGun((WeaponBuilder) new WeaponBuilder().setGameInstanceServer(gameInstanceServer).setRemoteClient(remoteClient).setCurrentWorld(currentWorld)));
    }


    public int getCurrentStamina() {
        return currentStamina;
    }

    public void setCurrentStamina(int currentStamina) {
        this.currentStamina = currentStamina;
    }

    public int getMaxStamina() {
        return maxStamina;
    }

    public void setMaxStamina(int maxStamina) {
        this.maxStamina = maxStamina;
    }

    public ItemBox getItemBox() {
        return itemBox;
    }

    public boolean isRunning() {
        return isRunning;
    }

    public void setRunning(boolean running) {
        isRunning = running;
    }


    @Override
    public void update(float delta) {
        super.update(delta);

        if (!this.getRemoteClient().getClientGameState().equals(RemoteClient.ClientGameState.ALIVE)) return;


        if (isRunning) currentStamina = Math.max(0, currentStamina - 1);
        else currentStamina = Math.min(maxStamina, currentStamina + 1);

        this.setRunning(remoteClient.getInputStates().contains(Input.Keys.SHIFT_LEFT));

        if (this.currentHealth <= 0) {
            this.getRemoteClient().setClientGameState(RemoteClient.ClientGameState.DEAD);
        }

        remoteClient.getInputStates().forEach(keycode -> this.updateMovement(delta, keycode));
        remoteClient.getMouseButtonStates().forEach(button -> this.updateMouseAction(delta, button));
    }

    public void updateMouseAction(float delta, int keycode) {
        if (keycode == Input.Buttons.LEFT) {
            shoot(delta);
        }
    }

    public void updateMovement(float delta, int keycode) {
        float currentSpeed = getMaxSpeed() * (isRunning && currentStamina > 0 ? runningMultiplier : 1);

        if (keycode == Input.Keys.D) {
            boxBody.applyLinearImpulse(new Vector2(currentSpeed, 0), boxBody.getWorldCenter(), true);
        }
        if (keycode == Input.Keys.A) {
            boxBody.applyLinearImpulse(new Vector2(-currentSpeed, 0), boxBody.getWorldCenter(), true);
        }
        if (keycode == Input.Keys.W) {
            boxBody.applyLinearImpulse(new Vector2(0, currentSpeed), boxBody.getWorldCenter(), true);
        }
        if (keycode == Input.Keys.S) {
            boxBody.applyLinearImpulse(new Vector2(0, -currentSpeed), boxBody.getWorldCenter(), true);
        }
    }

    public void shoot(float delta) {
        Item heldItem = itemBox.getHeldItem();
        heldItem.activate(delta);
    }
}