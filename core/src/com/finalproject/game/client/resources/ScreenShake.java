package com.finalproject.game.client.resources;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

import static com.finalproject.game.client.GameClient.camera;

public class ScreenShake {
    private final Vector2 shakeOffset;
    private float shakeTime;
    private float currentShakeTime;
    private float power;
    private float currentPower;

    public ScreenShake() {
        shakeOffset = new Vector2();
    }

    public void shake(float power, float duration) {
        this.power = power;
        this.shakeTime = duration;
        this.currentShakeTime = 0;
    }

    public void update(float deltaTime) {
        if (currentShakeTime < shakeTime) {
            currentShakeTime += deltaTime;
            currentPower = power * ((shakeTime - currentShakeTime) / shakeTime);

            // Randomize the shake offsets
            float x = (MathUtils.random() - 0.5f) * 2 * currentPower;
            float y = (MathUtils.random() - 0.5f) * 2 * currentPower;
            shakeOffset.set(x, y);

            // Apply the shake offset to the camera position
            camera.position.add(shakeOffset.x, shakeOffset.y, 0);
            camera.update();
        } else {
            shakeOffset.set(0, 0);
        }
    }
}

