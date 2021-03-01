package com.game.elements.user;

import com.card.elements.CardList;
import com.card.elements.card.CardController;
import com.card.elements.card.SmallCardView;
import com.game.elements.Player;
import com.game.elements.PlayerController;
import com.game.elements.PlayerView;
import com.game.elements.Player.parts;

public class UserController  extends PlayerController{
	
	private double startDelay;

	public UserController(Player model, PlayerView view) {
		super(model, view);
		startDelay = 0.5;
	}
	
	@Override
	public void addToHand(CardController cardController) {
		model.addToHand(cardController);
	}
	
	@Override
	public void start(){
		startTime = System.currentTimeMillis();
		starting = true;
		counter = model.hand.size()-1;
	}
	
	@Override
	public void update() {
		currentTime = System.currentTimeMillis();
		elapsedTime = (double)(currentTime - startTime)/ 1000;
		
		model.inAction = false;
		
		if (starting) {
			if (elapsedTime > startDelay) {
				startDelay = 0.025;
				if (counter >= 0){
					model.hand.cardControllers.get(counter).flipToFront();
					counter--;
				}else {
					starting = false;
					model.sort();
					view.updateOnce();
				}
				startTime = System.currentTimeMillis();
			}
		}
		
		if (model.partOfTurn == parts.DURING){
			
			if (elapsedTime > 0.2) {				
				
				if (model.hand.size() > 0) {
					model.inAction = true;
					
					CardList<CardController> cardControllers = new CardList<CardController>();
					
					CardController cardController1 = model.hand.drawCardController( model.hand.cardControllers.get(0) );
					//CardController cardController2 = model.hand.drawCardController( model.hand.cardControllers.get(0) );
					//CardController cardController3 = model.hand.drawCardController( model.hand.cardControllers.get(0) );
					//CardController cardController4 = model.hand.drawCardController( model.hand.cardControllers.get(0) );
					
					cardControllers.add(cardController1);
					//cardControllers.add(cardController2);
					//cardControllers.add(cardController3);
					//cardControllers.add(cardController4);
					
					//Add card to field
					model.gameplay.addCardController( cardControllers );
					
					view.updateOnce();
				
				}
				
				startTime = System.currentTimeMillis();
				model.partOfTurn = parts.AFTER;
				return;
			}
		}
		
		if (model.partOfTurn == parts.AFTER) {
			if (elapsedTime > 0.2) {
				
				model.gameplay.next();
				
				startTime = System.currentTimeMillis();
				model.partOfTurn = parts.BEFORE;
			}
		}
		
		view.update();
	}

}
