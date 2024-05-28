package com.finalproject.game.client.builder;

import com.finalproject.game.server.entity.live.player.Player;

public class PlayerBuilder extends EntityBuilder {
    public Player.PlayerState playerState = Player.PlayerState.IDLE;

    public Player.PlayerState getPlayerState() {
        return playerState;
    }

    public PlayerBuilder setPlayerState(Player.PlayerState playerState) {
        this.playerState = playerState;
        return this;
    }
}
