package com.finalproject.game.server;


import com.esotericsoftware.kryonet.Connection;
import com.finalproject.game.server.entity.live.player.Player;
import com.finalproject.game.server.packet.server.GameInstanceSnapshot;

import java.util.ArrayList;

public class RemoteClient {

    protected transient final Connection connection;

    protected int currentGameID;
    protected String name;

    protected ClientState clientState;
    protected ClientGameState clientGameState;

    protected int respawnTimer = 0;

    protected ArrayList<Integer> inputStates = new ArrayList<>();
    protected ArrayList<Integer> mouseButtonStates = new ArrayList<>();
    protected int isScrollingUp = 0;
    protected float mouseX = 0;
    protected float mouseY = 0;

    protected transient GameInstanceServer gameInstanceServer;
    protected transient Player player;


    public RemoteClient(Connection c) {
        connection = c;
        clientState = ClientState.IDLE;
        currentGameID = -1;

        name = "Player " + c.getID();
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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


    public void update() {
        if (getIsScrollingUp() == -1) player.getItemBox().scrollItemUp();
        if (getIsScrollingUp() == 1) player.getItemBox().scrollItemDown();
        setIsScrollingUp(0);

        sendDataToClient();
    }

    @Override
    public String toString() {
        return "RemoteClient{" +
                "connection=" + connection +
                ", name='" + name + '\'' +
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