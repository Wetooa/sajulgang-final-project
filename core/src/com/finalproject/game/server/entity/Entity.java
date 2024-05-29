package com.finalproject.game.server.entity;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.finalproject.game.server.GameObject;
import com.finalproject.game.server.builder.entity.EntityBuilder;
import com.finalproject.game.server.statusEffect.Status;

import java.util.ArrayList;

public abstract class Entity extends GameObject {

    protected int damage;
    protected float maxSpeed;
    protected float stateTime;

    protected Vector2 pos;
    protected Vector2 size;

    protected float angle;
    protected transient BodyDef bodyDef;
    protected transient Body boxBody;
    protected transient CircleShape dynamicBox;
    protected transient FixtureDef fixtureDef;
    private ArrayList<Status> statuses;

    public Entity() {
        this(new EntityBuilder());
    }

    public Entity(EntityBuilder builder) {
        super(builder);

        this.maxSpeed = builder.getMaxSpeed();

        this.pos = builder.getPos();
        this.size = builder.getSize();
        this.damage = builder.getDamage();

        bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(this.pos);
        boxBody = currentWorld.createBody(bodyDef);

        dynamicBox = new CircleShape();
        dynamicBox.setRadius(this.size.x);

        fixtureDef = new FixtureDef();
        fixtureDef.shape = dynamicBox;
        fixtureDef.density = 0f;
        fixtureDef.friction = 0.3f;
        fixtureDef.restitution = 0.5f; // Bounciness

        boxBody.createFixture(fixtureDef);
        boxBody.setLinearDamping(10f);
        boxBody.setUserData(this);

        dynamicBox.dispose();
    }

    public float getAngle() {
        return angle;
    }

    public void setAngle(float angle) {
        this.angle = angle;
    }

    public BodyDef getBodyDef() {
        return bodyDef;
    }

    public void setBodyDef(BodyDef bodyDef) {
        this.bodyDef = bodyDef;
    }

    public CircleShape getDynamicBox() {
        return dynamicBox;
    }

    public void setDynamicBox(CircleShape dynamicBox) {
        this.dynamicBox = dynamicBox;
    }

    public FixtureDef getFixtureDef() {
        return fixtureDef;
    }

    public void setFixtureDef(FixtureDef fixtureDef) {
        this.fixtureDef = fixtureDef;
    }

    public Body getBoxBody() {
        return boxBody;
    }

    public void setBoxBody(Body boxBody) {
        this.boxBody = boxBody;
    }

    public float getMaxSpeed() {
        return maxSpeed;
    }

    public void setMaxSpeed(float maxSpeed) {
        this.maxSpeed = maxSpeed;
    }


    public Vector2 getPos() {
        return pos;
    }

    public void setPos(Vector2 pos) {
        this.pos = pos;
    }

    public void update(float delta) {
        this.stateTime += delta;
        this.pos = boxBody.getPosition();
        this.angle = boxBody.getAngle();
    }

    public Vector2 getSize() {
        return size;
    }

    public void removeBody() {
        gameInstanceServer.removeObject(boxBody);
    }

    public ArrayList<Status> getStatuses() {
        return statuses;
    }

    public void setStatuses(ArrayList<Status> statuses) {
        this.statuses = statuses;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public float getStateTime() {
        return stateTime;
    }

    public void setStateTime(float stateTime) {
        this.stateTime = stateTime;
    }


}


