package com.card.elements;

import java.util.*;

import com.card.elements.card.Card;
import com.card.elements.card.CardController;
import com.card.elements.card.CardView;
import com.card.elements.card.LargeCardView;
import com.card.elements.card.SmallCardView;

public class Deck {

    private CardList<Card> cards = new CardList<Card>();
    public List<CardController> cardControllers = new ArrayList<CardController>();

    public Deck() {

        //Regular cards
        for (int i=0; i < 13; i++)
            cards.add(new Card(i, "spades"));

        for (int i=0; i < 13; i++)
            cards.add(new Card(i, "clubs"));

        for (int i=0; i < 13; i++)
            cards.add(new Card(i, "diamonds"));

        for (int i=0; i < 13; i++)
            cards.add(new Card(i, "hearts"));

        //Joker
        //cards.add(new Joker());
        
        for (int i=0; i < cards.size(); i++) {
        	CardView userCardView = new LargeCardView(cards.get(i), false);
        	CardController cardController = new CardController(cards.get(i), userCardView);
        	cardControllers.add(cardController);
        }
    }
    
    public int size() {
    	return cardControllers.size();
    }

    public void shuffle() {
        Collections.shuffle(cardControllers);
    }

    public Card drawCard() {
        Card drawnCard = cards.get(cards.size()-1);
        cards.remove(cards.size()-1);
        return drawnCard;
    }

    public CardList<Card> drawCards(int amount) {
        CardList<Card> drawnCards = new CardList<Card>();
        for (int i=0; i < amount; i++)
            drawnCards.add(cards.get(cards.size()-1 - i));
        for (int i=0; i < amount; i++)
            cards.remove(cards.get(cards.size()-1));
        	
        return drawnCards;
    }
    
    public CardController drawCardController() {
        CardController drawnCardController = cardControllers.get(cardControllers.size()-1);
        cardControllers.remove(cardControllers.size()-1);
        return drawnCardController;
    }

    public CardList<CardController> drawCardControllers(int amount) {
        CardList<CardController> drawnCardControllers = new CardList<CardController>();
        for (int i=0; i < amount; i++)
            drawnCardControllers.add(cardControllers.get(cardControllers.size()-1 - i));
        for (int i=0; i < amount; i++)
            cardControllers.remove(cardControllers.get(cardControllers.size()-1));
        	
        return drawnCardControllers;
    }

    public String toString() {
        String out = "";
        for (int i=0; i < cards.size(); i++)
            out += cards.get(i).toString() + "\n";
        return out;
    }

}
