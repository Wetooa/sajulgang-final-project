package com.finalproject.game.client.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.finalproject.game.client.GameClient;

public class MainMenu2 implements Screen {
    private Stage stage;
    private Skin skin;
    private OrthographicCamera camera;
    private TextureAtlas atlas;

    public MainMenu2() {
        this.stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        this.camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);
        atlas = new TextureAtlas(Gdx.files.internal("main_menu.atlas"));
        skin = new Skin(Gdx.files.internal("main_menu.json"), atlas);

        createUI();
    }

    private void createUI() {
        Table table = new Table(skin);
        table.setFillParent(true);
        table.setBackground(skin.getDrawable("bg"));
        stage.addActor(table);

        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("poppins_medium.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 24;
        BitmapFont font = generator.generateFont(parameter);
        generator.dispose();

        skin.add("default-font", font);

        // Set font for TextButton and Label styles
        skin.get(TextButton.TextButtonStyle.class).font = font;

        TextButton playButton = new TextButton("Play", skin, "default");
        TextButton optionsButton = new TextButton("Options", skin, "default");
        TextButton exitButton = new TextButton("Exit", skin, "default");


        playButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                //connect to the server
//                Gdx.app.log("Client: ", "Initializing client");
//                Gdx.app.postRunnable(GameClient::connectToServer);
                GameClient.gameClient.setScreen(new LogInScreen()); // Assuming you have a GameScreen class
                dispose();
            }
        });

        optionsButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {

            }
        });

        exitButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.exit();
            }
        });

        table.bottom().padBottom(10);
        table.add(playButton).fillX().uniformX().padTop(5);
        table.row();
        table.add(optionsButton).fillX().uniformX().padBottom(5);
        table.row();
        table.add(exitButton).fillX().uniformX().padBottom(5);
    }

    @Override
    public void show() {
        createUI();
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();
        GameClient.gameClient.batch.setProjectionMatrix(camera.combined);
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
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
        stage.dispose();
        skin.dispose();
    }
}
