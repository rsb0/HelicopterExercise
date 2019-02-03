package com.mygdx.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.Helicopter;

public class Pong_MenuState extends State{
    private Texture startScreen;
    private BitmapFont font;

    public Pong_MenuState(GameStateManager gsm){
        super(gsm);
        startScreen = new Texture("C:\\Users\\Rune\\Documents\\skole\\v2019\\programvarearkitektur\\oving1\\Source Codes\\Helicopter\\android\\assets\\MenuScreen.png");
        font = new BitmapFont();
    }

    //check for input, pop current state and push a PlayState to top of GameStateManager's state-stack
    public void upDate(float dt){
        if(Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)){
            gsm.set(new MenuState(gsm));
        }
        if(Gdx.input.isKeyJustPressed(Input.Keys.ENTER)){
            gsm.set(new Pong_PlayState(gsm));
        }
    }
    public void render(SpriteBatch sb){
        sb.begin();
        sb.draw(startScreen, 0,0, 1080, 720);
        font.draw(sb, "Press ENTER to start", (Helicopter.getScreenWidth() / 2) - 80,
                Helicopter.getScreenHeight() / 4);
        sb.end();
    }
    public void dispose(){
        startScreen.dispose();
    }
}
