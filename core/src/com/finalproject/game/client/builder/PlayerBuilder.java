package com.finalproject.game.client.builder;

import com.finalproject.game.server.entity.live.player.Player;
import com.finalproject.game.server.items.ItemBox;

public class PlayerBuilder extends EntityBuilder {

    public Player.PlayerState playerState = Player.PlayerState.IDLE;
    public ItemBox itemBox = new ItemBox();

    public ItemBox getItemBox() {
        return itemBox;
    }

    public PlayerBuilder setItemBox(ItemBox itemBox) {
        this.itemBox = itemBox;
        return this;
    }

    public Player.PlayerState getPlayerState() {
        return playerState;
    }

    public PlayerBuilder setPlayerState(Player.PlayerState playerState) {
        this.playerState = playerState;
        return this;
    }
}
