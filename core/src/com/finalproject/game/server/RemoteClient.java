package com.finalproject.game.server;


import com.esotericsoftware.kryonet.Connection;
import com.finalproject.game.server.entity.live.player.Player;

import java.util.ArrayList;

public class RemoteClient {

    private final Connection connection;
    private int currentGameID;
    private String name;

    private ClientState clientState;
    private ArrayList<Integer> inputStates = new ArrayList<>();

    private Player player;

    public void update() {
        inputStates.forEach(keycode -> player.update(keycode));
        System.out.println(player);
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

    public static enum InputState {
        LEFT,
        RIGHT,
        UP,
        DOWN,
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