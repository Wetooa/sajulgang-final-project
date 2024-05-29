package com.finalproject.game.client.resources;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.finalproject.game.server.entity.live.LiveEntity;
import com.finalproject.game.server.entity.live.player.Player;
import com.finalproject.game.server.entity.projectile.Projectile;
import com.finalproject.game.server.items.Item;

import java.util.HashMap;

public class Assets {
    public static HashMap<Player.PlayerState, HashMap<LiveEntity.FacingDirection, Animation<TextureRegion>>> playerAssets = new HashMap<>();
    public static HashMap<Projectile.ProjectileType, Animation<TextureRegion>> projectileAssets = new HashMap<>();
    public static HashMap<Item.ItemType, Animation<TextureRegion>> weaponAssets = new HashMap<>();

    // Load assets during initialization
    public static void load() {
        HashMap<LiveEntity.FacingDirection, Animation<TextureRegion>> playerWalkingAssets = new HashMap<>();
        HashMap<LiveEntity.FacingDirection, Animation<TextureRegion>> playerIdleAssets = new HashMap<>();
        HashMap<LiveEntity.FacingDirection, Animation<TextureRegion>> playerHurtAssets = new HashMap<>();
        HashMap<LiveEntity.FacingDirection, Animation<TextureRegion>> playerDeadAssets = new HashMap<>();

        String filePath = "OOP/CHARACTERS/";

        String filePathWalk = filePath + "walk/";
        String filePathIdle = filePath + "idle/";
        String filePathHurt = filePath + "hurt/";
//        String filePathDead = filePath + "hurt/";

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

//        playerDeadAssets.put(LiveEntity.FacingDirection.UP, loadSpritesheet(filePathDead + "deadback.png", 6));
//        playerDeadAssets.put(LiveEntity.FacingDirection.DOWN, loadSpritesheet(filePathDead + "deadfront.png", 6));
//        playerDeadAssets.put(LiveEntity.FacingDirection.LEFT, loadSpritesheet(filePathDead + "deadleft.png", 6));
//        playerDeadAssets.put(LiveEntity.FacingDirection.RIGHT, loadSpritesheet(filePathDead + "deadright.png", 6));

        playerAssets.put(Player.PlayerState.WALK, playerWalkingAssets);
        playerAssets.put(Player.PlayerState.IDLE, playerIdleAssets);
        playerAssets.put(Player.PlayerState.HURT, playerHurtAssets);
//        playerAssets.put(Player.PlayerState.DEAD, playerDeadAssets);

        String weaponFilePath = "OOP/ADRIAN NAA DIRI TANAN/ooptilesets/weapons/";

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

        String projectileFilePath = "OOP/ADRIAN NAA DIRI TANAN/PROJECTILE/";

        projectileAssets.put(Projectile.ProjectileType.BULLET, loadSpritesheet(projectileFilePath + "bullet1.png", 6));
//        projectileAssets.put(Projectile.ProjectileType.ENERGY, loadSpritesheet(projectileFilePath + "enery1.png", 6));
//        projectileAssets.put(Projectile.ProjectileType.SHELL, loadSpritesheet(projectileFilePath + "bullet1.png", 6));
//        projectileAssets.put(Projectile.ProjectileType.BOLT, loadSpritesheet(projectileFilePath + "bullet1.png", 6));

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

    public static TextureRegion getAssetFrameWeapon(Item.ItemType itemType) {
        return weaponAssets.get(itemType).getKeyFrame(0, true);
    }

    public static TextureRegion getAssetFrameProjectile(Projectile.ProjectileType projectileType, float stateTime) {
        return projectileAssets.get(projectileType).getKeyFrame(stateTime, true);
    }

    public static TextureRegion getAssetFramePlayer(Player.PlayerState playerState, LiveEntity.FacingDirection facingDirection, float stateTime) {
        return playerAssets.get(playerState).get(facingDirection).getKeyFrame(stateTime, true);
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
