package com.finalproject.game.server.entity.live.player;

import com.badlogic.gdx.math.Vector2;
import com.finalproject.game.server.builder.entity.LiveEntityBuilder;

public class Adrian extends Player {


//    private float
//    private void

    public Adrian() {
        this(new LiveEntityBuilder());
    }

    public Adrian(LiveEntityBuilder builder) {
        super((LiveEntityBuilder) builder.setPlayerType(PlayerType.ADRIAN).setMaxSpeed(3f).setSize(new Vector2(1.5f, 1.5f)));
    }

    @Override
    public void update(float delta) {
        super.update(delta);


    }

    @Override
    public void castSpecialSkill() {


    }

}
