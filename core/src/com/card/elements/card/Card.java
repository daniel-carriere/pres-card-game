package com.card.elements.card;

import com.card.elements.Rank;

public class Card implements Comparable<Card>{

    public Rank rank;
    public String suit;
    
    public boolean selected;
    public boolean available;

    public Card(int value, String suit) {
        this.rank = new Rank(value);
        this.suit = suit;
        
    }
    
    public void selectToggle() {
    	if (available)
    		selected = !selected;
    }
    
    public void select() {
    	if (available)
    		selected = true;
    }
    
    public void unSelect() {
    		selected = false;
    }
    
    public void makeAvailable() {
    	available = true;
    }
    
    public void makeUnAvailable() {
    	available = false;
    }
    
    //Comparison

    @Override
    public int compareTo(Card card) {
        return Integer.compare(this.rank.value, card.rank.value);
    }
        
    //To string
    
    public String toString() {
        String out = rank.name + " of " + suit;
        return out;

    }
    
    //Dispose
    
    public void dispose() {
    }


}
