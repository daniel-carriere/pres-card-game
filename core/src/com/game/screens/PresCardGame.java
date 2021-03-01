package com.game.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class PresCardGame extends Game {
	public SpriteBatch batch;
	
	public static final float GAME_WIDTH = 2200;
    public static final float GAME_HEIGHT = 1080;

	public void create(){
		batch = new SpriteBatch();
		this.setScreen(new MainMenuScreen(this));
	}

	public void render(){
		super.render();
	}

	public void dispose(){
		batch.dispose();
	}
}
