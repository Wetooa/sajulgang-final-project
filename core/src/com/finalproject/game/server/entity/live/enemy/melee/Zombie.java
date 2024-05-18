package com.finalproject.game.server.entity.live.enemy.melee;

import com.badlogic.gdx.math.Vector2;
import com.finalproject.game.server.GameInstanceServer;
import com.finalproject.game.server.builder.entity.LiveEntityBuilder;
import com.finalproject.game.server.entity.live.enemy.Enemy;

public class Zombie extends Enemy {

    public Zombie() {
        this(new LiveEntityBuilder());
    }

    public Zombie(LiveEntityBuilder builder) {
        super(builder);
    }

    @Override
    public void update(float delta) {
        super.update(delta);

        Vector2 zombiePosition = boxBody.getPosition();
        Vector2 playerPosition = gameInstanceServer.players.stream().iterator().next().getBoxBody().getPosition();

        Vector2 direction = playerPosition.cpy().sub(zombiePosition).nor();
        boxBody.applyLinearImpulse(direction.scl(maxSpeed), boxBody.getWorldCenter(), true);
    }

}
