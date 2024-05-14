package com.finalproject.game.client;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.finalproject.game.client.packet.client.KeyPressed;
import com.finalproject.game.server.packet.server.GameInstanceSnapshot;

import java.io.IOException;
import java.nio.channels.ClosedSelectorException;


public class ClientController {

    public static Client client;

    public ClientController(String ip) throws IOException {
        client = new Client();
        client.start();

        Kryo kryo = client.getKryo();
        kryo.setRegistrationRequired(false);

        client.connect(5000, ip, 54555, 54777);
        setupController();
    }

    public void setupController() {
        Gdx.app.log("Controller", "Setting up controllers...");

        Gdx.input.setInputProcessor(new InputAdapter() {
            @Override
            public boolean keyDown(int keycode) {

                Gdx.app.log("Player", "Clicked " + keycode);

                ClientController.client.sendUDP(new KeyPressed(keycode));

                if (keycode == Input.Keys.SPACE)
                    Gdx.app.log("ping: ", ClientController.client.getReturnTripTime() + " ");

                return false;
            }

            @Override
            public boolean keyUp(int keycode) {
                return super.keyUp(keycode);
            }
        });


        client.addListener(new Listener() {
            @Override
            public void received(Connection connection, Object object) {
                Gdx.app.log("Client", "Recieved " + object + " from " + connection);

                if (object instanceof GameInstanceSnapshot) {
                    GameInstanceSnapshot game = (GameInstanceSnapshot) object;


                }
            }
        });
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
