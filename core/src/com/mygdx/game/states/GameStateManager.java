package com.mygdx.game.states;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.Stack;

public class GameStateManager {
    private Stack<State> states;

    //create an instance of GameStateManager
    private static GameStateManager instance = new GameStateManager();

    //private constructor so new instances of GameStateManager cannot be created by other classes
    private GameStateManager(){
        states = new Stack<State>();
    }

    //Global accesspoint for other classes to get the one instance of GameStateManager
    public static GameStateManager getInstance(){
        return instance;
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
