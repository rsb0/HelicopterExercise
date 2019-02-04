package com.mygdx.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.Helicopter;
import com.mygdx.game.sprites.Heli;

public class Task1 extends State{
    private Heli heli;
    private BitmapFont text;
    private GameStateManager gsm;

    public Task1() {
        heli = new Heli(300,300,300);
        text = new BitmapFont();
        this.gsm = GameStateManager.getInstance();
    }

    @Override
    public void upDate(float dt) {
        if(Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)){
            gsm.set(new MenuState());
        }
        heli.autoUpdate(dt);

    }

    @Override
    public void render(SpriteBatch sb) {
        sb.begin();
        sb.draw(heli.getHeliSprite(), heli.getPosition().x, heli.getPosition().y,
                heli.getTextureWidth() * 0.7f, heli.getTextureHeight() * 0.7f);
        text.draw(sb, "press Esc to exit to menu screen",
                Helicopter.getScreenWidth() - 220, Helicopter.getScreenHeight() - 20);
        sb.end();
    }

    @Override
    public void dispose() {
        heli.dispose();
        text.dispose();
    }
}
