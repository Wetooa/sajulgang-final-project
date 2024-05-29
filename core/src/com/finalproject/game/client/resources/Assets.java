package com.finalproject.game.client.resources;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.finalproject.game.client.sound.Sound;
import com.finalproject.game.server.entity.live.LiveEntity;
import com.finalproject.game.server.entity.live.player.Player;
import com.finalproject.game.server.entity.projectile.Projectile;
import com.finalproject.game.server.items.Item;

import java.util.HashMap;

public class Assets {

    public static HashMap<Player.PlayerType, HashMap<Player.PlayerState, HashMap<LiveEntity.FacingDirection, Animation<TextureRegion>>>> playerAssets = new HashMap<>();
    public static HashMap<Projectile.ProjectileType, Animation<TextureRegion>> projectileAssets = new HashMap<>();
    public static HashMap<Item.ItemType, Animation<TextureRegion>> weaponAssets = new HashMap<>();
    public static HashMap<Sound.SoundType, Object> soundAssets = new HashMap();

    // Load assets during initialization
    public static void load() {
        HashMap<Player.PlayerType, String> playerTypeToString = new HashMap<>();
        playerTypeToString.put(Player.PlayerType.FRIA, "1");
        playerTypeToString.put(Player.PlayerType.ADRIAN, "2");
        playerTypeToString.put(Player.PlayerType.STEPHEN, "3");
        playerTypeToString.put(Player.PlayerType.JOSHUA, "4");

        HashMap<Player.PlayerState, String> playerStateToString = new HashMap<>();
        playerStateToString.put(Player.PlayerState.WALK, "walk");
        playerStateToString.put(Player.PlayerState.DEAD, "dead");
        playerStateToString.put(Player.PlayerState.IDLE, "idle");
        playerStateToString.put(Player.PlayerState.HURT, "hurt");

        HashMap<LiveEntity.FacingDirection, String> facingDirectionToString = new HashMap<>();
        facingDirectionToString.put(LiveEntity.FacingDirection.UP, "back");
        facingDirectionToString.put(LiveEntity.FacingDirection.DOWN, "front");
        facingDirectionToString.put(LiveEntity.FacingDirection.LEFT, "left");
        facingDirectionToString.put(LiveEntity.FacingDirection.RIGHT, "right");

        for (Player.PlayerType playerType : Player.PlayerType.values()) {
            HashMap<Player.PlayerState, HashMap<LiveEntity.FacingDirection, Animation<TextureRegion>>> playerTypeMap = new HashMap<>();
            for (Player.PlayerState playerState : Player.PlayerState.values()) {
                HashMap<LiveEntity.FacingDirection, Animation<TextureRegion>> playerStateMap = new HashMap<>();
                for (LiveEntity.FacingDirection facingDirection : LiveEntity.FacingDirection.values()) {
                    playerStateMap.put(facingDirection, loadSpritesheet("ooptilesets/characters/" + playerTypeToString.get(playerType) + playerStateToString.get(playerState) + facingDirectionToString.get(facingDirection) + ".png", 6));
                }
                playerTypeMap.put(playerState, playerStateMap);
            }
            playerAssets.put(playerType, playerTypeMap);
        }


        // Initialize and load weapon assets
        String weaponFilePath = "ooptilesets/weapons/";
        weaponAssets.put(Item.ItemType.HANDGUN, loadSpritesheet(weaponFilePath + "handgun.png", 1));
        weaponAssets.put(Item.ItemType.ANACONDA, loadSpritesheet(weaponFilePath + "anaconda.png", 1));
        weaponAssets.put(Item.ItemType.DESERT_EAGLE, loadSpritesheet(weaponFilePath + "desert_eagle.png", 1));
        weaponAssets.put(Item.ItemType.TWIN_PISTOLS, loadSpritesheet(weaponFilePath + "twin_pistols.png", 1));

        weaponAssets.put(Item.ItemType.LASER_GUN, loadSpritesheet(weaponFilePath + "lasergun.png", 1));
        weaponAssets.put(Item.ItemType.CROSSBOW, loadSpritesheet(weaponFilePath + "crossbow.png", 1));
        weaponAssets.put(Item.ItemType.MACHINE_GUN, loadSpritesheet(weaponFilePath + "machinegun.png", 1));
        weaponAssets.put(Item.ItemType.SUBMACHINE_GUN, loadSpritesheet(weaponFilePath + "submachinegun.png", 1));
        weaponAssets.put(Item.ItemType.AK69, loadSpritesheet(weaponFilePath + "ak69gun.png", 1));
        weaponAssets.put(Item.ItemType.SHOTGUN, loadSpritesheet(weaponFilePath + "shotgun.png", 1));

        // Initialize and load projectile assets if needed (example given below)
        String projectileFilePath = "ooptilesets/projectile/";
        projectileAssets.put(Projectile.ProjectileType.BULLET, loadSpritesheet(projectileFilePath + "bullet.png", 6));
        projectileAssets.put(Projectile.ProjectileType.ENERGY, loadSpritesheet(projectileFilePath + "energy.png", 6));
        projectileAssets.put(Projectile.ProjectileType.SHELL, loadSpritesheet(projectileFilePath + "shell.png", 6));
        projectileAssets.put(Projectile.ProjectileType.BOLT, loadSpritesheet(projectileFilePath + "bolt.png", 4));


        dispose();
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
        for (HashMap<Player.PlayerState, HashMap<LiveEntity.FacingDirection, Animation<TextureRegion>>> playerStateHashMap : playerAssets.values()) {
            for (HashMap<LiveEntity.FacingDirection, Animation<TextureRegion>> facingDirectionAnimationHashMap : playerStateHashMap.values()) {
                for (Animation<TextureRegion> textureAnimation : facingDirectionAnimationHashMap.values()) {
                    for (TextureRegion frame : textureAnimation.getKeyFrames()) {
                        frame.getTexture().dispose();
                    }
                }
            }
        }
    }

    public static TextureRegion getAssetFrameWeapon(Item.ItemType itemType) {
        return weaponAssets.get(itemType).getKeyFrame(0, true);
    }

    public static TextureRegion getAssetFrameProjectile(Projectile.ProjectileType projectileType, float stateTime) {
        return projectileAssets.get(projectileType).getKeyFrame(stateTime, true);
    }

    public static TextureRegion getAssetFramePlayer(Player.PlayerType playerType, Player.PlayerState playerState, LiveEntity.FacingDirection facingDirection, float stateTime) {
        return playerAssets.get(playerType).get(playerState).get(facingDirection).getKeyFrame(stateTime, true);
    }


    public static BitmapFont generateFont(String filePath, int size) {
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal(filePath));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();

        parameter.size = size;

        BitmapFont font = generator.generateFont(parameter); // font size 12 pixels
        generator.dispose(); // don't forget to dispose to avoid memory leaks!

        return font;
    }

}
