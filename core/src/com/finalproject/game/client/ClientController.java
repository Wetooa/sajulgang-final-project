package com.finalproject.game.client;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.finalproject.game.client.packet.client.*;
import com.finalproject.game.server.packet.server.GameInstanceSnapshot;

import java.io.IOException;
import java.nio.channels.ClosedSelectorException;


public class ClientController {

    public static Client client;
    private final static int BUFFER_SIZE = 1024 * 1024;

    public ClientController(String ip) throws IOException {
        client = new Client(BUFFER_SIZE, BUFFER_SIZE);
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
                return super.keyDown(keycode);
            }

            @Override
            public boolean keyUp(int keycode) {
                Gdx.app.log("Player", "Released " + keycode);
                ClientController.client.sendUDP(new KeyReleased(keycode));
                return super.keyUp(keycode);
            }

            @Override
            public boolean touchDown(int screenX, int screenY, int pointer, int button) {
                Gdx.app.log("Player", "Mouse Pressed " + button);
                Vector3 worldCoordinates = GameClient.camera.unproject(new Vector3(screenX, screenY, 0));
                ClientController.client.sendUDP(new MousePressed(worldCoordinates.x, worldCoordinates.y, button));
                return super.touchDown(screenX, screenY, pointer, button);
            }

            @Override
            public boolean touchUp(int screenX, int screenY, int pointer, int button) {
                Gdx.app.log("Player", "Mouse Released " + button);
                Vector3 worldCoordinates = GameClient.camera.unproject(new Vector3(screenX, screenY, 0));
                ClientController.client.sendUDP(new MouseReleased(worldCoordinates.x, worldCoordinates.y, button));
                return super.touchUp(screenX, screenY, pointer, button);
            }


            @Override
            public boolean touchDragged(int screenX, int screenY, int pointer) {
                Gdx.app.log("Player", "Mouse Dragged " + screenX + " " + screenY);
                Vector3 worldCoordinates = GameClient.camera.unproject(new Vector3(screenX, screenY, 0));
                ClientController.client.sendUDP(new MouseMove(worldCoordinates.x, worldCoordinates.y));
                return super.touchDragged(screenX, screenY, pointer);
            }

            @Override
            public boolean mouseMoved(int screenX, int screenY) {
                Gdx.app.log("Player", "Moved mouse to " + screenX + " " + screenY);
                Vector3 worldCoordinates = GameClient.camera.unproject(new Vector3(screenX, screenY, 0));
                ClientController.client.sendUDP(new MouseMove(worldCoordinates.x, worldCoordinates.y));
                return super.mouseMoved(screenX, screenY);
            }
        });


        client.addListener(new Listener() {
            @Override
            public void received(Connection connection, Object object) {
                Gdx.app.log("Client", "Received " + object + " from " + connection);

                // TODO: lol they look the same but trust me they *might differ lololkasdjfiosdni

                if (object instanceof GameInstanceSnapshot) {
                    GameClient.gameInstanceSnapshot = (GameInstanceSnapshot) object;
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
