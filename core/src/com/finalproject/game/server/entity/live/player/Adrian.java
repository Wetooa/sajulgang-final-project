package com.finalproject.game.server.entity.live.player;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.finalproject.game.server.builder.entity.LiveEntityBuilder;

public class Adrian extends Player {

    public static final float TELEPORT_DISTANCE = 10;

    public Adrian() {
        this(new LiveEntityBuilder());
    }

    public Adrian(LiveEntityBuilder builder) {
        super((LiveEntityBuilder) builder.setPlayerType(PlayerType.ADRIAN).setSpecialSkillTimer(5f).setMaxHealth(150).setMaxStamina(200).setMaxSpeed(1.5f).setSize(new Vector2(1.5f, 1.5f)));
    }

    @Override
    public void update(float delta) {
        super.update(delta);


    }

    @Override
    public void castSpecialSkill() {
        float angle = getAngleFromMouse();
        boxBody.setTransform(new Vector2(pos.x + MathUtils.cos(angle) * this.getSize().x * TELEPORT_DISTANCE, pos.y + MathUtils.sin(angle) * this.getSize().y * TELEPORT_DISTANCE), 0);
    }

}
