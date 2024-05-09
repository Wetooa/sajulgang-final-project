package com.finalproject.game.entities.enemies;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.finalproject.game.Storage;
import com.finalproject.game.builder.EntityBuilder;
import com.finalproject.game.entities.Entity;
import com.finalproject.game.screens.OverworldScreen;

import static com.finalproject.game.screens.OverworldScreen.camera;
import static com.finalproject.game.screens.OverworldScreen.world;

public class Zombie extends Enemy {

    public Zombie() {
        super(new EntityBuilder());
    }

    @Override
    public void create() {
        // Dynamic body
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(0, camera.viewportHeight);
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


    public void update() {
        Vector2 zombiePosition = boxBody.getPosition();
        Vector2 playerPosition = Storage.players.get(0).getBoxBody().getPosition();

        Vector2 direction = playerPosition.cpy().sub(zombiePosition).nor();
        boxBody.applyLinearImpulse(direction.scl(maxSpeed), boxBody.getWorldCenter(), true);
    }


    @Override
    public void render() {
        update();
    }
}
