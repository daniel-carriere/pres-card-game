package com.card.elements;

public class Rank {

    public int value;
    public String name;

    private String[] names = {"3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A", "2", "Joker"};

    public Rank(int value) {
        this.value = value;
        if (value > -1)
        	name = names[value];
        else
        	name = "-1";
    }

}
