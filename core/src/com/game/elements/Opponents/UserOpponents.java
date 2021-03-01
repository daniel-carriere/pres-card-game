package com.game.elements.Opponents;

import java.util.*;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.game.elements.PlayerView;
import com.game.screens.PresCardGame;

public class UserOpponents{
	
	private List<Opponent> models;
	
	public List<UserOpponentView> userOpponentViews;
	
	public int y;
	
	public UserOpponents(List<Opponent> models, int y) {
	
		this.models = models;
		this.y = y;
		
		userOpponentViews = new ArrayList<UserOpponentView>();
		
		//Create views
		for (int i=0; i < models.size(); i++) {
			userOpponentViews.add(new UserOpponentView(models.get(i), y, i, models.size()));
		}
			
	}
	
	public UserOpponentView getUserOpponentView(int i) {
		return userOpponentViews.get(i);
	}

	public void update() {
		for (int i=0; i < models.size(); i++) {
			userOpponentViews.get(i).update();		
		}
	}
	
	public void draw(Batch batch) {
		
		for (int i=0; i < models.size(); i++) {
			userOpponentViews.get(i).draw(batch);		
		}
			
	}

}