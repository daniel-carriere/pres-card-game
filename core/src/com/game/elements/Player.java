package com.game.elements;

import java.util.*;
import com.card.elements.Hand;
import com.card.elements.card.Card;
import com.card.elements.card.CardController;
import com.card.elements.card.LargeCardView;
import com.game.elements.Player.parts;

public class Player {
	
	public Hand hand;
	public Gameplay gameplay;
	
	public boolean inAction;

	//Parts of the turn
	public parts partOfTurn;
	public static enum parts {NOTSTARTED, STARTING, BEFORE, DURING, AFTER};
		
	public Player(Hand hand, Gameplay gameplay) {
		this.hand = hand;
		this.gameplay = gameplay;
		
		partOfTurn = parts.BEFORE;
		inAction = false;
	}
	
	public Player(Gameplay gameplay) {
		hand = new Hand();
		this.gameplay = gameplay;
	
		partOfTurn = parts.BEFORE;
		inAction = false;
	}
	
	public void setHand(Hand hand){
		this.hand = hand;
	}
	
	public void addToHand(CardController cardController){	
	}
	
	public void sort() {
		hand.sort();
	}
	
	public void start() {
	}
	
	public void play(){
		partOfTurn = parts.DURING;
	}
	
	public void update(){
	}
}
