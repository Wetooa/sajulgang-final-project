package com.finalproject.game.server.entity.live.player;

import com.badlogic.gdx.math.Vector2;
import com.finalproject.game.server.builder.entity.LiveEntityBuilder;

public class Joshua extends Player {

    public Joshua() {
        this(new LiveEntityBuilder());
    }

    public Joshua(LiveEntityBuilder builder) {
        super((LiveEntityBuilder) builder.setPlayerType(PlayerType.JOSHUA).setMaxSpeed(1.5f).setSize(new Vector2(1.5f, 1.5f)));
    }

    @Override
    public void castSpecialSkill() {
    }

}