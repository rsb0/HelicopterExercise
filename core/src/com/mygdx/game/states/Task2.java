package com.mygdx.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.Helicopter;
import com.mygdx.game.sprites.Heli;

public class Task2 extends State {

    private Heli heli;
    private BitmapFont pos;
    private BitmapFont text;

    public Task2(GameStateManager gsm) {
        super(gsm);
        heli = new Heli(300, 300,300);
        pos = new BitmapFont();
        text = new BitmapFont();
    }

    @Override
    public void upDate(float dt) {
        if(Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)){
            gsm.set(new MenuState(gsm));
        }
        heli.manualUpdate(dt);
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.begin();
        //draw helicopter on screen
        sb.draw(heli.getHeliSprite(), heli.getPosition().x, heli.getPosition().y,
                heli.getTextureWidth() * 0.7f, heli.getTextureHeight() * 0.7f);

        //draw helicopter's position on left upper corner of screen
        pos.draw(sb, "position:\nx: " + (heli.getPosition().x + heli.getTextureWidth() / 2) +
                        "\ny: " + (heli.getPosition().y + heli.getTextureHeight() / 2),
                0, Helicopter.getScreenHeight() - 20);
        text.draw(sb, "Press Esc to exit to menu screen",
                Helicopter.getScreenWidth() - 220, Helicopter.getScreenHeight() - 20);
        sb.end();
    }

    @Override
    public void dispose() {
        heli.dispose();
        pos.dispose();
        text.dispose();
    }
}
