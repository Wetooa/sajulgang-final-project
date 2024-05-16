package com.finalproject.game.server.entity.projectile;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.finalproject.game.server.builder.entity.ProjectileBuilder;
import com.finalproject.game.server.entity.Entity;

public abstract class Projectile extends Entity {

    protected float angle;

    public Projectile(ProjectileBuilder builder) {

        super(builder);

        float playerX = remoteClient.getPlayer().getBoxBody().getPosition().x;
        float playerY = remoteClient.getPlayer().getBoxBody().getPosition().y;

        float mouseX = remoteClient.getMouseX();
        float mouseY = remoteClient.getMouseY();

        this.angle = MathUtils.atan2(mouseY - playerY, mouseX - playerX);

        boxBody.setTransform(new Vector2(playerX + MathUtils.cos(angle), playerY + MathUtils.sin(angle)), 0);
    }

    @Override
    public void update(float delta) {
        float impulseStrength = getMaxSpeed(); // Adjust as needed
        Vector2 impulse = new Vector2(MathUtils.cos(angle) * impulseStrength, MathUtils.sin(angle) * impulseStrength);
        boxBody.applyLinearImpulse(impulse, boxBody.getWorldCenter(), true);
    }
}
