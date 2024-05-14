package com.finalproject.game.server.entity.projectile;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.finalproject.game.server.builder.entity.ProjectileBuilder;
import com.finalproject.game.server.entity.Entity;

public abstract class Projectile extends Entity {

    protected int damage;
    protected float speed;

    // temp size
    protected float size;

    public Projectile(ProjectileBuilder builder) {
        super(builder);

        this.damage = builder.getDamage();
        this.speed = builder.getSpeed();

        float playerX = remoteClient.getPlayer().getBoxBody().getPosition().x;
        float playerY = remoteClient.getPlayer().getBoxBody().getPosition().y;

        float mouseX = remoteClient.getMouseX();
        float mouseY = remoteClient.getMouseY();

//        Vector3 mousePosition = GameClient.camera.unproject(new Vector3(mouseX, mouseY, 0));

        float angle = MathUtils.atan2(mouseY - playerY, mouseX - playerX);

        BodyDef bulletDef = new BodyDef();
        bulletDef.type = BodyDef.BodyType.DynamicBody;
        bulletDef.position.set(playerX + MathUtils.cos(angle), playerY + MathUtils.sin(angle));
        boxBody = gameInstanceServer.world.createBody(bulletDef);

        CircleShape bulletShape = new CircleShape();
        bulletShape.setRadius(0.5f);

        FixtureDef bulletFixture = new FixtureDef();
        bulletFixture.shape = bulletShape;
        bulletFixture.density = 0.5f;
        bulletFixture.restitution = 0.1f;
        boxBody.createFixture(bulletFixture);
        bulletShape.dispose();

        float impulseStrength = speed; // Adjust as needed
        Vector2 impulse = new Vector2(MathUtils.cos(angle) * impulseStrength, MathUtils.sin(angle) * impulseStrength);
        boxBody.applyLinearImpulse(impulse, boxBody.getWorldCenter(), true);
    }
}
