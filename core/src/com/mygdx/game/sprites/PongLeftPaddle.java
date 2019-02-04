package com.mygdx.game.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.Helicopter;

public class PongLeftPaddle {
    private Texture paddleTexture;
    private Vector2 position;
    private Vector2 center = new Vector2(0,0);
    private int SCREEN_HEIGHT;
    private int SCREEN_WIDTH;

    //speed at which the paddle will move up or down. Slightly less than the ball's speed
    private static final int SPEED = 250;
    private Rectangle bounds;   //Rectangle class used to detect collision with ball
    private PongBall ball;


    public PongLeftPaddle(PongBall ball){
        paddleTexture = new Texture("C:\\Users\\Rune\\Documents\\skole\\v2019\\programvarearkitektur\\oving1\\Source Codes\\Helicopter\\android\\assets\\paddle.png");
        position = new Vector2(0, Helicopter.getScreenHeight() / 2 - paddleTexture.getHeight() / 2);
        bounds = new Rectangle(position.x, position.y, paddleTexture.getWidth(), paddleTexture.getHeight());
        SCREEN_HEIGHT = Helicopter.getScreenHeight();
        SCREEN_WIDTH = Helicopter.getScreenWidth();
        this.ball = ball;
    }

    /*Used for manually playing the game. Checks for key input W = UP or S = DOWN,
and moves the paddle up or down accordingly. Not in use in current implementation.
Opens for the possibility of multiplayer game*/
    public void manualUpdate(float dt){
        if(Gdx.input.isKeyPressed(Input.Keys.W)){
            position.add(0, SPEED * dt);
        }
        if(position.y + paddleTexture.getHeight() >= SCREEN_HEIGHT) {
            position.y = SCREEN_HEIGHT - paddleTexture.getHeight();
        }
        if(Gdx.input.isKeyPressed(Input.Keys.S)){
            position.sub(0, SPEED * dt);
        }
        if(position.y <= 0){
            position.y = 0;
        }
        bounds.setY(position.y);
    }

    /*automatically plays the game by keeping track of the balls position and moving up or down
    to keep the centre of the paddle aligned with the current y-position of the ball*/
    public void autoUpdate(float dt){
        /*if the ball's y-coordinate greater than the y-coordinate of the center of the paddle,
        the paddle is moved up*/
        if(ball.getPosition().y < position.y + paddleTexture.getHeight() / 2){
            position.sub(0,SPEED * dt);
            if(position.y <= 0){
                position.y = 0;
            }
        }
        /*if the ball's y-coordinate less than the y-coordinate of the center of the paddle,
        the paddle is moved down*/
        if(ball.getPosition().y > position.y + paddleTexture.getHeight() / 2) {
            position.add(0, SPEED * dt);
            if(position.y >= SCREEN_HEIGHT - paddleTexture.getHeight()){
                position.y = SCREEN_HEIGHT - paddleTexture.getHeight();
            }
        }
        //reset the Rectangle bounds according to the new coordinates.
        bounds.setY(position.y);
    }

    public Texture getTexture(){
        return paddleTexture;
    }

    public Vector2 getPosition(){
        return position;
    }

    private void setCenter(){
        center.set(paddleTexture.getWidth() / 2, getPosition().y + paddleTexture.getHeight() / 2);
    }

    public Vector2 getCenter(){
        setCenter();
        return center;
    }

    public Rectangle getBounds(){
        return bounds;
    }

    public void dispose(){ paddleTexture.dispose();}
}
