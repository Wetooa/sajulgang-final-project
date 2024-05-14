package com.finalproject.game.client.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.finalproject.game.client.GameClient;

import static com.finalproject.game.client.GameClient.camera;
import static com.finalproject.game.client.GameClient.shapeRenderer;

public class OverworldScreen implements Screen {

    @Override
    public void show() {


    }

    @Override
    public void render(float v) {
        Gdx.gl.glClearColor(0.0f, 0, 0.0f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        shapeRenderer.setProjectionMatrix(camera.combined);
        shapeRenderer.begin();
        shapeRenderer.setColor(Color.WHITE);
        shapeRenderer.set(ShapeRenderer.ShapeType.Filled);

        GameClient.gameInstanceSnapshot.players.forEach(player -> {
            Vector2 pos = player.getPos();
            shapeRenderer.rect(pos.x, pos.y, 1, 1);
        });

        GameClient.gameInstanceSnapshot.projectiles.forEach(projectile -> {
            Vector2 pos = projectile.getPos();
            shapeRenderer.rect(pos.x, pos.y, 1, 1);
        });

        shapeRenderer.end();
    }

    @Override
    public void resize(int i, int i1) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    public void dispose() {
    }

}
