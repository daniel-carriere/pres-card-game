package com.game.elements.Opponents;

import com.card.elements.card.CardController;
import com.card.elements.card.LargeCardView;
import com.card.elements.card.SmallCardView;
import com.game.elements.Player;
import com.game.elements.PlayerController;
import com.game.elements.PlayerView;
import com.game.elements.Player.parts;

public class OpponentController extends PlayerController{

	public OpponentController(Player model, PlayerView view) {
		super(model, view);
	}
	
	@Override
	public void addToHand(CardController cardController) {
		cardController.setViewAtStop(new SmallCardView(cardController.model));
		model.addToHand(cardController);
	}
	
	@Override
	public void start(){
		startTime = System.currentTimeMillis();
		model.sort();
	}
	
	@Override
	public void update() {
		currentTime = System.currentTimeMillis();
		elapsedTime = (double)(currentTime - startTime)/ 1000;
		
		model.inAction = false;
		
		if (model.partOfTurn == parts.DURING){
			
			if (elapsedTime > 0.2) {				
				
				if (model.hand.size() > 0) {
					model.inAction = true;
					
					//Draw card from hand
					CardController cardController = model.hand.drawCardController( model.hand.cardControllers.get(0) );
					
					//Change view
					cardController.setView(new LargeCardView(cardController.model, false ));
					
					cardController.flipAtStop();
					
					//Add to field
					model.gameplay.addCardController( cardController );
					
					view.updateOnce();
				}
				
				startTime = System.currentTimeMillis();
				model.partOfTurn = parts.AFTER;
				return;
			}
		}
		
		if (model.partOfTurn == parts.AFTER) {
			if (elapsedTime > 0.2) {
				
				//Tell gameplay the turn is over
				model.gameplay.next();
				
				startTime = System.currentTimeMillis();
				model.partOfTurn = parts.BEFORE;
			}
		}
		
		view.update();
	}

}
