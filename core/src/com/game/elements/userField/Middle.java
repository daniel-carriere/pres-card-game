package com.game.elements.userField;

import java.util.*;
import com.card.elements.*;
import com.card.elements.card.Card;
import com.card.elements.card.CardController;
import com.game.elements.Gameplay;

public class Middle {
	
	public Gameplay gameplay;
	
	public boolean turn;	
	
	//List of played cards
	public List<List<CardController>> plays;
	
	public Middle(Gameplay gameplay) {
		
		plays = new ArrayList<List<CardController>>();
		this.gameplay = gameplay;
		
	}
	
	public void addToPlays(CardController cardController) {
		List<CardController> play = new ArrayList<CardController>();
		play.add(cardController);
		
		plays.add(play);
		
	}
	
	public void addToPlays(CardList<CardController> cardControllers) {
		plays.add(cardControllers);
	}
	
	public void setTurn(boolean bool) {
		turn = bool;
	}
	
	public void updateOnce() {		
		if (plays.size()>2) {
			gameplay.dealer.addCards(plays.get(plays.size()-3));
			
			for (int i=0; i < plays.get(plays.size()-3).size(); i++) {
				plays.get(plays.size()-3).get(i).flipToBack();
				
			}
			
			gameplay.dealer.updateOnce();
		}
	}
	
	public void update() {		
		for (int i=0; i < plays.size(); i++)
			for (int j=0; j < plays.get(i).size(); j++)
				plays.get(i).get(j).update();
	}
}
