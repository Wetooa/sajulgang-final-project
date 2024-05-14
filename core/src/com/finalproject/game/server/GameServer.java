package com.finalproject.game.server;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Server;
import com.finalproject.game.client.packet.client.KeyPressed;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicLong;

public class GameServer {

    private final static int BUFFER_SIZE = 1024 * 1024;

    private final Map<Connection, RemoteClient> remoteClients = new HashMap<>();
    private final ConcurrentHashMap<Integer, GameInstanceServer> activeGames = new ConcurrentHashMap<>();

    private final Server server;

    private static final float TARGET_DELTA = 0.016f; // Assuming target delta is 16ms
    private static final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
    private static final ExecutorService gameUpdatePool = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
    private double mmTimer = 0.0;

    public GameServer() throws IOException {

        server = new Server(BUFFER_SIZE, BUFFER_SIZE);
        server.start();
        server.bind(54555, 54777);

        Kryo kryo = server.getKryo();
        kryo.setRegistrationRequired(false);

        GameInstanceServer ng = new GameInstanceServer(1, new HashMap<>());
        activeGames.put(1, ng);

        server.addListener(new Listener() {
                               @Override
                               public void received(Connection connection, Object object) {
                                   System.out.println("[Server]: " + connection + " sent this object " + object);

                                   RemoteClient remoteClient = remoteClients.get(connection);
                                   GameInstanceServer gameInstance = activeGames.get(remoteClient.getGameID());

                                   if (object instanceof KeyPressed) {
                                       int key = ((KeyPressed) object).key;
                                       gameInstance.players.get(remoteClient).move(key);
//                        remoteClient.inputState = RemoteClient.InputState.LEFT;
                                   }

//                if (object instanceof KeyReleased) {
//                    int key = ((KeyReleased) object).key;
//
//                    if (key == -1 && remoteClient.inputState == RemoteClient.InputState.LEFT)
//                        remoteClient.inputState = RemoteClient.InputState.IDLE;
//                    if (key == 1 && remoteClient.inputState == RemoteClient.InputState.RIGHT)
//                        remoteClient.inputState = RemoteClient.InputState.IDLE;
//                }

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


        long sleep = (long) (1000 * TARGET_DELTA);
        AtomicLong lastUpdateTime = new AtomicLong(System.nanoTime());
        scheduler.scheduleAtFixedRate(() -> {
            long now = System.nanoTime();
            long frameTime = now - lastUpdateTime.getAndSet(now);
            double delta = frameTime / 1_000_000_000.0f;

            // Update game logic timers
//            mmTimer += delta;
//            if (mmTimer > 3.0f) {
//                mmTimer = 0.0f;
//                attemptMatchmake();
//            }

            // Parallel update of game instances if feasible
            activeGames.values().parallelStream().forEach(gameInstance -> gameUpdatePool.submit(gameInstance::update));

        }, 0L, sleep, TimeUnit.MILLISECONDS);


    }

    public void addClient(Connection connection) {
        if (!remoteClients.containsKey(connection)) {
            RemoteClient newClient = new RemoteClient(connection);
            remoteClients.put(connection, newClient);
            activeGames.get(1).addRemoteClient(connection, newClient);
        } else
            System.out.println("Client with connection ID: " + connection.getID() + " is already connected!");
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


    public static void main(String[] args) {
        try {
            new GameServer();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
