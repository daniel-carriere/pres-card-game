package com.game.screens;

import java.util.*;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.card.elements.*;
import com.card.elements.card.Card;
import com.card.elements.card.CardController;
import com.card.elements.card.LargeCardView;
import com.game.elements.*;
import com.game.elements.Opponents.UserOpponentView;
import com.game.elements.user.User;
import com.game.elements.user.UserView;
import com.game.elements.userField.Middle;
import com.game.elements.userField.MiddleController;
import com.game.elements.userField.UserMiddleView;


public class GameScreen implements Screen{	
    final PresCardGame game;
    
    //View and input
    
    private OrthographicCamera camera;
    private Viewport viewport;

    private Vector3 touchPos;
    
    //Screen elements
    
    private Sprite background;
    private Button playButton;
    
    private Gameplay gameplay;
    
    //Temp
    
    //private Card card = new Card(11, "spades");
    //private CardView cardView = new CardView(card, 50, 500, true);
    //private CardController cardController = new CardController(card, cardView);
    
    
    public GameScreen(final PresCardGame game){
        this.game = game;
        
        //View
      
        float aspectRatio = (float) Gdx.graphics.getWidth() / (float) Gdx.graphics.getHeight();
        camera = new OrthographicCamera();
        viewport = new FillViewport(PresCardGame.GAME_HEIGHT * aspectRatio, PresCardGame.GAME_HEIGHT, camera);
        viewport.apply();
        camera.position.set(PresCardGame.GAME_WIDTH/2, PresCardGame.GAME_HEIGHT/2, 0);

        //Elements
        
        background = new Sprite(new Texture(Gdx.files.internal("background.png")));
        background.setPosition(0, 0);
        background.setSize(PresCardGame.GAME_WIDTH, PresCardGame.GAME_HEIGHT);
        
        playButton = new Button(2200 / 2 - (Button.width /2) + 600, 465);
        
        gameplay = new Gameplay(3, this);

        //Input
        
        touchPos = new Vector3();
        
        Gdx.input.setInputProcessor(new InputAdapter(){
            @Override
            public boolean touchDown(int screenX, int screenY, int pointer, int button) {
            	camera.unproject(touchPos.set(screenX, screenY, 0));
            	
            	if(playButton.button.getBoundingRectangle().contains(touchPos.x, touchPos.y))
                	playButton.select();
                
            	
                return true;
            }
            @Override
            public boolean touchUp(int screenX, int screenY, int pointer, int button) {
            	camera.unproject(touchPos.set(screenX, screenY, 0));
            	
            	if(playButton.button.getBoundingRectangle().contains(touchPos.x, touchPos.y)) {
            		gameplay.next();	
            	}
            	playButton.deSelect();
                	
            	
                return true;
            }

        });
    }
    
    
    @Override
    public void render(float delta){
    	
    	gameplay.update();

        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();             
        game.batch.setProjectionMatrix(camera.combined);

        game.batch.begin();
        
        background.draw(game.batch);
        
        playButton.draw(game.batch);

        gameplay.draw(game.batch);
        
        game.batch.end();
    }
    
    //Overridden Screen functions
    @Override
    public void resize(int width, int height){
    	viewport.update(width, height);
    }

    @Override
    public void show(){ }

    @Override
    public void hide(){ }

    @Override
    public void pause(){ }

    @Override
    public void resume(){ }

    @Override
    public void dispose(){
    }
}
