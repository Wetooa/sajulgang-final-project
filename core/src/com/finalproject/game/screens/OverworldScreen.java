package com.finalproject.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.finalproject.game.MainGame;
import com.finalproject.game.Storage;
import com.finalproject.game.builder.EntityBuilder;
import com.finalproject.game.client.ClientController;
import com.finalproject.game.entities.Entity;
import com.finalproject.game.entities.enemies.Enemy;
import com.finalproject.game.entities.enemies.Zombie;
import com.finalproject.game.entities.player.Player;

import java.io.IOException;
import java.util.ArrayList;

public class OverworldScreen implements Screen  {

    public static World world;
    public static Box2DDebugRenderer debugRenderer;
    public static OrthographicCamera camera;

    private Body groundBody;

    public MainGame mainGame;

    public OverworldScreen(MainGame mainGame) {
        this.mainGame = mainGame;

        Gdx.app.log("Screen", "Initializing overworld screen");


        Gdx.app.postRunnable(this::createUiForNet);
    }

    public OrthographicCamera getCamera() {
        return camera;
    }
    public World getWorld() {
        return world;
    }


    public void createUiForNet() {
        String ip = "127.0.0.1";
        try {
            ClientController.clientController = new ClientController(ip);
            Gdx.app.log("Client Connection", "Connected to server successfully");
//            info.setText("Connected, waiting for opponent");
        } catch (IOException e) {
            Gdx.app.log("Client Connection", "Could not connect to the main server");
//            info.setText("Could not connect to the main server");

            e.printStackTrace();
        }
    }


    @Override
    public void show() {

        float w = Gdx.graphics.getWidth();
        float h = Gdx.graphics.getHeight();

        camera = new OrthographicCamera(50, 50 * (h / w));
        camera.position.set(camera.viewportWidth / 2f, camera.viewportHeight / 2f, 0);
        camera.update();

        world = new World(new Vector2(0, 0), true);
        debugRenderer = new Box2DDebugRenderer();

        // Ground body
        BodyDef groundBodyDef = new BodyDef();
        groundBodyDef.position.set(new Vector2(0, 10));
        groundBody = world.createBody(groundBodyDef);

        PolygonShape groundBox = new PolygonShape();
        groundBox.setAsBox(camera.viewportWidth, 1.0f);
        groundBody.createFixture(groundBox, 0.0f);
        groundBox.dispose();

        Storage.players.add(new Player(new EntityBuilder()));

//        Storage.enemies.add(new Zombie());
//        Storage.enemies.add(new Zombie());
//        Storage.enemies.add(new Zombie());
//        Storage.enemies.add(new Zombie());

    }

    @Override
    public void render(float v) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        world.step(1/60f, 6, 2);
        debugRenderer.render(world, camera.combined);
        camera.update();

        Storage.players.forEach(Player::render);

        Storage.enemies.forEach(Enemy::render);
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
        world.dispose();
        debugRenderer.dispose();
    }

}
