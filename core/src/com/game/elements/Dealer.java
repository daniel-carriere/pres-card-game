package com.game.elements;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.card.elements.CardList;
import com.card.elements.Deck;
import com.card.elements.Hand;
import com.card.elements.card.CardController;
import com.card.elements.card.CardView;
import com.card.elements.card.SmallCardView;
import com.game.elements.Opponents.Opponent;

public class Dealer {
	
	public int x, y;
	
	//Deck and players
	public Deck deck;
	public List<PlayerController> playerControllers;
	
	//Time
	private long startTime;
	private long currentTime;
	private double elapsedTime;
	
	public boolean started;
	
	public int cursor;
	
	//Cards
	private List<CardController> cardControllers;
	
	public Dealer(Gameplay gameplay, int x, int y) {
		playerControllers = gameplay.playerControllers;
		
		this.x = x;
		this.y = y;
		
		cursor = -1;

		cardControllers = new ArrayList<CardController>();
			
		deck = new Deck();
		deck.shuffle();
		
		int deckSize = deck.size();
		for (int i=0; i < deckSize; i++) {
			cardControllers.add(deck.drawCardController());
			//Stacking offset
			cardControllers.get(i).setPos(x + i/4, y + i/4);
		}
		
	}
	
	public void addCards(List<CardController> cardControllers) {
		this.cardControllers.addAll(cardControllers);
	}
	
	public void start() {
		startTime = System.currentTimeMillis();
		started = true;
		
	}
	
	public void finish() {
		for (int i=0; i < playerControllers.size(); i++) {
			playerControllers.get(i).start();
			playerControllers.get(i).updateOnce();

		}
	}
	
	public void updateOnce() {
		for (int i=0; i < cardControllers.size(); i++) {
			cardControllers.get(i).setNextPos(x + i/4, y + i/4);
		}
	}
	
	public void update() {
		currentTime = System.currentTimeMillis();
		elapsedTime = (double)(currentTime - startTime)/ 1000;
		
		for (int i=0; i < cardControllers.size(); i++) {
			cardControllers.get(i).update();
		}
		
		if (started) {
			if (elapsedTime > 0.050) {
				
				//Switch players
				cursor++;
				cursor = cursor % playerControllers.size();
				
				if (cardControllers.size() > 0) {
				
					//Add card to player's hand
					CardController cardController = cardControllers.get(cardControllers.size()-1); 
					cardControllers = cardControllers.subList(0, cardControllers.size() -1);
				
					playerControllers.get(cursor).addToHand(cardController);
					playerControllers.get(cursor).updateOnce();
					
				}else {
					started = false;
					finish();
				}
				startTime = System.currentTimeMillis();
				
			}
		}
		
		for (int i=0; i < cardControllers.size(); i++) {
			cardControllers.get(i).update();
		}
		
	}
	
	public void draw(Batch batch) {
		for (int i=0; i < cardControllers.size(); i++)
			cardControllers.get(i).draw(batch);
	}

}
