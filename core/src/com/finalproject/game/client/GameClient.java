package com.finalproject.game.client;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.finalproject.game.client.screens.OverworldScreen;
import com.finalproject.game.server.entity.live.player.Player;
import com.finalproject.game.server.packet.server.GameInstanceSnapshot;

import java.io.IOException;
import java.util.ArrayList;

public class GameClient extends Game {
    public static GameInstanceSnapshot gameInstanceSnapshot = new GameInstanceSnapshot();
    public static GameClient gameClient;
    public static ClientController clientController;
    public static ShapeRenderer shapeRenderer;
    public static OrthographicCamera camera;
    public static Screen screen;
    public static ArrayList<Player> players;
    public static SpriteBatch batch;

    @Override
    public void create() {
        batch = new SpriteBatch();
        gameClient = this;

        Gdx.app.log("Client: ", "Initializing client");
        Gdx.app.postRunnable(this::connectToServer);

        float w = Gdx.graphics.getWidth();
        float h = Gdx.graphics.getHeight();

        camera = new OrthographicCamera(1500, 1500 * (h / w));
        camera.position.set(camera.viewportWidth / 2f, camera.viewportHeight / 2f, 0);
        camera.update();

        shapeRenderer = new ShapeRenderer();
        screen = new OverworldScreen();
        shapeRenderer.setAutoShapeType(true);

        setScreen(screen);
    }


    @Override
    public void render() {
        super.render();
    }

    @Override
    public void dispose() {
        batch.dispose();
        shapeRenderer.dispose();
    }


    public void initializeClient() {
    }


    public void connectToServer() {
        String ip = "192.168.1.22";
        ip = "127.0.0.1";

        try {
            clientController = new ClientController(ip);
            Gdx.app.log("Client Connection", "Connected to server successfully");
            //            info.setText("Connected, waiting for opponent");
        } catch (IOException e) {
            Gdx.app.log("Client Connection", "Could not connect to the main server");
            //            info.setText("Could not connect to the main server");
            e.printStackTrace();
        }
    }
}
