package com.card.elements;

import java.util.*;

import com.card.elements.card.Card;
import com.card.elements.card.CardController;

public class Hand implements Iterable<CardController>{

    public CardList<CardController> cardControllers = new CardList<CardController>();

    public Hand() {
    }

    public Hand(Deck deck, int amount) {
    	cardControllers.addAll(deck.drawCardControllers(amount));
    }

    public void sort() {
        Collections.sort(cardControllers);
    }
    
    public void add(CardController cardController) {
    	cardControllers.add(cardController);
    }
    
    public void add(int i, CardController cardController) {
    	cardControllers.add(i, cardController);
    }

    public CardController drawCardController(CardController specifiedCardController) {
        CardController drawnCardController = null;
        for (CardController cardController: cardControllers) {
            if (cardController == specifiedCardController) {
                drawnCardController = specifiedCardController;
            }
        }
        cardControllers.remove(drawnCardController);
        return drawnCardController;
    }

    public CardList<CardController> drawCardControllers(CardList<CardController> specifiedCardControllers) {
        CardList<CardController> drawnCardControllers = new CardList<CardController>();
        for (CardController cardController: specifiedCardControllers) {
            if (cardControllers.contains(cardController)) {
                drawnCardControllers.add(cardController);
            }
        }
        cardControllers.removeAll(drawnCardControllers);
        return drawnCardControllers;
    }
    
    public CardList<CardController> selectedCardControllers(){
    	CardList<CardController> fittingCardControllers = new CardList<CardController>();
    	
    	for (CardController cardController : cardControllers)
    		if (cardController.model.selected)
    			fittingCardControllers.add(cardController);
    	
    	return fittingCardControllers;
    }
    
    public void makeAvailable(CardList<CardList<Card>> availableCards) {
    	for (CardList<Card> cardList : availableCards)
    		for (Card card : cardList)
    			card.makeAvailable();
    }
    
    //public void makeUnavailable() {
    //	for (Card card : cards)
	//		card.makeUnAvailable();
    //}
    
    public int size() {
    	return cardControllers.size();
    }
    
    //Iterator
    
    @Override
	public Iterator<CardController> iterator() {
    	return cardControllers.iterator();
	}
    
    //public Hand reversed() {
    //	Hand tempHand = new Hand();
    //	for (int i=cards.size()-1; i > -1; i--)
    //		tempHand.add(cards.get(i));
	//	return tempHand;
    	
    //}
    

    //To string function

    public String toString() {
        String out = "";
        for (int i=0; i < cardControllers.size(); i++)
            out += cardControllers.get(i).toString() + "\n";
        return out;
    }
    
    //Dispose
    
    public void dispose(){
    }

}