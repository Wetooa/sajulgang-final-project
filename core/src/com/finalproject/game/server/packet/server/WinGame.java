package com.finalproject.game.server.packet.server;

public class WinGame {

    public boolean isWinner = false;

    public WinGame() {
    }

    public WinGame(boolean isWinner) {
        this.isWinner = isWinner;
    }
}
