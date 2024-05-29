package com.finalproject.game.server.entity.live.player;

import com.badlogic.gdx.math.Vector2;
import com.finalproject.game.server.builder.entity.LiveEntityBuilder;

public class Stephen extends Player {

    public Stephen() {
        this(new LiveEntityBuilder());
    }

    public Stephen(LiveEntityBuilder builder) {
        super((LiveEntityBuilder) builder.setPlayerType(PlayerType.STEPHEN).setMaxSpeed(2f).setSize(new Vector2(1.5f, 1.5f)));
    }

    @Override
    public void castSpecialSkill() {
    }

}
