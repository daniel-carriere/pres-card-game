package com.game.elements.userField;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.card.elements.*;
import com.card.elements.card.CardController;
import com.card.elements.card.LargeCardView;
import com.game.elements.Gameplay;
import com.game.screens.PresCardGame;

public class UserMiddleView {
	
	private Middle model;
	
	public int y;
	
	//Centre x, centre y
	public int cX, cY;
	
	private Texture whiteImage;
	private Sprite turnMarker;
	private int width;
	
	
	public UserMiddleView(Middle model, int y) {
		this.model = model;
		this.y = y;
		
		width = 360;
		
		cX = (int) PresCardGame.GAME_WIDTH/2 - width/2;
		cY = y + 15;
		
		whiteImage = new Texture(Gdx.files.internal("white.png"));
		turnMarker = new Sprite(new TextureRegion(whiteImage, 0, 0, (int) PresCardGame.GAME_WIDTH, 300));
		turnMarker.setPosition(0, y);
	}
	
	public void updateOnce() {
		
		int gap;
		int halfWidthCard;
		int halfWidthField;
		
		List<CardController> currentPlay = model.plays.get(model.plays.size()-1);
		
		if (currentPlay.size() > 1) {
			gap = (width - LargeCardView.WIDTH)/(currentPlay.size() - 1);
			halfWidthCard = 0;
			halfWidthField = 0;
		}else {
			gap = 1;
			halfWidthCard = LargeCardView.WIDTH/2;
			halfWidthField = width/2;
		}
		
		for (int i=0; i < currentPlay.size(); i++)
			currentPlay.get(i).setNextPos(cX + halfWidthField - halfWidthCard + i*gap, cY);
	}
	
	public void update() {
		if (model.turn)
			turnMarker.setAlpha(0.08f);
		else
			turnMarker.setAlpha(0f);
	
		
	}
	
	public void draw(Batch batch){
		turnMarker.draw(batch);

		for (int i=0; i < model.plays.size(); i++)
			for (int j=0; j < model.plays.get(i).size(); j++)
				model.plays.get(i).get(j).draw(batch);

	}

}
