package com.card.elements.card;

import java.util.*;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

public class LargeCardView extends CardView {
	
	//Value and colour
	private int suitNum;
    private int suitColourNum;
    
    private int selectionPadding;
    
    //Orientation
    public orient orientState;
    public static enum orient {FRONT, TOBACK, BACK, TOFRONT};
    
    //Textures
    private Texture cardImage, faceImage, cardImageRegular, cardImageAvailable, cardImageBack;
    public Sprite cardSprite, rankSprite, suitSprite, faceSprite;
    
    private Texture suitImages, rankImages;
    private TextureRegion suitImage, rankImage;
    
    private Texture animationImages;
    private List<TextureRegion> frames;
    private TextureRegion currentFrame;
    
    private int frameCounter;
    
    //Constants
    public static int WIDTH = 168;
    
	public LargeCardView(Card card, boolean isFront) {	
		super(card);
		
		selectionPadding = 0;
		
        if (isFront)
        	orientState = orient.FRONT;
        else
        	orientState = orient.BACK;
        
        frameCounter = 0;
        
        if (model.suit.equals("spades")) {
        	suitNum = 0;
            suitColourNum = 0;
        }
        if (model.suit.equals("clubs")) {
        	suitNum = 1;
            suitColourNum = 0;
        }
        if (model.suit.equals("diamonds")) {
        	suitNum = 2;
            suitColourNum = 1;
        }
        if (model.suit.equals("hearts")) {
        	suitNum = 3;
            suitColourNum = 1;
        }
        
        cardImageRegular = new Texture(Gdx.files.internal("card_base.png"));
        cardImageAvailable = new Texture(Gdx.files.internal("card_base_available.png"));
        cardImageBack = new Texture(Gdx.files.internal("card_back.png"));
        cardImage = cardImageRegular;
        
        faceImage = new Texture(Gdx.files.internal("card_face.png"));
        
        suitImages = new Texture(Gdx.files.internal("card_suits.png"));
        rankImages = new Texture(Gdx.files.internal("card_ranks.png"));
        
        suitImage = new TextureRegion(suitImages, 54 * suitNum, 0, 54, 54);
        rankImage = new TextureRegion(rankImages, 54 * model.rank.value, 60 * suitColourNum , 54, 54);
        
        suitSprite = new Sprite(suitImage);
        rankSprite = new Sprite(rankImage);
        
        faceSprite = new Sprite(faceImage);

        cardSprite = new Sprite(cardImage);
        
        animationImages = new Texture(Gdx.files.internal("card_sprite_sheet.png"));
        
        frames = new ArrayList<TextureRegion>();
        
        for (int i=0; i < 7; i++) {
        	frames.add(new TextureRegion(animationImages, i*168, 0, 168, 264));
        }
        
        currentFrame = frames.get(0);
 
        if (model.rank.value >= 8 && model.rank.value <= 10)
        	faceSprite.setAlpha(1f);
        else
        	faceSprite.setAlpha(0f);
        
        suitSprite.setPosition(x + 20	, y + 125 	+ selectionPadding);
        rankSprite.setPosition(x + 20	, y + 190 	+ selectionPadding);
        cardSprite.setPosition(x		, y 		+ selectionPadding);
        faceSprite.setPosition(x		, y 		+ selectionPadding);
        
	}
	
	public void setDetailsVisible(boolean bool) {
		if (bool) {
			suitSprite.setAlpha(1f);
			rankSprite.setAlpha(1f);
			if (model.rank.value >= 8 && model.rank.value <= 10)
	        	faceSprite.setAlpha(1f);
	        else
	        	faceSprite.setAlpha(0f);
		}else {
			suitSprite.setAlpha(0f);
			rankSprite.setAlpha(0f);
			faceSprite.setAlpha(0f);
			
		}
			
	}
	
	@Override
	public void flipToFront() {
		frameCounter = -1;
		orientState = orient.TOFRONT;
	}
	
	@Override
	public void flipToBack() {
		frameCounter = frames.size();
		orientState = orient.TOBACK;
		
	}
	
	@Override
	public void update() {
		
		//Available
    	if (model.available)
    		cardImage = cardImageAvailable;
    	else
    		cardImage = cardImageRegular;
    	
    	//Orientation
		if (orientState == orient.TOFRONT) {
			frameCounter++;
			frameCounter = frameCounter % frames.size();
			
			currentFrame = frames.get(frameCounter);
			cardSprite.setRegion(currentFrame);
			
			setDetailsVisible(false);
			
			if (frameCounter == frames.size() -1) {
				orientState = orient.FRONT;
			}
			
		} else if (orientState == orient.TOBACK) {
			frameCounter++;
			frameCounter = frameCounter % frames.size();
			
			currentFrame = frames.get(frames.size() - frameCounter - 1);
			cardSprite.setRegion(currentFrame);
			
			setDetailsVisible(false);
			
			
			
			if (frameCounter == 0) {
				orientState = orient.BACK;
			}
			
		} else if (orientState == orient.FRONT) {
			cardSprite.setRegion(cardImage);
			
			setDetailsVisible(true);
		} else if (orientState == orient.BACK) {
			cardSprite.setRegion(cardImageBack);
			
			setDetailsVisible(false);
		}
		
		
		//Selected
    	if (model.selected)
    		selectionPadding = 40;
    	else
    		selectionPadding = 0;
    	
    	//CardView update
    	super.update();
    	    	
    	//Positioning
    	suitSprite.setPosition(x + 20	, y + 125 	+ selectionPadding);
        rankSprite.setPosition(x + 20	, y + 190 	+ selectionPadding);
        cardSprite.setPosition(x		, y 		+ selectionPadding);
        faceSprite.setPosition(x		, y			+ selectionPadding);
        
    }

	@Override
    public void draw(Batch batch) {
    	
        cardSprite.draw(batch);
        faceSprite.draw(batch);
        rankSprite.draw(batch);
        suitSprite.draw(batch);
            
    }

}
