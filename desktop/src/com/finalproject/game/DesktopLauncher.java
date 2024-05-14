package com.finalproject.game;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.finalproject.game.client.ClientController;
import com.finalproject.game.client.GameClient;

// Please note that on macOS your application needs to be started with the -XstartOnFirstThread JVM argument
public class DesktopLauncher {
	public static void main (String[] arg) {
		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
		config.setForegroundFPS(60);

		// FIX: set fullscreen later
//		config.setFullscreenMode(Lwjgl3ApplicationConfiguration.getDisplayMode());

		config.setTitle("Lost in CIT (tentative name)");
		new Lwjgl3Application(new GameClient(), config);
	}
}
