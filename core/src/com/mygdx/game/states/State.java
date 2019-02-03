package com.mygdx.game.states;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public abstract class State {
    protected GameStateManager gsm;
    public State(GameStateManager gsm){
        this.gsm = gsm;
    }
    public abstract void upDate(float dt);
    public abstract void render(SpriteBatch sb);
    public abstract void dispose();
}
