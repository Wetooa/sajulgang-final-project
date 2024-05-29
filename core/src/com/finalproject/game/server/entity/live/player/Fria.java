package com.finalproject.game.server.entity.live.player;

import com.badlogic.gdx.math.Vector2;
import com.finalproject.game.server.builder.entity.LiveEntityBuilder;

public class Fria extends Player {

    public static final int SKILL_HEALTH_INCREASE = 50;

    public Fria() {
        this(new LiveEntityBuilder());
    }

    public Fria(LiveEntityBuilder builder) {
        super((LiveEntityBuilder) builder.setPlayerType(PlayerType.FRIA).setSpecialSkillTimer(10f).setSpecialSkillStaminaCost(100).setMaxHealth(300).setMaxStamina(200).setMaxSpeed(0.75f).setSize(new Vector2(1.5f, 1.5f)));
    }

    @Override
    public void castSpecialSkill() {
        increaseCurrentHealth(SKILL_HEALTH_INCREASE);
    }

}
