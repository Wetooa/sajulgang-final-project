package com.finalproject.game.server;

import com.badlogic.gdx.backends.headless.HeadlessApplication;
import com.badlogic.gdx.backends.headless.HeadlessApplicationConfiguration;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Server;
import com.finalproject.game.client.packet.client.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicLong;

public class GameServer {

    private final static int BUFFER_SIZE = 1024 * 1024;
    private static final float TARGET_DELTA = 0.016f; // Assuming target delta is 16ms
    private static final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
    private static final ExecutorService gameUpdatePool = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
    private final Map<Connection, RemoteClient> remoteClients = new HashMap<>();
    private final ConcurrentHashMap<Integer, GameInstanceServer> activeGames = new ConcurrentHashMap<>();

//    private double matchMakeTimer = 0.0;

    public GameServer() throws IOException {
        Server server = new Server(BUFFER_SIZE, BUFFER_SIZE);
        server.start();
        server.bind(54555, 54777);

        Kryo kryo = server.getKryo();
        kryo.setRegistrationRequired(false);
        kryo.setReferences(true);

        createNewGameInstance();

        server.addListener(new Listener() {
                               @Override
                               public void received(Connection connection, Object object) {

                                   RemoteClient remoteClient = remoteClients.get(connection);

                                   if (object instanceof KeyPressed) {
                                       remoteClient.getInputStates().add(((KeyPressed) object).keycode);
                                   }

                                   if (object instanceof KeyReleased) {
                                       remoteClient.getInputStates().remove((Object) ((KeyReleased) object).keycode);
                                   }

                                   if (object instanceof MousePressed) {
                                       MousePressed mouse = (MousePressed) object;
                                       remoteClient.getMouseButtonStates().add(mouse.button);
                                   }

                                   if (object instanceof MouseReleased) {
                                       MouseReleased mouse = (MouseReleased) object;
                                       remoteClient.getMouseButtonStates().remove((Object) (mouse.button));
                                   }

                                   if (object instanceof MouseMove) {
                                       MouseMove mouse = (MouseMove) object;
                                       remoteClient.setMouseX(mouse.mouseX);
                                       remoteClient.setMouseY(mouse.mouseY);
                                   }

                                   if (object instanceof MouseScroll) {
                                       MouseScroll mouse = (MouseScroll) object;
                                       remoteClient.setIsScrollingUp(mouse.scrolledUp ? 1 : -1);
                                   }

                               }

                               @Override
                               public void disconnected(Connection connection) {
//                System.out.println("Client disconnected with ID: " + connection.getID());
//                removeClient(connection);
                               }

                               @Override
                               public void connected(Connection connection) {
                                   addClient(connection);
                                   System.out.println("Client connected with ID: " + connection.getID());
//                queueClientMatchmaking(connection);
                               }
                           }

        );


        AtomicLong lastUpdateTime = new AtomicLong(System.nanoTime());
        long sleep = (long) (1000 * TARGET_DELTA);

        scheduler.scheduleAtFixedRate(() -> {
            long now = System.nanoTime();
            float delta = (now - lastUpdateTime.getAndSet(now)) / 1_000_000_000.0f;
            activeGames.values().parallelStream().forEach(gameInstance -> gameUpdatePool.submit(() -> gameInstance.update(delta)));
        }, 0L, sleep, TimeUnit.MILLISECONDS);


    }

    public static void main(String[] args) {
        try {
            new GameServer();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void createNewGameInstance() {
        int gameId = activeGames.size();

        GameInstanceServer ng = new GameInstanceServer(gameId, GameInstanceServer.GameWorld.OVERWORLD);

        HeadlessApplicationConfiguration config = new HeadlessApplicationConfiguration();
        new HeadlessApplication(ng, config);

//        Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
//        new Lwjgl3Application(ng, config);

        activeGames.put(gameId, ng);
    }


//    public void removeClient(Connection connection) {
//        if (remoteClients.containsKey(connection)) {
//
//            RemoteClient clientToRemove = remoteClients.get(connection);
//
//            if (clientToRemove.clientState() == RemoteClient.ClientState.INGAME) {
//                //Check if the client being removed is in a game
//                if (activeGames.containsKey(clientToRemove.getGameID()) && activeGames.get(clientToRemove.getGameID()).containsClient(clientToRemove)) {
//
//                    //If the opponent is still connected, then send them to the main menu
//                    RemoteClient opponent = activeGames.get(clientToRemove.getGameID()).getOpponent(clientToRemove);
//                    if (opponent.getConnection().isConnected()) {
//                        opponent.getConnection().sendTCP(new GameEndDisconnect());
//                        opponent.setClientState(RemoteClient.ClientState.IDLE);
//                        opponent.setGameID(-1);
//                    }
//
//                    closeGame(clientToRemove.getGameID());
//                }
//            }
//
//            remoteClients.remove(connection);
//        } else {
//            System.out.println("Connection with ID: " + connection.getID() + " is not in client list!");
//        }
//    }

    public void addClient(Connection connection) {
        if (!remoteClients.containsKey(connection)) {
            RemoteClient newClient = new RemoteClient(connection);
            remoteClients.put(connection, newClient);
            activeGames.get(0).addRemoteClient(connection, newClient);
        } else System.out.println("Client with connection ID: " + connection.getID() + " is already connected!");
    }
}
