package com.finalproject.game.server;


import com.esotericsoftware.kryonet.Connection;
import com.finalproject.game.server.builder.item.WeaponBuilder;
import com.finalproject.game.server.entity.live.LiveEntity;
import com.finalproject.game.server.entity.live.player.Player;
import com.finalproject.game.server.items.weapons.Weapon;
import com.finalproject.game.server.packet.server.GameInstanceSnapshot;
import com.finalproject.game.server.packet.server.WinGame;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

public class RemoteClient {
    protected final int RESPAWN_TIMER = 3;
    protected transient final Connection connection;
    protected int killCount = 0;
    protected transient GameInstanceServer gameInstanceServer;
    protected int currentGameID;
    protected Player player;
    protected ClientState clientState = ClientState.INGAME;
    protected ClientGameState clientGameState = ClientGameState.ALIVE;
    protected float respawnTimer = 0;
    protected ArrayList<Integer> inputStates = new ArrayList<>();
    protected ArrayList<Integer> mouseButtonStates = new ArrayList<>();
    protected int isScrollingUp = 0;
    protected float mouseX = 0;
    protected float mouseY = 0;

    protected boolean isLoggedIn = false;
    protected String id;
    protected String username;


    public RemoteClient() {
        connection = null;
    }

    public RemoteClient(Connection c) {
        connection = c;
        clientState = ClientState.IDLE;
        currentGameID = -1;

        username = "Player " + c.getID();
    }

    public void loginUser(String id, String username) {
        this.id = id;
        this.username = username;
    }

    public int getKillCount() {
        return killCount;
    }

    public void setKillCount(int killCount) {
        this.killCount = killCount;
    }

    public Weapon getWeaponBasedOnKill() {
        Weapon w = null;
        try {
            Constructor<?> constructor = gameInstanceServer.weaponsOrder.get(this.killCount).getConstructor(WeaponBuilder.class);
            w = (Weapon) constructor.newInstance(new WeaponBuilder().setGameInstanceServer(gameInstanceServer).setRemoteClient(this).setCurrentWorld(gameInstanceServer.world));
        } catch (NoSuchMethodException | InstantiationException | IllegalAccessException |
                 InvocationTargetException e) {
            e.printStackTrace();
        }
        return w;
    }

    public void increaseKillCount() {
        this.killCount++;
        player.getItemBox().addItem(getWeaponBasedOnKill());

    }

    public GameInstanceServer getGameInstanceServer() {
        return gameInstanceServer;
    }

    public void setGameInstanceServer(GameInstanceServer gameInstanceServer) {
        this.gameInstanceServer = gameInstanceServer;
    }

    public int getIsScrollingUp() {
        return isScrollingUp;
    }

    public void setIsScrollingUp(int isScrollingUp) {
        this.isScrollingUp = isScrollingUp;
    }

    public ArrayList<Integer> getMouseButtonStates() {
        return mouseButtonStates;
    }

    public void sendDataToClient() {

        GameInstanceSnapshot gameInstanceSnapshot = new GameInstanceSnapshot(gameInstanceServer, this);
        connection.sendUDP(gameInstanceSnapshot);

    }

    public float getMouseY() {
        return mouseY;
    }

    public void setMouseY(float mouseY) {
        this.mouseY = mouseY;
    }

    public float getMouseX() {
        return mouseX;
    }

    public void setMouseX(float mouseX) {
        this.mouseX = mouseX;
    }

    public Connection getConnection() {
        return connection;
    }

    public int getCurrentGameID() {
        return currentGameID;
    }

    public void setCurrentGameID(int currentGameID) {
        this.currentGameID = currentGameID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public ClientState getClientState() {
        return clientState;
    }

    public void setClientState(ClientState clientState) {
        this.clientState = clientState;
    }

    public ArrayList<Integer> getInputStates() {
        return inputStates;
    }

    public void setInputStates(ArrayList<Integer> inputStates) {
        this.inputStates = inputStates;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public float getRespawnTimer() {
        return respawnTimer;
    }

    public void setRespawnTimer(float respawnTimer) {
        this.respawnTimer = respawnTimer;
    }

    public void update(float delta) {
        if (getIsScrollingUp() == -1) player.getItemBox().scrollItemUp();
        if (getIsScrollingUp() == 1) player.getItemBox().scrollItemDown();
        setIsScrollingUp(0);

        if (this.getClientGameState().equals(ClientGameState.DEAD)) {
            respawnTimer = RESPAWN_TIMER;
            this.setClientGameState(ClientGameState.RESPAWNING);
        }

        if (this.getClientGameState().equals(ClientGameState.RESPAWNING)) {
            respawnTimer -= delta;

            if (respawnTimer <= 0) {
                respawnTimer = 0;
                this.getGameInstanceServer().spawnPlayer(this);
                this.setClientGameState(ClientGameState.ALIVE);
            }
        }


        if (killCount == gameInstanceServer.WIN_KILL_COUNT) {
            gameInstanceServer.remoteClientsInServer.keySet().forEach(connection1 -> connection1.sendUDP(new WinGame(connection1.equals(this.connection))));
        }

        sendDataToClient();
    }

    public LiveEntity.FacingDirection getFacingDirection() {
        float angle = getPlayer().getAngle();

        if (angle >= -Math.PI / 4 && angle <= Math.PI / 4) {
            return LiveEntity.FacingDirection.RIGHT;
        } else if (angle > Math.PI / 4 && angle < 3 * Math.PI / 4) {
            return LiveEntity.FacingDirection.UP;
        } else if (angle >= 3 * Math.PI / 4 || angle <= -3 * Math.PI / 4) {
            return LiveEntity.FacingDirection.LEFT;
        } else {
            return LiveEntity.FacingDirection.DOWN;
        }
    }

    @Override
    public String toString() {
        return "RemoteClient{" +
                "connection=" + connection +
                ", username='" + username + '\'' +
                ", clientState=" + clientState +
                ", currentGameID=" + currentGameID +
                '}';
    }

    public ClientGameState getClientGameState() {
        return clientGameState;
    }

    public void setClientGameState(ClientGameState clientGameState) {
        this.clientGameState = clientGameState;
    }

    public enum ClientGameState {
        ALIVE,
        RESPAWNING,
        DEAD
    }


    public enum ClientState {
        IDLE,
        QUEUED,
        INGAME,
        READY
    }

}