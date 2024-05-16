package com.finalproject.game.server.entity.projectile;

import com.badlogic.gdx.physics.box2d.Body;
import com.finalproject.game.server.GameInstanceServer;
import com.finalproject.game.server.RemoteClient;
import com.finalproject.game.server.builder.entity.ProjectileBuilder;

public class Shell extends Projectile {


    public Shell(ProjectileBuilder builder) {
        super((ProjectileBuilder) builder.setMaxSpeed(15f));
    }
}