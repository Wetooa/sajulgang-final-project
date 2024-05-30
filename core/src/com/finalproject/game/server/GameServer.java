package com.finalproject.game.server;

import com.badlogic.gdx.backends.headless.HeadlessApplication;
import com.badlogic.gdx.backends.headless.HeadlessApplicationConfiguration;
import com.badlogic.gdx.math.Vector2;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Server;
import com.finalproject.game.client.packet.client.*;
import com.finalproject.game.client.packet.client.jdbc.LoginPacket;
import com.finalproject.game.client.packet.client.jdbc.RegisterPacket;
import com.finalproject.game.server.JDBC.InsertData;
import com.finalproject.game.server.JDBC.MySqlConnection;
import com.finalproject.game.server.JDBC.ReadData;
import com.finalproject.game.server.packet.server.jdbc.LoginSuccessPacket;
import com.finalproject.game.server.packet.server.jdbc.RegisterSuccessPacket;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicLong;

public class GameServer {


    private final static int BUFFER_SIZE = 1024 * 1024;
    private static final float TARGET_DELTA = 0.016f; // Assuming target delta is 16ms
    private static final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
    private static final ExecutorService gameUpdatePool = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
    public static ArrayList<Vector2> spawnPoints = new ArrayList<>();
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

        GameInstanceServer.load();
        loadSpawnPoints();
        MySqlConnection.getConnection();

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

                                   if (object instanceof LoginPacket) {
                                       LoginPacket login = (LoginPacket) object;
                                       String id = ReadData.login(login);
                                       remoteClient.loginUser(id, login.username);
                                       activeGames.get(0).addRemoteClient(connection, remoteClient);
                                       connection.sendTCP(new LoginSuccessPacket());
                                   }

                                   if (object instanceof RegisterPacket) {
                                       RegisterPacket registerPacket = (RegisterPacket) object;
                                       InsertData.register(registerPacket);
                                       connection.sendTCP(new RegisterSuccessPacket());
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

    public void loadSpawnPoints() {
        spawnPoints.add(new Vector2(6.2f, 9.2f));
        spawnPoints.add(new Vector2(195.8f, 8.4f));
        spawnPoints.add(new Vector2(47.5f, 42.1f));
        spawnPoints.add(new Vector2(187.4f, 58.2f));
        spawnPoints.add(new Vector2(32.4f, 180.4f));
        spawnPoints.add(new Vector2(119.5f, 158.8f));
        spawnPoints.add(new Vector2(189.5f, 193.0f));
        spawnPoints.add(new Vector2(47.8f, 101.3f));
        spawnPoints.add(new Vector2(73.7f, 19.1f));
        spawnPoints.add(new Vector2(155.2f, 136.7f));
    }

    public void createNewGameInstance() {
        int gameId = activeGames.size();

        GameInstanceServer ng = new GameInstanceServer(gameId, GameInstanceServer.GameWorld.OVERWORLD);

        HeadlessApplicationConfiguration config = new HeadlessApplicationConfiguration();
        new HeadlessApplication(ng, config);

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
//            activeGames.get(0).addRemoteClient(connection, newClient);
        } else System.out.println("Client with connection ID: " + connection.getID() + " is already connected!");
    }
}
