package com.game.elements;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class Button {
	
	public int x, y;
	public static int width = 551;
	public static int height = 221;
	
	public boolean selected;
	public boolean available;
	
	//Textures
	private Texture buttonImage, buttonImage1, buttonImage2, buttonImage3;
	public Sprite button;
	
	public Button(int x, int y) {
		this.x = x;
		this.y = y;
		
		selected = false;
		available = true;
		
		buttonImage1 = new Texture(Gdx.files.internal("button1.png"));
        buttonImage2 = new Texture(Gdx.files.internal("button1_clicked.png"));
        buttonImage3 = new Texture(Gdx.files.internal("button1_unavailable.png"));
        
        button = new Sprite(buttonImage1);
        button.setPosition(x, y);
	}
	
	public void select() {
		if (available) {
			
			selected = true;
			updateSelectedImage();
		}
	}
	
	public void deSelect() {
		if (available) {
			selected = false;
			updateSelectedImage();
		}
	}
	
	public void makeAvailable() {
		available = true;
		updateAvailableImage();
	}
	
	public void makeUnAvailable() {
		available = false;
		updateAvailableImage();
	}
	
	public void updateSelectedImage() {
		if (selected)
			button.setTexture(buttonImage2);
		else
			button.setTexture(buttonImage1);
	}
	
	public void updateAvailableImage() {
		if (available)
			button.setTexture(buttonImage1);
		else
			button.setTexture(buttonImage3);
	}
	
	 public void draw(Batch batch) {
	    	
	        button.draw(batch);
	 }
	 
	 public void dispose() {
		 buttonImage1.dispose();
		 buttonImage2.dispose();
		 buttonImage.dispose();
	 }

}
