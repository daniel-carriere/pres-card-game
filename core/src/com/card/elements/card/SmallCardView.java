package com.card.elements.card;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.card.elements.card.LargeCardView.orient;

public class SmallCardView extends CardView{
	
	
	//Textures
    private Texture cardImage;
    public Sprite cardSprite;
    
    //Constants
    public static int WIDTH = 108;
    
	public SmallCardView(Card card) {
		super(card);
		
		direction = 0;
		inTransit = false;
		
        cardImage = new Texture(Gdx.files.internal("card_back_small.png"));
        cardSprite = new Sprite(cardImage);
        
        cardSprite.setPosition(this.x, this.y);

	}
	
	@Override
	public void update() {
		
		//CardView update
		super.update();
		
    	//Positioning
        cardSprite.setPosition(this.x, this.y );
       
    }

	@Override
    public void draw(Batch batch) {	
        cardSprite.draw(batch);
    }

}
