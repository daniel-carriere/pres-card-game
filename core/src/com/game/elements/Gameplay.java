package com.game.elements;

import java.util.*;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.card.elements.*;
import com.card.elements.card.Card;
import com.card.elements.card.CardController;
import com.card.elements.card.CardView;
import com.card.elements.card.SmallCardView;
import com.game.elements.Opponents.*;
import com.game.elements.user.User;
import com.game.elements.user.UserController;
import com.game.elements.user.UserView;
import com.game.elements.userField.Middle;
import com.game.elements.userField.MiddleController;
import com.game.elements.userField.UserMiddleView;
import com.game.screens.GameScreen;
import com.game.screens.PresCardGame;

public class Gameplay {
	
	//No. of players
	public int amount;
	
	//All players
	public List<Player> players;
	public List<PlayerController> playerControllers;
	
	//Only the opponents
	public List<Opponent> opponents;
	
	//The User
	public Player user;
	public PlayerController userController;
	
	//The playing field as seen by the user
	public Middle userField;
	public UserMiddleView userFieldView;
	public MiddleController userFieldController;
	
	//The opponents as seen by the user
	public UserOpponents userOpponents;
	
	//The user's hand as seen by the user
	public UserView userView;
	
	public int cursor;
	
	public Dealer dealer;
	
	public Gameplay(int amount, GameScreen gameScreen) {
		this.amount = amount;
		
		//players and player controller lists
		
		players = new ArrayList<Player>();
		opponents = new ArrayList<Opponent>();
		
		playerControllers = new ArrayList<PlayerController>();
			
		cursor = -1;
		
		//Middle
		
		userField = new Middle(this);
		userFieldView = new UserMiddleView(userField, 425);
		userFieldController = new MiddleController(userField, userFieldView);
		
		//user hand, User
		
		user = new User(this);
		userView = new UserView((User) user, (int) PresCardGame.GAME_WIDTH/2 - 1600/2, 75, 1600);
		userController = new UserController(user, userView);
		
		players.add(user);
		playerControllers.add(userController);
		
		//opponent hands and opponents
		
		for (int i=0; i < amount - 1; i++) {

			Opponent opp = new Opponent(this);
			
			players.add(opp);
			opponents.add(opp);
		}
		
		//UserOpponents
		
		userOpponents = new UserOpponents(opponents, 730);
		
		//opponent controllers
	
		for (int i=0; i < opponents.size(); i++) {
			PlayerController OpponentController = new OpponentController(opponents.get(i), userOpponents.getUserOpponentView(i));
			playerControllers.add(OpponentController);
			
		}
		
		dealer = new Dealer(this, 700, 440);
		dealer.start();
			
	}
	
	public void next(){
		//Switch player's turn
		cursor++;
		cursor = cursor % amount;
		playerControllers.get(cursor).play();
		
	}
	
	public void addCardController(CardController cardController) {
		userFieldController.addToPlays(cardController);
		userFieldController.updateOnce();
	}
	
	public void addCardController(CardList<CardController> cardControllers) {
		userFieldController.addToPlays(cardControllers);
		userFieldController.updateOnce();
	}

	
	public void update(){
		dealer.update();
		
		for (int i=0; i < playerControllers.size(); i++)
			playerControllers.get(i).update();

		userFieldController.update();
		
	}
	
	public void draw(Batch batch){
		dealer.draw(batch);
		
		for (int i=0; i < playerControllers.size(); i++)
			playerControllers.get(i).draw(batch);
		
		userFieldController.draw(batch);
		
	}

}
