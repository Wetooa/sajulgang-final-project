package com.finalproject.game.client.packet.client;

public class MouseReleased extends MouseMove {
    public int button;

    public MouseReleased() {
    }

    public MouseReleased(float mouseX, float mouseY, int button) {
        super(mouseX, mouseY);
        this.button = button;
    }
}
