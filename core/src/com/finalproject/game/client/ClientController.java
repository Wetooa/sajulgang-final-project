package com.finalproject.game.client;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.math.Vector3;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.finalproject.game.client.packet.client.*;
import com.finalproject.game.client.resources.Assets;
import com.finalproject.game.client.sound.SoundPlayer;
import com.finalproject.game.server.packet.server.GameInstanceSnapshot;
import com.finalproject.game.server.packet.server.ItemSoundPlay;
import com.finalproject.game.server.packet.server.SoundPlay;
import com.finalproject.game.server.packet.server.WinGame;
import com.finalproject.game.server.packet.server.jdbc.LoginSuccessPacket;

import java.io.IOException;
import java.nio.channels.ClosedSelectorException;

import static com.finalproject.game.client.GameClient.isWinner;
import static com.finalproject.game.client.GameClient.screenShake;


public class ClientController {

    private final static int BUFFER_SIZE = 1024 * 1024;
    public static Client client;

    public ClientController(String ip) throws IOException {
        client = new Client(BUFFER_SIZE, BUFFER_SIZE);
        client.start();

        Kryo kryo = client.getKryo();
        kryo.setRegistrationRequired(false);
        kryo.setReferences(true);

        client.connect(5000, ip, 54555, 54777);


        client.addListener(new Listener() {
            @Override
            public void received(Connection connection, Object object) {
                Gdx.app.log("Client", "Received " + object + " from " + connection);

                // TODO: lol they look the same but trust me they *might differ lololkasdjfiosdni

                if (object instanceof GameInstanceSnapshot) {
                    GameClient.gameInstanceSnapshot = (GameInstanceSnapshot) object;
                }

                if (object instanceof ItemSoundPlay) {
                    Assets.gunSoundAssets.get(((ItemSoundPlay) (object)).itemType).play();
                    screenShake.shake(0.25f, 0.5f); // Shake with power 10 for 0.5 seconds
                }

                if (object instanceof SoundPlay) {
                    SoundPlayer.SoundType sp = ((SoundPlay) object).soundType;
                    Assets.soundAssets.get(((SoundPlay) (object)).soundType).play();

                    if (sp.equals(SoundPlayer.SoundType.RUN)) {
                        screenShake.shake(0.1f, 0.25f); // Shake with power 10 for 0.5 seconds
                    }
                }

                if (object instanceof LoginSuccessPacket) {
                    setupGameControllers();
                }


                if (object instanceof WinGame) {
                    GameClient.isDone = true;
                    GameClient.isWinner = ((WinGame) object).isWinner;


                    if (isWinner) Assets.musicAssets.get(SoundPlayer.MusicType.WIN).play();
                    else Assets.musicAssets.get(SoundPlayer.MusicType.LOSE).play();
                }

            }
        });
    }


    public static void setupGameControllers() {
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


            @Override
            public boolean scrolled(float amountX, float amountY) {
                Gdx.app.log("Player", "Moved scrolled " + amountX + " " + amountY);
                ClientController.client.sendUDP(new MouseScroll(amountY < 0));
                return super.scrolled(amountX, amountY);
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
