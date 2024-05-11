package com.finalproject.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.finalproject.game.screens.OverworldScreen;

public class MainGame extends Game {
	public SpriteBatch batch;

	@Override
	public void create () {
		Gdx.app.log("Client: ", "Initializing client");

		this.batch = new SpriteBatch();
		this.setScreen(new OverworldScreen(this));
	}

	@Override
	public void render () {
		super.render();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
	}
}
