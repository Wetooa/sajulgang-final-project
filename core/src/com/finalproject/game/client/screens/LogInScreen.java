package com.finalproject.game.client.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.finalproject.game.client.ClientController;
import com.finalproject.game.client.GameClient;
import com.finalproject.game.client.packet.client.jdbc.LoginPacket;

public class LogInScreen implements Screen {
    private final Stage stage;
    private final Skin skin;
    private final OrthographicCamera camera;
    private final TextureAtlas atlas;

    public LogInScreen() {
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
        table.setBackground(skin.getDrawable("auth_screen"));
        stage.addActor(table);

        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("poppins_medium.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 20;
        BitmapFont font = generator.generateFont(parameter);
        generator.dispose();
        skin.add("default-font", font);

        // Set font for TextButton and Label styles
        skin.get(TextButton.TextButtonStyle.class).font = font;
        skin.get(Label.LabelStyle.class).font = font;
        skin.get(TextField.TextFieldStyle.class).font = font;

        Label usernameLabel = new Label("Enter your username: ", skin);
        TextField username = new TextField("", skin);
        username.setAlignment(Align.center);
        Label passwordLabel = new Label("Enter your password: ", skin);
        TextField password = new TextField("", skin);
        password.setAlignment(Align.center);
        password.setPasswordMode(true); // Enable password mode
        password.setPasswordCharacter('*');

        TextButton loginButton = new TextButton("Log In", skin);
        TextButton registerButton = new TextButton("Register", skin);
        TextButton backButton = new TextButton("Menu", skin);

        table.add(usernameLabel).fillX().uniformX().padRight(30);
        table.add(username).uniformX().fillX();
        table.row();
        table.add(passwordLabel).fillX().uniformX().padRight(30);
        table.add(password).uniformX().fillX();
        table.row();
        table.add(backButton);
        table.add(registerButton);
        table.row();
        table.add(loginButton).colspan(2);

        loginButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                ClientController.client.sendUDP(new LoginPacket(username.getText(), password.getText()));

                GameClient.gameClient.setScreen(new GameScreen());

                dispose();
            }
        });

        registerButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                GameClient.gameClient.setScreen(new SignUpScreen()); // Assuming you have a GameScreen class
                dispose();
            }
        });

        backButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                GameClient.gameClient.setScreen(new MainMenu2()); // Assuming you have a GameScreen class
                dispose();
            }
        });
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
        GameClient.batch.setProjectionMatrix(camera.combined);
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
