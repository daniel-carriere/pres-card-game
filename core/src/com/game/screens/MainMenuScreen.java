package com.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.game.elements.Button;

public class MainMenuScreen implements Screen{
    final PresCardGame game;
    
    private OrthographicCamera camera;
    private Viewport viewport;
    
    private Texture backgroundImage;
    private Sprite background;
    
    private Button button1;

    private Vector3 touchPos;

    public MainMenuScreen(final PresCardGame game){
        this.game = game;
        
        float aspectRatio = (float) Gdx.graphics.getWidth() / (float) Gdx.graphics.getHeight();
        camera = new OrthographicCamera();
        viewport = new FillViewport(PresCardGame.GAME_HEIGHT * aspectRatio, PresCardGame.GAME_HEIGHT, camera);
        viewport.apply();
        camera.position.set(PresCardGame.GAME_WIDTH/2, PresCardGame.GAME_HEIGHT/2, 0);

        touchPos = new Vector3();

        backgroundImage = new Texture(Gdx.files.internal("background.png"));
        background = new Sprite(backgroundImage);
        background.setPosition(0, 0);
        background.setSize(PresCardGame.GAME_WIDTH, PresCardGame.GAME_HEIGHT);
        
        button1 = new Button(2200 / 2 - (Button.width /2), 375);
   
        Gdx.input.setInputProcessor(new InputAdapter(){
            @Override
            public boolean touchDown(int screenX, int screenY, int pointer, int button) {
                camera.unproject(touchPos.set(screenX, screenY, 0));
                
                if(button1.button.getBoundingRectangle().contains(touchPos.x, touchPos.y)) {
                	button1.select();
                }

                return true;
            }
            @Override
            public boolean touchUp(int screenX, int screenY, int pointer, int button) {
                camera.unproject(touchPos.set(screenX, screenY, 0));

                if(button1.button.getBoundingRectangle().contains(touchPos.x, touchPos.y))
                    game.setScreen(new GameScreen(game));
                else
                	button1.deSelect();
                
                return true;
            }
        });

    }

    @Override
    public void render(float delta){
    	
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();   
        game.batch.setProjectionMatrix(camera.combined);
    
        game.batch.begin();
        
        background.draw(game.batch);
        button1.draw(game.batch);
        
        game.batch.end();     
    }

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
        button1.dispose();
        backgroundImage.dispose();
    }
}
