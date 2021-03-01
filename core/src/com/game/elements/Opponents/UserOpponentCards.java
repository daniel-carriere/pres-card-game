package com.game.elements.Opponents;

import java.util.*;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.card.elements.card.CardView;
import com.card.elements.card.SmallCardView;

public class UserOpponentCards {
	
	public int x, y;
	public int width;
	
	private Opponent opp;
	
	//Gap between cards when hand contains relatively fewer cards
	private int gap;
	
	public UserOpponentCards(int x, int y, int width, Opponent opp) {
		this.x = x;
		this.y = y;
	
		this.width = width;
		
		this.opp = opp;
		
		gap = 60;
	}
	
	public void updateOnce() {
	
		double xPad;
		int newGap;
		
		//Card placement
		if (((opp.hand.size() - 1) * gap + SmallCardView.WIDTH) > width ) {
			xPad = 0;
			newGap = (width - SmallCardView.WIDTH)/(opp.hand.size() - 1);
		}else {
			xPad = (width/2 - ( ((opp.hand.size() - 1) * gap + SmallCardView.WIDTH) / 2 )  );
			newGap = gap;
		}
		
		//Move cards into place
		for (int i=opp.hand.size()-1; i > -1; i--)
			opp.hand.cardControllers.get(i).setNextPos( x + (int) xPad + i*newGap, y );		
	}
	
	public void update() {
		
		for (int i=opp.hand.cardControllers.size()-1; i > -1; i--)
			opp.hand.cardControllers.get(i).update();
	}
	
	public void draw(Batch batch) {
		
		for (int i=opp.hand.cardControllers.size()-1; i > -1; i--)
			opp.hand.cardControllers.get(i).draw(batch);		
	}

}
