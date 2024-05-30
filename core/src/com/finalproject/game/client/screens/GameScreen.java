package com.finalproject.game.client.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.finalproject.game.client.ClientController;
import com.finalproject.game.client.GameClient;
import com.finalproject.game.client.packet.client.MouseMove;
import com.finalproject.game.client.resources.Assets;
import com.finalproject.game.client.sound.SoundPlayer;
import com.finalproject.game.server.GameInstanceServer;
import com.finalproject.game.server.RemoteClient;
import com.finalproject.game.server.entity.live.LiveEntity;
import com.finalproject.game.server.entity.live.player.Player;
import com.finalproject.game.server.items.Item;

import static com.finalproject.game.client.GameClient.*;

public class GameScreen implements Screen {
    private static final float UPDATE_INTERVAL = 0.1f;
    protected Texture layer0;
    protected Texture layer1;
    protected Texture layer2;
    private float timeSinceLastUpdate = 0;
    private BitmapFont font;
    private Music backgroundMusic;


    @Override
    public void show() {

        this.layer0 = Assets.layer0;
        this.layer1 = Assets.layer1;
        this.layer2 = Assets.layer2;

        backgroundMusic = Assets.musicAssets.get(SoundPlayer.MusicType.BACKGROUND_1);
        backgroundMusic.setLooping(true); //
        backgroundMusic.play();

        font = Assets.generateFont("font/roboto/Roboto-Medium.ttf", 12);
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
    public void render(float delta) {
        sendMouseUpdates();

        Gdx.gl.glClearColor(0.0f, 0, 0.0f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        RemoteClient userClient = gameInstanceSnapshot.remoteClient;
        if (userClient == null) return;

        Player player = userClient.getPlayer();

        Vector2 playerPos = player.getPos();
        Vector2 playerSize = player.getSize();
        float playerAngle = player.getAngle();

        float frameWidth = layer0.getWidth() / GameInstanceServer.PPM;
        float frameHeight = layer0.getHeight() / GameInstanceServer.PPM;

        batch.begin();
        batch.setProjectionMatrix(camera.combined);

        if (playerPos != null) {
            GameClient.cameraX = playerPos.x;
            GameClient.cameraY = playerPos.y;

            camera.position.set(cameraX, cameraY, 0);
            camera.update();
        }

        if (isDone) {

            if (isWinner) {
                batch.draw(Assets.win, 0, 0, CAMERA_VIEW_X, CAMERA_VIEW_Y);
            } else {
                batch.draw(Assets.lose, 0, 0, CAMERA_VIEW_X, CAMERA_VIEW_Y);
            }

            batch.end();
            return;
        }

        batch.draw(layer0, 0, 0, frameWidth, frameHeight);
        batch.draw(layer1, 0, 0, frameWidth, frameHeight);


        shapeRenderer.begin();
        shapeRenderer.setColor(Color.WHITE);
        shapeRenderer.set(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setProjectionMatrix(camera.combined);

        GameClient.gameInstanceSnapshot.players.forEach(p -> {
            Vector2 pos = p.getPos();
            Vector2 size = p.getSize();

            LiveEntity.FacingDirection facingDirection = p.getFacingDirection();
            TextureRegion playerFrameTexture = Assets.getAssetFramePlayer(p.getPlayerType(), p.getPlayerState(), facingDirection, p.getStateTime());
            batch.draw(playerFrameTexture, pos.x - size.x, pos.y - size.y, size.x * 2, size.y * 4);

            Item currentWeapon = p.getItemBox().getHeldItem();
            float playerAngleDeg = (float) Math.toDegrees(p.getAngle());

            if (currentWeapon != null) {

                TextureRegion weaponFrameTexture = new TextureRegion(Assets.getAssetFrameWeapon(currentWeapon.getItemType()));

                float renderingAngle = playerAngleDeg;

                if (90 < playerAngleDeg && playerAngleDeg < 270) {
                    weaponFrameTexture.flip(true, false);
                    renderingAngle -= 180;
                }

                float originX = size.x; // Half width of the texture when drawn
                float originY = size.y; // Half height of the texture when drawn


                batch.draw(weaponFrameTexture,
                        pos.x - size.x, pos.y - size.y, // Position where texture will be drawn
                        originX, originY, // Origin point for rotation (center of texture)
                        size.x * 2, size.y * 2, // Size of the texture when drawn
                        1f, 1f, // Scale, no scaling applied
                        renderingAngle); // Adjusted rendering angle

            }
        });

        GameClient.gameInstanceSnapshot.projectiles.forEach(projectile -> {
            Vector2 pos = projectile.getPos();
            Vector2 size = projectile.getSize();

            TextureRegion playerFrameTexture = Assets.getAssetFrameProjectile(projectile.getProjectileType(), player.getStateTime());
            batch.draw(playerFrameTexture, pos.x, pos.y, size.x * 2, size.y * 2);
        });

        if (playerPos != null) {
//            renderDimmingOverlay(playerPos, playerAngle);
        }

        screenShake.update(delta);
        batch.draw(layer2, 0, 0, frameWidth, frameHeight);
        drawHUD();

        shapeRenderer.end();
        batch.end();
    }


    private void renderDimmingOverlay(Vector2 playerPos, float playerAngle) {
        shapeRenderer.setColor(new Color(0, 0, 0, 0.3f)); // Semi-transparent black

        // Draw a rectangle covering the whole screen
        float screenWidth = camera.viewportWidth;
        float screenHeight = camera.viewportHeight;
        shapeRenderer.rect(playerPos.x - screenWidth / 2, playerPos.y - screenHeight / 2, screenWidth, screenHeight);

        // Set the color to fully transparent to carve out the vision cone
        shapeRenderer.setColor(new Color(255, 255, 255, 0.3f));

        // Define the vision cone parameters
        float visionDistance = 100f;
        float visionAngle = 45f; // Vision cone angle in degrees

        // Calculate the left and right boundaries of the vision cone
        float halfVisionAngle = visionAngle / 2;
        Vector2 direction = new Vector2(MathUtils.cos(playerAngle), MathUtils.sin(playerAngle));
        Vector2 leftBoundary = direction.cpy().rotateDeg(-halfVisionAngle).setLength(visionDistance).add(playerPos);
        Vector2 rightBoundary = direction.cpy().rotateDeg(halfVisionAngle).setLength(visionDistance).add(playerPos);

        // Draw the vision cone as a triangle or polygon
        shapeRenderer.triangle(playerPos.x, playerPos.y, leftBoundary.x, leftBoundary.y, rightBoundary.x, rightBoundary.y);

    }


    public void drawHUD() {
        // Set font color and scale
        font.setColor(Color.WHITE);
        font.getData().setScale(0.25f); // Adjust scale as needed

        if (gameInstanceSnapshot.remoteClient == null) return;
        Player p = gameInstanceSnapshot.remoteClient.getPlayer();

        if (p == null) return;

        // Example: Display player health
        font.setColor(Color.BLUE);
        font.draw(batch, "H: " + (int) p.getCurrentHealth(), cameraX - 35, cameraY + 35);

        // Example: Display player stamina
        font.setColor(Color.YELLOW);
        font.draw(batch, "S: " + (int) p.getCurrentStamina(), cameraX - 35, cameraY + 30);


        font.setColor(Color.RED);
        font.draw(batch, "K: " + gameInstanceSnapshot.remoteClient.getKillCount(), cameraX - 35, cameraY + 25);

        // Example: Display a message at the center of the screen
        String message = "Welcome to the Game!";
        GlyphLayout layout = new GlyphLayout(font, message);
        float messageX = (Gdx.graphics.getWidth() - layout.width) / 2;
        float messageY = (Gdx.graphics.getHeight() + layout.height) / 2;
        font.draw(batch, layout, messageX, messageY);
    }

    @Override
    public void resize(int width, int height) {
        camera.setToOrtho(false, CAMERA_VIEW_X, CAMERA_VIEW_Y);
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
    }

}
