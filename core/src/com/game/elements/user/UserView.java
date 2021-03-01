package com.game.elements.user;

import java.util.*;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.card.elements.card.LargeCardView;
import com.game.elements.Player;
import com.game.elements.PlayerView;
import com.game.elements.userField.*;

public class UserView extends PlayerView{
	
	private User model;
	private MiddleController userFieldController;
	
	private int x, y;
	private int width;
	
	public UserView(User user, int x, int y, int width) {
		this.model = user;
		this.userFieldController = model.gameplay.userFieldController;
		
		this.x = x;
		this.y = y;
		this.width = width;

	}
	
	@Override
	public void flipToFront() {
		for (int i=0; i < model.hand.cardControllers.size(); i++) {
			model.hand.cardControllers.get(i).flipToFront();
		}
	}
	
	@Override
	public void updateOnce() {
		for (int i=0; i < model.hand.cardControllers.size(); i++) {
			
			//Card placement
			int xPad = 0;
			if (model.hand.size() >= 11 )
				xPad = i * ((width - 168) / (model.hand.size() - 1));
			else
				xPad = (150) * i + ((11 - model.hand.size()) * 140)/2;
			
			//Place the card
			model.hand.cardControllers.get(i).setNextPos(x + xPad, y);
		}
	}
	
	@Override
	public void update() {
		if (model.partOfTurn == Player.parts.DURING || model.partOfTurn == Player.parts.AFTER)
			userFieldController.setTurn(true);
		else
			userFieldController.setTurn(false);
		
		for (int i=0; i < model.hand.cardControllers.size(); i++) 
			model.hand.cardControllers.get(i).update();
		
		if (model.inAction)
			updateOnce();
	}

	@Override
	public void draw(Batch batch) {
		for (int i=0; i < model.hand.cardControllers.size(); i++)
			model.hand.cardControllers.get(i).draw(batch);
	}
}
