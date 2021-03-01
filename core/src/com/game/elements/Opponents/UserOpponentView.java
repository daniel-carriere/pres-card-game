package com.game.elements.Opponents;

import java.util.*;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.game.elements.PlayerView;
import com.game.screens.PresCardGame;

public class UserOpponentView extends PlayerView{
	
	private Texture whiteImage;
	private Sprite turnMarker;
	
	public Opponent model;
	
	//Specific pponent number and number of opponents
	public int oppNo, amount;
	
	private UserOpponentCards userOpponentCards;

	public int y;
	public int height;
	
	public UserOpponentView(Opponent model, int y, int oppNo, int amount) {
	
		this.model = model;
		this.y = y;
		
		centreX = (int) (oppNo * (PresCardGame.GAME_WIDTH / amount) + (PresCardGame.GAME_WIDTH / amount)/2);
		centreY = (int) (PresCardGame.GAME_HEIGHT - height + 50);
		height = 350;
		
		//Represents the opponents turn
		whiteImage = new Texture(Gdx.files.internal("white.png"));
		turnMarker = new Sprite(new TextureRegion(whiteImage, 0, 0, (int) (PresCardGame.GAME_WIDTH / amount), height));
		turnMarker.setPosition(oppNo * ( (int) (PresCardGame.GAME_WIDTH / amount)), PresCardGame.GAME_HEIGHT - height );

		userOpponentCards = new UserOpponentCards(oppNo * (int) (PresCardGame.GAME_WIDTH / amount) + 60, y + 70, (int) (PresCardGame.GAME_WIDTH / amount) - 120, model );
			
	}
	
	@Override
	public void updateOnce() {
		userOpponentCards.updateOnce();
	}
	
	@Override
	public void update() {
		
		//Update turn marker
		if (model.partOfTurn == Opponent.parts.DURING || model.partOfTurn == Opponent.parts.AFTER)
			turnMarker.setAlpha(0.08f);
		else
			turnMarker.setAlpha(0f);
			
		userOpponentCards.update();
		
		if (model.inAction)
			updateOnce();

	}
	
	@Override
	public void draw(Batch batch) {

		turnMarker.draw(batch);
		userOpponentCards.draw(batch);
			
	}

}
