package com.card.elements;

import java.util.*;

import com.card.elements.card.Card;
import com.card.elements.card.CardController;

public class CardList<E> extends ArrayList<E> {

    public CardList(){
    }

    public CardList(CardController[] CardControllers){
        for (CardController CardController : CardControllers)
            this.add((E) CardController);
    }

    public CardList<CardController> getHigher(CardController CardController) {
        CardList<CardController> fittingCards = new CardList<CardController>();
        for (int i=0; i < this.size(); i++)
            if (( (CardController) this.get(i)).model.rank.value > CardController.model.rank.value)
                fittingCards.add((CardController) this.get(i));

        return fittingCards;
    }
    
    public CardList<CardList<CardController>> getSingles() {
    	CardList<CardList<CardController>> fittingCards = new CardList<CardList<CardController>>();
        CardList<CardController> fittingCardsSet = new CardList<CardController>();
        
        for (int i=0; i < this.size(); i++) {
        	fittingCardsSet = new CardList<CardController>();
        	fittingCardsSet.add((CardController) this.get(i));
        	fittingCards.add(fittingCardsSet);
        }
        
        return fittingCards;
    }

    public CardList<CardList<CardController>> getMultis(int amount) {
        CardList<CardList<CardController>> fittingCards = new CardList<CardList<CardController>>();
        CardList<CardController> fittingCardsSet = new CardList<CardController>();
        Map<Integer, Integer> counts = new HashMap<Integer, Integer>();

        for (int i=0; i < this.size(); i++)
            if(!counts.containsKey( ((CardController) this.get(i)).model.rank.value))
                counts.put( ((CardController) this.get(i)).model.rank.value, 1 );
            else
                counts.put( ((CardController) this.get(i)).model.rank.value, counts.get( ((CardController) this.get(i)).model.rank.value) + 1 );

        for (Map.Entry<Integer, Integer> entry : counts.entrySet())
            if (entry.getValue() >= amount) {
                fittingCardsSet = new CardList<CardController>();
                for (E CardController: this)
                    if ( ((CardController) CardController).model.rank.value == entry.getKey())
                        fittingCardsSet.add( (CardController) CardController);
                		if (fittingCardsSet.size() >= amount)
                			fittingCards.add(fittingCardsSet);
                	
               
            }

        return fittingCards;
    }


    public CardList<CardList<CardController>> getStraights(int amount){
        CardList<CardList<CardController>> fittingCards = new CardList<CardList<CardController>>();

        fittingCards.addAll(this.getSameSuit("spades").getStraightsIntermediate(amount));
        fittingCards.addAll(this.getSameSuit("clubs").getStraightsIntermediate(amount));
        fittingCards.addAll(this.getSameSuit("diamonds").getStraightsIntermediate(amount));
        fittingCards.addAll(this.getSameSuit("hearts").getStraightsIntermediate(amount));

        return fittingCards;
    }

    public CardList<CardList<CardController>> getStraightsIntermediate(int amount) {
        CardList<CardList<CardController>> fittingCards = new CardList<CardList<CardController>>();
        CardList<CardController> fittingCardsSet = new CardList<CardController>();

        int counter = 1;

        for (int i=0; i < this.size()-1; i++) {
            if ( Integer.compare( ((CardController) this.get(i)).model.rank.value,  ((CardController) this.get(i+1)).model.rank.value - 1 ) == 0)
                counter += 1;
            else {
                if (counter >= amount) {
                    fittingCardsSet = new CardList<CardController>();
                    for (int j=0; j < counter; j++)
                        fittingCardsSet.add( ((CardController) this.get(i - j)) );

                    fittingCards.add(fittingCardsSet);
                }

                counter = 1;
            }
        }

        if (counter >= amount) {
            fittingCardsSet = new CardList<CardController>();
            for (int j=0; j < counter; j++)
                fittingCardsSet.add( ((CardController) this.get(this.size() - 1 - j)) );

            fittingCards.add(fittingCardsSet);
        }

        return fittingCards;
    }

