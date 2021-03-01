package com.game.elements.user;

import com.card.elements.CardList;
import com.card.elements.Hand;
import com.card.elements.card.Card;
import com.card.elements.card.CardController;
import com.game.elements.Gameplay;
import com.game.elements.Player;
import com.game.elements.Player.parts;

public class User extends Player{
	
	public User(Hand hand, Gameplay gameplay) {
		super(hand, gameplay);
	}
	
	public User(Gameplay gameplay) {
		super(gameplay);
	}
	
	@Override
	public void addToHand(CardController cardController) {
		hand.add(cardController);
	}
	
	@Override
	public void update(){
		
		
	}
}
