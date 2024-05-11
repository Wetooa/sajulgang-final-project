package com.finalproject.game.server;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Server;
import com.finalproject.game.server.packets.client.KeyPressed;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class GameServer {

    private final Map<Connection, RemoteClient> remoteClients = new HashMap<>();
    private final Map<Integer, GameInstance> activeGames = new HashMap<>();

    private final Server server;

    public GameServer() throws IOException {

        server = new Server();
        server.start();
        server.bind(54555, 54777);

        Kryo kryo = server.getKryo();
        kryo.setRegistrationRequired(false);


        server.addListener(new Listener() {
                               @Override
                               public void received(Connection connection, Object object) {


                                   System.out.println("[Server]: " + connection + " sent this object " + object);

                                   RemoteClient remoteClient = remoteClients.get(connection);

                                   if (object instanceof KeyPressed) {
                                       int key = ((KeyPressed) object).key;

                                       System.out.println("[Server]: Something was pressed");

                                       if (key == Input.Keys.W) {
                                           System.out.println("[Server]: W was pressed");
//                        remoteClient.inputState = RemoteClient.InputState.LEFT;
                                       }

                                       if (key == Input.Keys.S) {
                                           System.out.println("[Server]: S was pressed");
//                        remoteClient.inputState = RemoteClient.InputState.RIGHT;
                                       }
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
//                addClient(connection);
//                System.out.println("Client connected with ID: " + connection.getID());
//                remoteClients.get(connection).setName("player" + connection.getID());
//                queueClientMatchmaking(connection);
                               }
                           }
        );

    }


    public static void main(String[] args) {
        try {
            new GameServer();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
