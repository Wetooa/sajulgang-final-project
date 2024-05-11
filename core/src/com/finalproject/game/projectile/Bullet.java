package com.finalproject.game.projectile;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;

import static com.finalproject.game.screens.OverworldScreen.camera;
import static com.finalproject.game.screens.OverworldScreen.world;

public class Bullet extends Projectile {



    public Bullet(Body boxBody) {
        float playerX = boxBody.getPosition().x;
        float playerY = boxBody.getPosition().y;

        float mouseX = Gdx.input.getX();
        float mouseY = Gdx.input.getY(); // Y-axis not inverted for screen coordinates
        Vector3 mousePosition = camera.unproject(new Vector3(mouseX, mouseY, 0));

        float angle = MathUtils.atan2(mousePosition.y - playerY, mousePosition.x - playerX);

        BodyDef bulletDef = new BodyDef();
        bulletDef.type = BodyDef.BodyType.DynamicBody;
        bulletDef.position.set(playerX + MathUtils.cos(angle), playerY + MathUtils.sin(angle));
        Body bullet = world.createBody(bulletDef);

        CircleShape bulletShape = new CircleShape();
        bulletShape.setRadius(0.2f);
        FixtureDef bulletFixture = new FixtureDef();
        bulletFixture.shape = bulletShape;
        bulletFixture.density = 0.5f;
        bulletFixture.restitution = 0.1f;
        bullet.createFixture(bulletFixture);
        bulletShape.dispose();

        float impulseStrength = 10f; // Adjust as needed
        Vector2 impulse = new Vector2(MathUtils.cos(angle) * impulseStrength, MathUtils.sin(angle) * impulseStrength);
        bullet.applyLinearImpulse(impulse, bullet.getWorldCenter(), true);
    }

}
