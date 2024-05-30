package com.finalproject.game.server.entity.live.player;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;
import com.finalproject.game.client.sound.SoundPlayer;
import com.finalproject.game.server.RemoteClient;
import com.finalproject.game.server.builder.entity.LiveEntityBuilder;
import com.finalproject.game.server.builder.item.WeaponBuilder;
import com.finalproject.game.server.entity.live.LiveEntity;
import com.finalproject.game.server.items.Item;
import com.finalproject.game.server.items.ItemBox;
import com.finalproject.game.server.items.weapons.melee.Katana;
import com.finalproject.game.server.items.weapons.range.secondary.HandGun;

public abstract class Player extends LiveEntity {

    protected static final float MOVING_THRESHOLD = 0.1f;
    private static final float HURT_TIMER = 0.5f;
    protected final float SPECIAL_SKILL_COOLDOWN_TIMER;
    protected final float SPECIAL_SKILL_STAMINA_COST;
    protected boolean isRunning = false;
    protected float runningMultiplier;
    protected float currentStamina;
    protected float maxStamina;
    protected PlayerState playerState = PlayerState.IDLE;
    protected ItemBox itemBox;
    protected PlayerType playerType = PlayerType.FRIA;
    protected float specialSkillCooldownTimer = 0;
    private float hurtTimer = 0;

    public Player() {
        this(new LiveEntityBuilder());
    }

    public Player(LiveEntityBuilder builder) {
        super(builder);

        this.runningMultiplier = builder.getRunningMultiplier();
        this.currentStamina = builder.getCurrentStamina();
        this.maxStamina = builder.getMaxStamina();
        this.playerType = builder.getPlayerType();
        this.SPECIAL_SKILL_COOLDOWN_TIMER = builder.getSpecialSkillTimer();
        this.SPECIAL_SKILL_STAMINA_COST = builder.getSpecialSkillStaminaCost();

        this.itemBox = new ItemBox(this.gameInstanceServer);

        Item startingGun = new HandGun((WeaponBuilder) new WeaponBuilder().setGameInstanceServer(gameInstanceServer).setRemoteClient(remoteClient).setCurrentWorld(currentWorld));
        Item meleeWeapon = new Katana((WeaponBuilder) new WeaponBuilder().setGameInstanceServer(gameInstanceServer).setRemoteClient(remoteClient).setCurrentWorld(currentWorld));
//        Item startingGun2 = new TwinPistols((WeaponBuilder) new WeaponBuilder().setGameInstanceServer(gameInstanceServer).setRemoteClient(remoteClient).setCurrentWorld(currentWorld));

        itemBox.addItem(startingGun);
        itemBox.addItem(meleeWeapon);
    }

    public float getSPECIAL_SKILL_STAMINA_COST() {
        return SPECIAL_SKILL_STAMINA_COST;
    }

    public float getSpecialSkillCooldownTimer() {
        return specialSkillCooldownTimer;
    }

    public void setSpecialSkillCooldownTimer(float specialSkillCooldownTimer) {
        this.specialSkillCooldownTimer = specialSkillCooldownTimer;
    }

    public float getCurrentStamina() {
        return currentStamina;
    }

    public void setCurrentStamina(int currentStamina) {
        this.currentStamina = currentStamina;
    }

    public float getMaxStamina() {
        return maxStamina;
    }

    public void setMaxStamina(int maxStamina) {
        this.maxStamina = maxStamina;
    }

    public ItemBox getItemBox() {
        return itemBox;
    }

    public void setItemBox(ItemBox itemBox) {
        this.itemBox = itemBox;
    }

    public boolean isRunning() {
        return isRunning;
    }

    public void setRunning(boolean running) {
        isRunning = running;
    }

    public boolean isMoving() {
        return this.getBoxBody().getLinearVelocity().len() > MOVING_THRESHOLD;
    }

