package com.game.elements.userField;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.card.elements.*;
import com.card.elements.card.Card;
import com.card.elements.card.CardController;
import com.game.elements.AreaController;

public class MiddleController extends AreaController{
	
	private Middle model;
	public UserMiddleView view;
	
	public MiddleController(Middle model, UserMiddleView view) {
		this.model = model;
		this.view = view;
	}
	
	public void addToPlays(CardController cardController) {
		model.addToPlays(cardController);
	}
	
	public void addToPlays(CardList<CardController> cardControllers) {
		model.addToPlays(cardControllers);
	}
	
	public void setTurn(boolean bool) {
		model.setTurn(bool);
	}
	
	public void updateOnce() {
		view.updateOnce();
		model.updateOnce();
	}
	
	public void update() {
		model.update();
		view.update();
	}
	
	public void draw(Batch batch) {
		view.draw(batch);
	}

}
