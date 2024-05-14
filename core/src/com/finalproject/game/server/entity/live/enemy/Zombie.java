package com.finalproject.game.server.entity.live.enemy;

import com.finalproject.game.server.builder.EntityBuilder;

public class Zombie extends Enemy {

    public Zombie() {
        super(new EntityBuilder());

        // Dynamic body
//        BodyDef bodyDef = new BodyDef();
//        bodyDef.type = BodyDef.BodyType.DynamicBody;
//        bodyDef.position.set(0, GameClient.camera.viewportHeight);
//        boxBody = GameClient.world.createBody(bodyDef);
//
//        PolygonShape dynamicBox = new PolygonShape();
//        dynamicBox.setAsBox(1.0f, 1.0f);
//
//        FixtureDef fixtureDef = new FixtureDef();
//        fixtureDef.shape = dynamicBox;
//        fixtureDef.density = 1.0f;
//        fixtureDef.friction = 0.5f;
//        fixtureDef.restitution = 0.5f; // Bounciness
//
//        boxBody.createFixture(fixtureDef);
//        boxBody.setLinearDamping(5.0f);
//        dynamicBox.dispose();
    }


    public void update() {
//        Vector2 zombiePosition = boxBody.getPosition();
//        Vector2 playerPosition = Storage.players.get(0).getBoxBody().getPosition();

//        Vector2 direction = playerPosition.cpy().sub(zombiePosition).nor();
//        boxBody.applyLinearImpulse(direction.scl(maxSpeed), boxBody.getWorldCenter(), true);
    }

}
