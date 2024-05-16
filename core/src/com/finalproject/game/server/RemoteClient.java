package com.finalproject.game.server;


import com.badlogic.gdx.Input;
import com.esotericsoftware.kryonet.Connection;
import com.finalproject.game.server.entity.live.player.Player;

import java.util.ArrayList;

public class RemoteClient {

    protected final Connection connection;
    protected int currentGameID;
    protected String name;
    protected GameInstanceServer gameInstanceServer;

    protected ClientState clientState;

    protected ArrayList<Integer> inputStates = new ArrayList<>();
    protected ArrayList<Integer> mouseButtonStates = new ArrayList<>();

    public ArrayList<Integer> getMouseButtonStates() {
        return mouseButtonStates;
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

    protected float mouseX = 0;
    protected float mouseY = 0;

    protected Player player;

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

    public static enum ClientState {
        NAMELESS,
        IDLE,
        QUEUED,
        INGAME,
        READY
    }

    public RemoteClient(Connection c) {
        connection = c;
        clientState = ClientState.NAMELESS;
        currentGameID = -1;

        name = "Player " + c.getID();
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

}