package com.finalproject.game.client.resources;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.finalproject.game.server.entity.live.LiveEntity;
import com.finalproject.game.server.entity.live.player.Player;

import java.util.HashMap;

public class Assets {
    public static HashMap<Player.PlayerState, HashMap<LiveEntity.FacingDirection, Animation<TextureRegion>>> playerAssets = new HashMap<>();

    // Load assets during initialization
    public static void load() {
        HashMap<LiveEntity.FacingDirection, Animation<TextureRegion>> playerWalkingAssets = new HashMap<>();
        HashMap<LiveEntity.FacingDirection, Animation<TextureRegion>> playerIdleAssets = new HashMap<>();
        HashMap<LiveEntity.FacingDirection, Animation<TextureRegion>> playerHurtAssets = new HashMap<>();

        String filePath = "OOP/CHARACTERS/";

        String filePathWalk = filePath + "walk/";
        String filePathIdle = filePath + "idle/";
        String filePathHurt = filePath + "hurt/";

        playerWalkingAssets.put(LiveEntity.FacingDirection.UP, loadSpritesheet(filePathWalk + "walkback.png", 6));
        playerWalkingAssets.put(LiveEntity.FacingDirection.DOWN, loadSpritesheet(filePathWalk + "walkfront.png", 6));
        playerWalkingAssets.put(LiveEntity.FacingDirection.LEFT, loadSpritesheet(filePathWalk + "walkleft.png", 6));
        playerWalkingAssets.put(LiveEntity.FacingDirection.RIGHT, loadSpritesheet(filePathWalk + "walkright.png", 6));

        playerIdleAssets.put(LiveEntity.FacingDirection.UP, loadSpritesheet(filePathIdle + "idleback.png", 6));
        playerIdleAssets.put(LiveEntity.FacingDirection.DOWN, loadSpritesheet(filePathIdle + "idlefront.png", 6));
        playerIdleAssets.put(LiveEntity.FacingDirection.LEFT, loadSpritesheet(filePathIdle + "idleleft.png", 6));
        playerIdleAssets.put(LiveEntity.FacingDirection.RIGHT, loadSpritesheet(filePathIdle + "idleright.png", 6));

        playerHurtAssets.put(LiveEntity.FacingDirection.UP, loadSpritesheet(filePathHurt + "hurtback.png", 6));
        playerHurtAssets.put(LiveEntity.FacingDirection.DOWN, loadSpritesheet(filePathHurt + "hurtfront.png", 6));
        playerHurtAssets.put(LiveEntity.FacingDirection.LEFT, loadSpritesheet(filePathHurt + "hurtleft.png", 6));
        playerHurtAssets.put(LiveEntity.FacingDirection.RIGHT, loadSpritesheet(filePathHurt + "hurtright.png", 6));

        playerAssets.put(Player.PlayerState.WALK, playerWalkingAssets);
        playerAssets.put(Player.PlayerState.IDLE, playerIdleAssets);
        playerAssets.put(Player.PlayerState.HURT, playerHurtAssets);

        // Initialize and load projectile assets if needed (example given below)
    }

    private static Animation<TextureRegion> loadSpritesheet(String fileName, int frameCount) {
        Texture spritesheet = new Texture(fileName);
        TextureRegion[] frames = new TextureRegion[frameCount];

        // Calculate the width of each frame based on the spritesheet size and frame count
        int frameWidth = spritesheet.getWidth() / frameCount;

        for (int i = 0; i < frameCount; i++) {
            frames[i] = new TextureRegion(spritesheet, i * frameWidth, 0, frameWidth, spritesheet.getHeight());
        }

        return new Animation<>(0.1f, frames);
    }

    public static void dispose() {
        for (HashMap<LiveEntity.FacingDirection, Animation<TextureRegion>> assetMap : playerAssets.values()) {
            for (Animation<TextureRegion> animation : assetMap.values()) {
                for (TextureRegion frame : animation.getKeyFrames()) {
                    frame.getTexture().dispose();
                }
            }
        }
    }

    public static TextureRegion getAssetFramePlayer(Player.PlayerState playerState, LiveEntity.FacingDirection facingDirection, float stateTime) {
        return playerAssets.get(playerState).get(facingDirection).getKeyFrame(stateTime, true);
    }

}
