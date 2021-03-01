package com.card.elements.card;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.card.elements.card.LargeCardView.orient;

public class CardController implements Comparable<CardController>{
	
	public Card model;
	public CardView view;
	
	private boolean changeView;
	private boolean flip;
	
	private CardView nextView;
	
	public CardController(Card card, CardView view){
		this.model = card;
		this.view = view;
	}
	
	public void setView(CardView view) {
		this.view = view;
	}

	public void flipToFront() {
		view.flipToFront();
	}
	
	public void flipToBack() {
		view.flipToBack();
	}
	
	public void setViewAtStop(CardView view) {
		changeView = true;
		nextView = view;
	}
	
	public void flipAtStop() {
		flip = true;
	}
	
	public void setPos(int x, int y) {
		view.setPos(x, y);
	}
	
	public void setNextPos(int x, int y) {
		view.setNextPos(x, y);
	}
	
	public void update() {
		
		//Change view on stop
		if (changeView && view.doneMove) {
			nextView.copyFromOtherView(view);
			
			view = nextView;
			
			changeView = false;
		}
		
		//flip on stop
		if (flip && view.doneMove) {
			flipToFront();
			
			flip = false;
		}
		
		//Update view
		view.update();
		
	}
	
	public void draw(Batch batch) {
		view.draw(batch);
	}
	

	//Comparison

	@Override
	public int compareTo(CardController other) {
		return Integer.compare(this.model.rank.value, other.model.rank.value);
	}

}
