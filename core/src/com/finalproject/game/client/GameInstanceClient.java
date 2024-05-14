package com.finalproject.game.client;

import com.finalproject.game.client.entity.live.player.Player;
import com.finalproject.game.server.packet.server.GameInitialization;

import java.util.ArrayList;
import java.util.List;

public class GameInstanceClient {

    public List<Player> players = new ArrayList<>();

    public GameInstanceClient() {}

    public GameInstanceClient(GameInitialization gameInitialization) {
        this.players = gameInitialization.players;
    }

}
