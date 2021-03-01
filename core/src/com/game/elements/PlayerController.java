package com.game.elements;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.card.elements.Hand;
import com.card.elements.card.Card;
import com.card.elements.card.CardController;
import com.card.elements.card.SmallCardView;
import com.game.elements.Opponents.Opponent;

public class PlayerController extends AreaController{
	
	public Player model;
	public PlayerView view;
	
	//Time
	protected long startTime;
	protected long currentTime;
	protected double elapsedTime;
	
	public boolean starting;
	
	protected int counter;
	
	public PlayerController(Player model, PlayerView view) {
		this.model = model;
		this.view = view;
	}
	
	public void setHand(Hand hand) {
		model.setHand(hand);
	}
	
	@Override
	public void addToHand(CardController cardController) {
		cardController.setViewAtStop(new SmallCardView(cardController.model));
		model.addToHand(cardController);
	}
	
	public void sort() {
		model.sort();
	}
	
	public void start() {
	}
	
	public void flipToFront() {
		view.flipToFront();
	}
	
	public void flipToBack() {
		view.flipToBack();
	}
	
	public void play() {
		model.play();
	}
	
	public void updateOnce() {
		view.updateOnce();
	}
	
	public void update() {
	}
	
	public void draw(Batch batch) {
		view.draw(batch);
	}
	
}
