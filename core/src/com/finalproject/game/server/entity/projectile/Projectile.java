package com.finalproject.game.server.entity.projectile;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.finalproject.game.server.builder.entity.ProjectileBuilder;
import com.finalproject.game.server.entity.Entity;

public abstract class Projectile extends Entity {

    protected float angle;
    protected float range;
    protected float expiration = 0;

    public Projectile() {
        this(new ProjectileBuilder());
    }

    public Projectile(ProjectileBuilder builder) {
        super(builder);

        float playerX = remoteClient.getPlayer().getBoxBody().getPosition().x;
        float playerY = remoteClient.getPlayer().getBoxBody().getPosition().y;

        float mouseX = remoteClient.getMouseX();
        float mouseY = remoteClient.getMouseY();

        this.angle = MathUtils.atan2(mouseY - playerY, mouseX - playerX);
        this.range = builder.getRange();
        this.expiration = range;


        float acc_x = builder.getAccuracy();
        float acc_y = builder.getAccuracy();

        boxBody.setTransform(new Vector2(playerX + MathUtils.cos(angle) * this.getSizeX(), playerY + MathUtils.sin(angle) * this.getSizeY()), 0);
    }

    @Override
    public void update(float delta) {
        super.update(delta);

        expiration -= delta;
        if (expiration <= 0) {
            removeBody();
            return;
        }

        float impulseStrength = getMaxSpeed(); // Adjust as needed
        Vector2 impulse = new Vector2(MathUtils.cos(angle) * impulseStrength, MathUtils.sin(angle) * impulseStrength);
        boxBody.applyLinearImpulse(impulse, boxBody.getWorldCenter(), true);
    }
}
