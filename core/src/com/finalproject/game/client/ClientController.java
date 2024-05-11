package com.finalproject.game.client;


import com.badlogic.gdx.Gdx;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;

import java.io.IOException;
import java.nio.channels.ClosedSelectorException;

public class ClientController {

    public static final String version = "1.0";
    public static ClientController clientController;
    public static Client client;
//    private GameScreen gameScreen;

    public ClientController(String ip) throws IOException {
        client = new Client();
        client.start();

        Kryo kryo = client.getKryo();
        kryo.setRegistrationRequired(false);

        client.connect(5000, ip, 54555, 54777);
        setupController();
    }

    public void setupController() {
//        client.sendTCP(new RegisterName(Save.INSTANCE.name));

        Gdx.app.log("Controller", "Setting up controllers...");

        client.addListener(new Listener() {
            @Override
            public void received(Connection connection, Object object) {
//                System.out.println(object);
            }
        });

//        Listener.typeListener = new Listener.TypeListener();

//        typeListener.addTypeHandler(GameSetup.class, ((connection, gameSetup) -> {
//            Gdx.app.log("Game setup", gameSetup.toString());
//            Gdx.app.postRunnable(() -> {
//                gameScreen = new GameScreen(main, this, gameSetup);
//                main.setScreen(gameScreen);
//            });
//        }));
//
//        typeListener.addTypeHandler(GameInstanceSnapshot.class, (connection, snapshot) -> {
//            gameScreen.parse(snapshot);
//        });
//
//        typeListener.addTypeHandler(GameEndDisconnect.class, ((connection, s) -> {
////            Gdx.app.postRunnable(() -> gameScreen.opponentDisconnected());
//        }));
//
//        typeListener.addTypeHandler(Error.class, ((connection, error) -> {
//            error.printStackTrace();
//        }));
//
//
//        client.addListener(typeListener);
    }


    public void dispose() {
        try {
            client.close();
            client = null;
        } catch (ClosedSelectorException e) {
            e.printStackTrace();
        }
    }

}