    public CardList<CardList<CardController>> getStraightsOffByOne(int amount){
        CardList<CardList<CardController>> fittingCards = new CardList<CardList<CardController>>();

        fittingCards.addAll(this.getSameSuit("spades").getStraightsOffByOneIntermediate(amount));
        fittingCards.addAll(this.getSameSuit("clubs").getStraightsOffByOneIntermediate(amount));
        fittingCards.addAll(this.getSameSuit("diamonds").getStraightsOffByOneIntermediate(amount));
        fittingCards.addAll(this.getSameSuit("hearts").getStraightsOffByOneIntermediate(amount));

        return fittingCards;
    }

    public CardList<CardList<CardController>> getStraightsOffByOneIntermediate(int amount) {
        CardList<CardList<CardController>> fittingCards = new CardList<CardList<CardController>>();
        CardList<CardController> fittingCardsSet = new CardList<CardController>();

        int counter = 1;
        int plusTwoCounter = 0;


        for (int i=0; i < this.size() - 1; i++) {
            if ( Integer.compare( ((CardController) this.get(i)).model.rank.value,  ((CardController) this.get(i+1)).model.rank.value - 1 ) == 0)
                counter += 1;
            else if ( Integer.compare( ((CardController) this.get(i)).model.rank.value,  ((CardController) this.get(i+1)).model.rank.value - 2 ) == 0) {
                plusTwoCounter += 1;
                if (plusTwoCounter < 2)
                    counter += 1;
                else {
                    if (counter >= amount - 1) {
                        fittingCardsSet = new CardList<CardController>();
                        for (int j=0; j < counter; j++)
                            fittingCardsSet.add( ((CardController) this.get(i - j)) );
                        fittingCards.add(fittingCardsSet);
                    }
                    counter = 1;
                    plusTwoCounter = 1;
                }
            }
            else
            if (counter >= amount - 1) {
                fittingCardsSet = new CardList<CardController>();
                for (int j=0; j < counter; j++)
                    fittingCardsSet.add( ((CardController) this.get(i - j)) );
                fittingCards.add(fittingCardsSet);

                counter = 1;
                plusTwoCounter = 1;
            }
        }
        if (counter >= amount - 1) {
            fittingCardsSet = new CardList<CardController>();
            for (int j=0; j < counter; j++)
                fittingCardsSet.add( ((CardController) this.get(this.size() - 1 - j)) );
            fittingCards.add(fittingCardsSet);
        }

        return fittingCards;
    }


    public CardList<CardController> getSameSuit(CardController CardController){
        CardList<CardController> fittingCards = new CardList<CardController>();

        for (int i=0; i < this.size(); i++)
            if (( (CardController) this.get(i)).model.suit.equals(CardController.model.suit))
                fittingCards.add((CardController) this.get(i));

        return fittingCards;
    }

    public CardList<CardController> getSameSuit(String suit){
        CardList<CardController> fittingCards = new CardList<CardController>();

        for (int i=0; i < this.size(); i++)
            if (( (CardController) this.get(i)).model.suit.equals(suit))
                fittingCards.add((CardController) this.get(i));

        return fittingCards;
    }

    public CardList<CardList<CardController>> getAvailableAfterSelection(CardList<CardController> CardControllers){
        CardList<CardList<CardController>> fittingCards = new CardList<CardList<CardController>>();

        for (int i=0; i < this.size(); i++)
            if ( ((CardList<CardController>) this.get(i)).containsAll(CardControllers))
                fittingCards.add(((CardList<CardController>) this.get(i)));

        if (CardControllers.isEmpty())
        	return (CardList<CardList<CardController>>) this;
  
        else
        	return fittingCards;
    }

    public CardList<CardList<CardController>> added (CardList<CardList<CardController>> CardControllers){
        CardList<CardList<CardController>> fittingCards = new CardList<CardList<CardController>>();

        fittingCards.addAll((Collection<? extends CardList<CardController>>) this);
        fittingCards.addAll(CardControllers);

        return fittingCards;

    }
    
    @Override
    public Object clone() {
    	CardList<E> c = new CardList<E>();
    	c.addAll(this);
    	return c;
    }


}
