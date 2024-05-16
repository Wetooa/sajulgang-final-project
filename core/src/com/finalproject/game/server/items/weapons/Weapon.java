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


    protected Weapon(WeaponBuilder builder) {
        super(builder);

        this.accuracy = builder.getAccuracy();
        this.range = builder.getRange();
    }

    public <T extends Projectile> void shoot(Class<T> projectile) {
        try {
            Constructor<T> constructor = projectile.getConstructor(ProjectileBuilder.class);
            gameInstanceServer.projectiles.add(constructor.newInstance((ProjectileBuilder) new ProjectileBuilder().setGameInstanceServer(gameInstanceServer).setRemoteClient(remoteClient)));
        } catch (NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }


    }


}
