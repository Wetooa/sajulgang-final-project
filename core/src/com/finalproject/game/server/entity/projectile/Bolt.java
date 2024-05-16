package com.finalproject.game.server.entity.projectile;

import com.finalproject.game.server.GameInstanceServer;
import com.finalproject.game.server.RemoteClient;
import com.finalproject.game.server.builder.entity.ProjectileBuilder;

public class Bolt extends Projectile {
    public Bolt(ProjectileBuilder builder) {
        super((ProjectileBuilder) builder.setMaxSpeed(40f).setSize(1).setDamage(15));
    }
}
