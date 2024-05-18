package com.finalproject.game.server.entity.live.player;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;
import com.finalproject.game.server.builder.entity.LiveEntityBuilder;
import com.finalproject.game.server.builder.item.WeaponBuilder;
import com.finalproject.game.server.entity.live.LiveEntity;
import com.finalproject.game.server.items.Item;
import com.finalproject.game.server.items.ItemBox;
import com.finalproject.game.server.items.weapons.melee.Katana;
import com.finalproject.game.server.items.weapons.melee.Tukog;
import com.finalproject.game.server.items.weapons.range.CrossBow;
import com.finalproject.game.server.items.weapons.range.HandGun;
import com.finalproject.game.server.items.weapons.range.LaserGun;

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

        itemBox.addItem(new HandGun((WeaponBuilder) new WeaponBuilder().setGameInstanceServer(gameInstanceServer).setRemoteClient(remoteClient)));
        itemBox.addItem(new LaserGun((WeaponBuilder) new WeaponBuilder().setGameInstanceServer(gameInstanceServer).setRemoteClient(remoteClient)));
        itemBox.addItem(new Tukog((WeaponBuilder) new WeaponBuilder().setGameInstanceServer(gameInstanceServer).setRemoteClient(remoteClient)));
        itemBox.addItem(new CrossBow((WeaponBuilder) new WeaponBuilder().setGameInstanceServer(gameInstanceServer).setRemoteClient(remoteClient)));
        itemBox.addItem(new Katana((WeaponBuilder) new WeaponBuilder().setGameInstanceServer(gameInstanceServer).setRemoteClient(remoteClient)));
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

        this.setRunning(remoteClient.getInputStates().contains(Input.Keys.SHIFT_LEFT));

        remoteClient.getInputStates().forEach(keycode -> this.updateMovement(delta, keycode));
        remoteClient.getMouseButtonStates().forEach(button -> this.updateMouseAction(delta, button));
    }

    public void updateMouseAction(float delta, int keycode) {
        if (keycode == Input.Buttons.LEFT) {
            shoot(delta);
        }
    }

    public void updateMovement(float delta, int keycode) {
        float currentSpeed = getMaxSpeed() * (isRunning ? runningMultiplier : 1);

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