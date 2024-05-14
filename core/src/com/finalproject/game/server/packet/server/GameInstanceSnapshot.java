package com.finalproject.game.server.packet.server;

import com.badlogic.gdx.math.Vector2;
import com.finalproject.game.server.entities.live.player.Player;
import com.finalproject.game.server.GameInstanceServer;
import com.finalproject.game.server.RemoteClient;

import java.util.ArrayList;
import java.util.Map;

public class GameInstanceSnapshot {

    public ArrayList<Vector2> players = new ArrayList<>();

    public GameInstanceSnapshot() {}

    public GameInstanceSnapshot(GameInstanceServer gameInstance) {
        for (Map.Entry<RemoteClient, Player> player : gameInstance.players.entrySet())  {
            Player p = player.getValue();
            players.add(p.getBoxBody().getPosition());
        }
        System.out.println(players);
    }


}
