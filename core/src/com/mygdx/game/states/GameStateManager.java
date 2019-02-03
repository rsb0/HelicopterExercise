package com.mygdx.game.states;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.Stack;

public class GameStateManager {
    private Stack<State> states;

    public GameStateManager(){
        states = new Stack<State>();
    }

    //add a new state to the top of the state-stack
    public void push(State state){
        states.push(state);
    }

    /*pops the top state off the state-stack and disposes of it. Takes in a new state to push to
    the top of the state-stack*/
    public void set(State state){
        states.pop().dispose();
        states.push(state);
    }

    //call the update method of the state on top of the state-stack
    public void update(float dt){ states.peek().upDate(dt); }

    //calls the render method of the state on top of the state-stack
    public void render(SpriteBatch sb){ states.peek().render(sb);}

}
