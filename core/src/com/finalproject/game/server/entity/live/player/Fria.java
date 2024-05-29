package com.finalproject.game.server.entity.live.player;

import com.badlogic.gdx.math.Vector2;
import com.finalproject.game.server.builder.entity.LiveEntityBuilder;

public class Fria extends Player {

    public Fria() {
        this(new LiveEntityBuilder());
    }

    public Fria(LiveEntityBuilder builder) {
        super((LiveEntityBuilder) builder.setPlayerType(PlayerType.FRIA).setMaxSpeed(3f).setSize(new Vector2(1.5f, 1.5f)));
    }

    @Override
    public void castSpecialSkill() {
    }

}
