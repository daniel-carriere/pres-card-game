package com.card.elements.card;

import com.badlogic.gdx.graphics.g2d.Batch;

public class CardView {
	
	public Card model;
	
	public float x, y;
	
	//Movement
	public float tX, tY;
	public float direction;
	public float speed;
	public float time;
	public boolean inTransit;
	public float totalDistance;
	public float whenDone;

	//On completed movement
	protected boolean flip;
	public boolean doneMove;
	
	public CardView(Card card) {
		this.model = card;

		direction = 0;
		time = 10;
		whenDone = 0.6f;
		
	}
	
	public void flipToFront() {
	}
	
	public void flipToBack() {
	}
	
	public void setPos(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public void copyFromOtherView(CardView view) {
		x = view.x;
		y = view.y;
		direction = view.direction;
		speed = view.speed;
		tX = view.tX;
		tY = view.tY;
		inTransit = view.inTransit;
	}
	
	public void setNextPos(int x, int y) {
		tX = x;
		tY = y;
		
		inTransit = true;
		
		float deltaX = tX - this.x;
        float deltaY = tY - this.y;
        
        direction = (float) Math.atan2(deltaY, deltaX);
        
        totalDistance = distance();
        
        speed = totalDistance/time;
	}
	
	public float distance() {
		return (float) Math.sqrt( Math.pow(Math.abs(this.x - tX), 2) + Math.pow(Math.abs(this.y - tY), 2) );
	}
	
	public void update() {
		if (inTransit) {
			doneMove = false;
			
			if (distance() > speed + 1) {
				
				this.x += speed * Math.cos(direction);
				this.y += speed * Math.sin(direction);

			}else {
				this.x = tX;
				this.y = tY;
				inTransit = false;
				
			}
			
			if(distance() < totalDistance * whenDone)
				doneMove = true;
		}
	}
	
	public void draw(Batch batch) {
	}

}
