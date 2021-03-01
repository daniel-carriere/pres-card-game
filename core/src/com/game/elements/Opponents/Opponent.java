package com.game.elements.Opponents;

import com.card.elements.Hand;
import com.card.elements.card.CardController;
import com.card.elements.card.CardView;
import com.card.elements.card.LargeCardView;
import com.card.elements.card.SmallCardView;
import com.game.elements.Gameplay;
import com.game.elements.Player;

public class Opponent extends Player{
	
	public Opponent(Hand hand, Gameplay gameplay) {
		super(hand, gameplay);
	}
	
	public Opponent(Gameplay gameplay) {
		super(gameplay);
	}
	
	@Override
	public void addToHand(CardController cardController) {
		//Adds to the start of hand
		hand.add(0, cardController);
	}
	
	@Override
	public void start() {
	}
	
	@Override
	public void update(){
		
	}
}
