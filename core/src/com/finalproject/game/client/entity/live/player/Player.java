package com.finalproject.game.client.entity.live.player;

import com.finalproject.game.client.builder.PlayerBuilder;
import com.finalproject.game.client.entity.Entity;
import com.finalproject.game.server.items.ItemBox;

public class Player extends Entity {

    public com.finalproject.game.server.entity.live.player.Player.PlayerState playerState = com.finalproject.game.server.entity.live.player.Player.PlayerState.WALK;
    public ItemBox itemBox = new ItemBox();

    public Player() {
        this(new PlayerBuilder());
    }

    public Player(PlayerBuilder builder) {
        super(builder);

        this.playerState = builder.getPlayerState();
        this.itemBox = builder.getItemBox();
    }

    public com.finalproject.game.server.entity.live.player.Player.PlayerState getPlayerState() {
        return playerState;
    }

    public void setPlayerState(com.finalproject.game.server.entity.live.player.Player.PlayerState playerState) {
        this.playerState = playerState;
    }
}
