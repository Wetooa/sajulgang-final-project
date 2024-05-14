package com.finalproject.game.server.entities.live.player;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.finalproject.game.server.builder.EntityBuilder;
import com.finalproject.game.server.entities.Entity;
import com.finalproject.game.server.entities.items.Item;
import com.finalproject.game.server.entities.projectile.Bullet;

import java.util.ArrayList;

public class Player extends Entity {

    protected boolean isRunning = false;
    protected float runningMultiplier;
    protected int currentStamina;
    protected int maxStamina;

    private ArrayList<Item> items;

    public Player(EntityBuilder builder) {
        super(builder);

        this.runningMultiplier = builder.getRunningMultiplier();
        this.currentStamina = builder.getCurrentStamina();
        this.maxStamina = builder.getMaxStamina();

        // Dynamic body
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(posX, posY);
        boxBody = gameInstance.world.createBody(bodyDef);

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
    }

    public void move(int keycode) {
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

//        if (Gdx.input.isButtonPressed(Input.Buttons.LEFT)) {
//            shoot();
//        }
    }

    private void handleInput() {

//        isRunning = Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT);
//        float currentSpeed = getMaxSpeed() * (isRunning ? runningMultiplier : 1);
        // TODO: and other statusses


//            public boolean keyUp(int keycode) {
//                if (keycode == Input.Keys.LEFT || keycode == Input.Keys.A)
//                    clientController.client.sendUDP(new KeyReleased(finalKEY_LEFT));
//                if (keycode == Input.Keys.RIGHT || keycode == Input.Keys.D)
//                    clientController.client.sendUDP(new KeyReleased(finalKEY_RIGHT));
//
//
//                return false;
//            }

//        if (Gdx.input.isKeyPressed(Input.Keys.D)) {
//            boxBody.applyLinearImpulse(new Vector2(currentSpeed, 0), boxBody.getWorldCenter(), true);
//        }
//        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
//            boxBody.applyLinearImpulse(new Vector2(-currentSpeed, 0), boxBody.getWorldCenter(), true);
//        }
//        if (Gdx.input.isKeyPressed(Input.Keys.W)) {
//            boxBody.applyLinearImpulse(new Vector2(0, currentSpeed), boxBody.getWorldCenter(), true);
//        }
//        if (Gdx.input.isKeyPressed(Input.Keys.S)) {
//            boxBody.applyLinearImpulse(new Vector2(0, -currentSpeed), boxBody.getWorldCenter(), true);
//        }
//
//        if (Gdx.input.isButtonPressed(Input.Buttons.LEFT)) {
//            shoot();
//        }
//
//        if (Gdx.input.isButtonPressed(Input.Buttons.RIGHT)) {
//            // TODO: but cooler
//            shoot();
//        }
    }

    public void shoot() {
        new Bullet(boxBody);
    }


    public void render() {

    }
};