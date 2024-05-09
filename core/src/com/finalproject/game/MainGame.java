package com.finalproject.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.finalproject.game.screens.OverworldScreen;

public class MainGame extends Game {
	public SpriteBatch batch;

	@Override
	public void create () {
		this.batch = new SpriteBatch();
		this.setScreen(new OverworldScreen());
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
