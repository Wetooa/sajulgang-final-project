package com.finalproject.game.client.packet.client;

public class MousePressed extends  MouseMove {

    public int button;

    public MousePressed() {
    }

    public MousePressed(float mouseX, float mouseY, int button) {
        super(mouseX, mouseY);
        this.button = button;
    }
}