    @Override
    public void update(float delta) {
        super.update(delta);

        if (!this.getRemoteClient().getClientGameState().equals(RemoteClient.ClientGameState.ALIVE)) return;

        this.setRunning(remoteClient.getInputStates().contains(Input.Keys.SHIFT_LEFT));
        if (isRunning) {
            currentStamina = Math.max(0, currentStamina - delta * 100);
            gameInstanceServer.playSound(SoundPlayer.SoundType.RUN);
        } else currentStamina = Math.min(maxStamina, currentStamina + delta * 150);

        this.specialSkillCooldownTimer -= delta;

        if (this.currentHealth <= 0) {
            this.getRemoteClient().setClientGameState(RemoteClient.ClientGameState.DEAD);
            this.setPlayerState(PlayerState.DEAD);
        } else if (hurtTimer > 0) {
            hurtTimer -= delta;
            this.setPlayerState(PlayerState.HURT);
        } else if (isMoving()) {
            this.setPlayerState(PlayerState.WALK);
        } else {
            this.setPlayerState(PlayerState.IDLE);
        }

        if (remoteClient.getInputStates().contains(Input.Keys.SPACE)) useSpecialSkill();
        remoteClient.getInputStates().forEach(keycode -> this.updateMovement(delta, keycode));
        remoteClient.getMouseButtonStates().forEach(button -> this.updateMouseAction(delta, button));
    }

    public void useSpecialSkill() {
        if (specialSkillCooldownTimer > 0 || currentStamina < SPECIAL_SKILL_STAMINA_COST) return;

        specialSkillCooldownTimer = SPECIAL_SKILL_COOLDOWN_TIMER;
        currentStamina -= SPECIAL_SKILL_STAMINA_COST;
        gameInstanceServer.playSound(SoundPlayer.SoundType.SKILL);
        castSpecialSkill();
    }

    public abstract void castSpecialSkill();

    public void updateMouseAction(float delta, int keycode) {
        if (keycode == Input.Buttons.LEFT) {
            Item currentItem = itemBox.getHeldItem();
            currentItem.activate(delta);
        }
    }

    public void updateMovement(float delta, int keycode) {
        float currentSpeed = getMaxSpeed() * (isRunning && currentStamina > 0 ? runningMultiplier : 1);

        if (keycode == Input.Keys.D) {
            boxBody.applyLinearImpulse(new Vector2(currentSpeed, 0), boxBody.getWorldCenter(), true);
        }
        if (keycode == Input.Keys.A) {
            boxBody.applyLinearImpulse(new Vector2(-currentSpeed, 0), boxBody.getWorldCenter(), true);
        }
        if (keycode == Input.Keys.W) {
            boxBody.applyLinearImpulse(new Vector2(0, currentSpeed), boxBody.getWorldCenter(), true);
        }
        if (keycode == Input.Keys.S) {
            boxBody.applyLinearImpulse(new Vector2(0, -currentSpeed), boxBody.getWorldCenter(), true);
        }
    }

    @Override
    public void takeDamage(float damage) {
        super.takeDamage(damage);

        setPlayerState(PlayerState.HURT);
        hurtTimer = HURT_TIMER;
    }

    public void shoot(float delta) {
        Item heldItem = itemBox.getHeldItem();
        heldItem.activate(delta);
    }

    public PlayerState getPlayerState() {
        return playerState;
    }

    public void setPlayerState(PlayerState playerState) {
        this.playerState = playerState;
    }

    public float getSPECIAL_SKILL_COOLDOWN_TIMER() {
        return SPECIAL_SKILL_COOLDOWN_TIMER;
    }

    public float getRunningMultiplier() {
        return runningMultiplier;
    }

    public void setRunningMultiplier(float runningMultiplier) {
        this.runningMultiplier = runningMultiplier;
    }

    public PlayerType getPlayerType() {
        return playerType;
    }

    public void setPlayerType(PlayerType playerType) {
        this.playerType = playerType;
    }

    public enum PlayerType {
        FRIA, JOSHUA, STEPHEN, ADRIAN
    }

    public enum PlayerState {
        WALK, IDLE, HURT, DEAD
    }
}