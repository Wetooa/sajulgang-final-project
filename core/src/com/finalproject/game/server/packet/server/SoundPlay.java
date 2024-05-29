package com.finalproject.game.server.packet.server;

import com.finalproject.game.client.sound.SoundPlayer;

public class SoundPlay {

    public SoundPlayer.SoundType soundType;

    public SoundPlay() {
    }

    public SoundPlay(SoundPlayer.SoundType soundType) {
        this.soundType = soundType;
    }

}
