package com.finalproject.game.server.entity.live.player;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.finalproject.game.server.builder.entity.LiveEntityBuilder;
import com.finalproject.game.server.entity.Entity;
import com.finalproject.game.server.items.Item;
import com.finalproject.game.server.items.weapons.range.HandGun;

import java.util.ArrayList;

public class Player extends Entity {

    protected boolean isRunning = false;
    protected float runningMultiplier;
    protected int currentStamina;
    protected int maxStamina;

    protected int currentItemHeld = 0;

    protected ArrayList<Item> items = new ArrayList<>();

    public Player(LiveEntityBuilder builder) {
        super(builder);

        this.runningMultiplier = builder.getRunningMultiplier();
        this.currentStamina = builder.getCurrentStamina();
        this.maxStamina = builder.getMaxStamina();

        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(posX, posY);
        boxBody = gameInstanceServer.world.createBody(bodyDef);

        // player collision box
        PolygonShape dynamicBox = new PolygonShape();
        dynamicBox.setAsBox(1.0f, 1.0f);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = dynamicBox;
        fixtureDef.density = 1.0f;
        fixtureDef.friction = 0.5f;
        fixtureDef.restitution = 0.5f; // Bounciness

        boxBody.createFixture(fixtureDef);
        boxBody.setLinearDamping(5.0f);
        dynamicBox.dispose();

        items.add(new HandGun(getGameInstanceServer(), getRemoteClient()));
    }


    public boolean isRunning() {
        return isRunning;
    }

    public void setRunning(boolean running) {
        isRunning = running;
    }

    public void updateMouseAction(int keycode) {
        if (keycode == Input.Buttons.LEFT) {
            shoot();
        }
    }

    public void updateMovement(int keycode) {
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

    public void shoot() {
        // TODO: temp fix
        Item heldItem = items.get(currentItemHeld);
        heldItem.activate();
    }
};