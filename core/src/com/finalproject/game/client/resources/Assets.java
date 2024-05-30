package com.finalproject.game.client.resources;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.finalproject.game.client.sound.SoundPlayer;
import com.finalproject.game.server.entity.live.LiveEntity;
import com.finalproject.game.server.entity.live.player.Player;
import com.finalproject.game.server.entity.projectile.Projectile;
import com.finalproject.game.server.items.Item;

import java.util.HashMap;

public class Assets {

    public static HashMap<Player.PlayerType, HashMap<Player.PlayerState, HashMap<LiveEntity.FacingDirection, Animation<TextureRegion>>>> playerAssets = new HashMap<>();
    public static HashMap<Projectile.ProjectileType, Animation<TextureRegion>> projectileAssets = new HashMap<>();

    public static HashMap<Item.ItemType, Animation<TextureRegion>> weaponAssets = new HashMap<>();
    public static HashMap<Item.ItemType, Sound> gunSoundAssets = new HashMap<>();

    public static HashMap<SoundPlayer.SoundType, Sound> soundAssets = new HashMap<>();

    public static HashMap<SoundPlayer.MusicType, Music> musicAssets = new HashMap<>();

    public static Texture layer0, layer1, layer2, win, lose;

    // Load assets during initialization
    public static void load() {

        layer0 = new Texture("ooptilesets/map_layers/overworld_layer_0.png");
        layer1 = new Texture("ooptilesets/map_layers/overworld_layer_1.png");
        layer2 = new Texture("ooptilesets/map_layers/overworld_layer_2.png");

        win = new Texture("ooptilesets/win.png");
        lose = new Texture("ooptilesets/lose.png");

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
                if (playerState == Player.PlayerState.DEAD) {
                    playerStateMap.put(LiveEntity.FacingDirection.DOWN, loadSpritesheet("ooptilesets/characters/" + playerTypeToString.get(playerType) + playerStateToString.get(Player.PlayerState.DEAD) + ".png", 6));
                    playerStateMap.put(LiveEntity.FacingDirection.UP, loadSpritesheet("ooptilesets/characters/" + playerTypeToString.get(playerType) + playerStateToString.get(Player.PlayerState.DEAD) + ".png", 6));
                    playerStateMap.put(LiveEntity.FacingDirection.LEFT, loadSpritesheet("ooptilesets/characters/" + playerTypeToString.get(playerType) + playerStateToString.get(Player.PlayerState.DEAD) + ".png", 6));
                    playerStateMap.put(LiveEntity.FacingDirection.RIGHT, loadSpritesheet("ooptilesets/characters/" + playerTypeToString.get(playerType) + playerStateToString.get(Player.PlayerState.DEAD) + ".png", 6));
                } else {
                    for (LiveEntity.FacingDirection facingDirection : LiveEntity.FacingDirection.values()) {
                        playerStateMap.put(facingDirection, loadSpritesheet("ooptilesets/characters/" + playerTypeToString.get(playerType) + playerStateToString.get(playerState) + facingDirectionToString.get(facingDirection) + ".png", playerState != Player.PlayerState.HURT ? 6 : 3));
                    }
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

        weaponAssets.put(Item.ItemType.LASER_GUN, loadSpritesheet(weaponFilePath + "laser_gun.png", 1));
        weaponAssets.put(Item.ItemType.CROSSBOW, loadSpritesheet(weaponFilePath + "crossbow.png", 1));
        weaponAssets.put(Item.ItemType.MACHINE_GUN, loadSpritesheet(weaponFilePath + "machine_gun.png", 1));
        weaponAssets.put(Item.ItemType.SUBMACHINE_GUN, loadSpritesheet(weaponFilePath + "submachine_gun.png", 1));
        weaponAssets.put(Item.ItemType.AK69, loadSpritesheet(weaponFilePath + "ak69gun.png", 1));
        weaponAssets.put(Item.ItemType.SHOTGUN, loadSpritesheet(weaponFilePath + "shotgun.png", 1));

        // Initialize and load projectile assets if needed (example given below)
        String projectileFilePath = "ooptilesets/projectile/";
        projectileAssets.put(Projectile.ProjectileType.BULLET, loadSpritesheet(projectileFilePath + "bullet.png", 6));
        projectileAssets.put(Projectile.ProjectileType.ENERGY, loadSpritesheet(projectileFilePath + "energy.png", 6));
        projectileAssets.put(Projectile.ProjectileType.SHELL, loadSpritesheet(projectileFilePath + "shell.png", 6));
        projectileAssets.put(Projectile.ProjectileType.BOLT, loadSpritesheet(projectileFilePath + "bolt.png", 4));

        gunSoundAssets.put(Item.ItemType.LASER_GUN, loadSound("laserShoot"));
        gunSoundAssets.put(Item.ItemType.HANDGUN, loadSound("9mm"));
        gunSoundAssets.put(Item.ItemType.AK69, loadSound("heavy_machine_gun"));
        gunSoundAssets.put(Item.ItemType.MACHINE_GUN, loadSound("heavy_machine_gun"));
        gunSoundAssets.put(Item.ItemType.SUBMACHINE_GUN, loadSound("smg"));
        gunSoundAssets.put(Item.ItemType.CROSSBOW, loadSound("laserShoot"));
        gunSoundAssets.put(Item.ItemType.TWIN_PISTOLS, loadSound("gun_shot"));
        gunSoundAssets.put(Item.ItemType.DESERT_EAGLE, loadSound("gun_shot"));
        gunSoundAssets.put(Item.ItemType.ANACONDA, loadSound("gun_shot"));

        soundAssets.put(SoundPlayer.SoundType.PLAYER_HIT, loadSound("player_hit"));
        soundAssets.put(SoundPlayer.SoundType.RUN, loadSound("step"));
        soundAssets.put(SoundPlayer.SoundType.WALK, loadSound("player_hit"));
        soundAssets.put(SoundPlayer.SoundType.BULLET_HIT_WALL, loadSound("player_hit"));
        soundAssets.put(SoundPlayer.SoundType.ITEM_UP, loadSound("item_up"));
        soundAssets.put(SoundPlayer.SoundType.ITEM_DOWN, loadSound("item_down"));
        soundAssets.put(SoundPlayer.SoundType.DEATH, loadSound("death"));
        soundAssets.put(SoundPlayer.SoundType.SKILL, loadSound("skill"));

        musicAssets.put(SoundPlayer.MusicType.BACKGROUND_1, loadMusic("background1"));
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


    private static Sound loadSound(String fileName) {
        return Gdx.audio.newSound(Gdx.files.internal("sound/sfx/" + fileName + ".mp3"));
    }

    private static Music loadMusic(String fileName) {
        return Gdx.audio.newMusic(Gdx.files.internal("sound/music/" + fileName + ".mp3"));
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
