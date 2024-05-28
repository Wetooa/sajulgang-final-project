package com.finalproject.game.client.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.finalproject.game.client.ClientController;
import com.finalproject.game.client.GameClient;
import com.finalproject.game.client.packet.client.MouseMove;
import com.finalproject.game.client.resources.Assets;
import com.finalproject.game.server.entity.live.LiveEntity;

import static com.finalproject.game.client.GameClient.*;

public class OverworldScreen implements Screen {
    private static final float UPDATE_INTERVAL = 0.1f;

    protected TiledMap tiledMap;
    protected OrthogonalTiledMapRenderer tiledMapRenderer;
    private float timeSinceLastUpdate = 0;

    @Override
    public void show() {

        tiledMap = new TmxMapLoader().load("OOP/ADRIAN NAA DIRI TANAN/ooptilesets/oopmap.tmx");
        tiledMapRenderer = new OrthogonalTiledMapRenderer(tiledMap);

    }

    public void sendMouseUpdates() {
        float deltaTime = Gdx.graphics.getDeltaTime();
        timeSinceLastUpdate += deltaTime;

        if (timeSinceLastUpdate >= UPDATE_INTERVAL) {
            Vector3 worldCoordinates = GameClient.camera.unproject(new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0));
            ClientController.client.sendUDP(new MouseMove(worldCoordinates.x, worldCoordinates.y));
            timeSinceLastUpdate = 0;
        }
    }

    @Override
    public void render(float v) {
        sendMouseUpdates();

        Gdx.gl.glClearColor(0.0f, 0, 0.0f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        Vector2 playerPos = GameClient.gameInstanceSnapshot.playerPos;

        if (playerPos != null) {
            GameClient.cameraX = playerPos.x;
            GameClient.cameraY = playerPos.y;

            camera.position.set(playerPos.x, playerPos.y, 0);
            camera.update();
        }

        tiledMapRenderer.setView(camera);
        tiledMapRenderer.render();

        shapeRenderer.setProjectionMatrix(camera.combined);
        shapeRenderer.begin();
        shapeRenderer.setColor(Color.WHITE);
        shapeRenderer.set(ShapeRenderer.ShapeType.Filled);

        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        GameClient.gameInstanceSnapshot.players.forEach(player -> {
            Vector2 pos = player.getPos();
            float size = player.getSize();

            LiveEntity.FacingDirection facingDirection = player.getFacingDirection();

            TextureRegion frameTexture = Assets.getAssetFramePlayer(player.getPlayerState(), facingDirection, player.getStateTime());
            batch.draw(frameTexture, pos.x - size / 2, pos.y - size / 2);
        });
        batch.end();

        GameClient.gameInstanceSnapshot.enemies.forEach(enemy -> {
            Vector2 pos = enemy.getPos();
            shapeRenderer.setColor(Color.RED);
            shapeRenderer.circle(pos.x, pos.y, enemy.getSize());
        });

        GameClient.gameInstanceSnapshot.projectiles.forEach(projectile -> {
            Vector2 pos = projectile.getPos();
            shapeRenderer.setColor(Color.GREEN);
            shapeRenderer.circle(pos.x, pos.y, projectile.getSize());
        });

        shapeRenderer.end();
    }

    @Override
    public void resize(int width, int height) {
        camera.setToOrtho(false, width, height);
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

    @Override
    public void dispose() {
        tiledMapRenderer.dispose();
        tiledMap.dispose();
    }

}
