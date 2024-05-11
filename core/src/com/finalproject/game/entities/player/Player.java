package com.finalproject.game.entities.player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.esotericsoftware.kryonet.Client;
import com.finalproject.game.builder.EntityBuilder;
import com.finalproject.game.client.ClientController;
import com.finalproject.game.entities.Entity;
import com.finalproject.game.items.Item;
import com.finalproject.game.projectile.Bullet;
import com.finalproject.game.server.packets.client.KeyPressed;

import java.util.ArrayList;

import static com.finalproject.game.client.ClientController.clientController;
import static com.finalproject.game.screens.OverworldScreen.*;

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
    }


    @Override
    public void create() {
        // Dynamic body
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2);
        boxBody = world.createBody(bodyDef);

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


    @Override
    public void render() {
        handleInput();


    }

    private void handleInput() {

//        isRunning = Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT);
//        float currentSpeed = getMaxSpeed() * (isRunning ? runningMultiplier : 1);
        // TODO: and other statusses


        Gdx.input.setInputProcessor(new InputAdapter() {
            public boolean keyDown(int keycode) {

                Gdx.app.log("Player", "Clicked " + keycode);

                ClientController.client.sendUDP(new KeyPressed(keycode));

                if (keycode == Input.Keys.SPACE)
                    Gdx.app.log("ping: ", ClientController.client.getReturnTripTime() + " ");

                return false;
            }

//            public boolean keyUp(int keycode) {
//                if (keycode == Input.Keys.LEFT || keycode == Input.Keys.A)
//                    clientController.client.sendUDP(new KeyReleased(finalKEY_LEFT));
//                if (keycode == Input.Keys.RIGHT || keycode == Input.Keys.D)
//                    clientController.client.sendUDP(new KeyReleased(finalKEY_RIGHT));
//
//
//                return false;
//            }
        });

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
};