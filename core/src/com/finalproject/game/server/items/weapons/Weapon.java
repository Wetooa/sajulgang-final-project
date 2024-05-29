package com.finalproject.game.server.items.weapons;

import com.finalproject.game.server.builder.entity.ProjectileBuilder;
import com.finalproject.game.server.builder.item.WeaponBuilder;
import com.finalproject.game.server.entity.projectile.Projectile;
import com.finalproject.game.server.items.Item;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public abstract class Weapon extends Item {


    protected final float accuracy;
    protected final float range;

    public Weapon() {
        this(new WeaponBuilder());
    }

    public Weapon(WeaponBuilder builder) {
        super(builder);

        this.accuracy = builder.getAccuracy();
        this.range = builder.getRange();
    }

    public float getAccuracy() {
        return accuracy;
    }

    public float getRange() {
        return range;
    }

    public <T extends Projectile> void shoot(Class<T> projectile) {
        try {
            Constructor<T> constructor = projectile.getConstructor(ProjectileBuilder.class);
            gameInstanceServer.projectiles.add(constructor.newInstance(new ProjectileBuilder().setAccuracy(accuracy).setRange(range).setGameInstanceServer(gameInstanceServer).setRemoteClient(remoteClient).setCurrentWorld(currentWorld)));
        } catch (NoSuchMethodException | InstantiationException | IllegalAccessException |
                 InvocationTargetException e) {
            e.printStackTrace();
        }


    }


}
