package com.mygdx.game.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.Helicopter;
import java.lang.Math;

public class PongBall {
    //balls speed
    private static final int SPEED = 300;

    private int SCREEN_WIDTH;
    private int SCREEN_HEIGHT;
    private Texture ballTexture;
    private Vector2 position;
    private boolean upHeading = true;
    private boolean rightHeading = true;
    private Rectangle bounds;   //Rectangle class used to detect collision with paddles
    private Vector2 center = new Vector2(0,0);

    /*int values used to keep track of how many times the ball has crossed the left or right edge
    of screen*/
    private int leftScore;
    private int rightScore;

    //The angle relative to the x-axis, with which the ball is moving
    private double angle;


    public PongBall(){
        ballTexture = new Texture("C:\\Users\\Rune\\Documents\\skole\\v2019\\programvarearkitektur\\oving1\\Source Codes\\Helicopter\\android\\assets\\ball.png");
        position = new Vector2(Helicopter.getScreenWidth() / 2, Helicopter.getScreenHeight() / 2);
        bounds = new Rectangle(position.x, position.y, ballTexture.getWidth(),ballTexture.getHeight());
        rightScore = 0;
        leftScore = 0;
        angle = (Math.PI) /4;
    }


    public void upDate(float dt){

        //If the ball is heading up, add to the y-coordinate of the balls position
        if(upHeading){
            position.add(0, (float)Math.sin(angle) * SPEED * dt);
            //Check if outside top edge of screen, and if so, change heading
            if(position.y >= Helicopter.getScreenHeight() - ballTexture.getHeight()){
                position.y = Helicopter.getScreenHeight() - ballTexture.getHeight();
                upHeading = false;
            }
        }
        //If the ball is heading down, subtract from the y-coordinate of the balls position
        else{
            position.sub(0, (float)Math.sin(angle) * SPEED * dt);
            //Check if outside bottom edge of screen, and if so, change heading:
            if(position.y <= 0){
                position.y = 0;
                upHeading = true;
            }
        }
        //If the ball is heading right, add to the x-coordinate of the balls position
        if(rightHeading){
            position.add((float)Math.cos(angle) * SPEED * dt, 0);
            //Check if outside right edge of screen
            if(position.x >= Helicopter.getScreenWidth() - ballTexture.getWidth()){
                //reset ball to center of screen
                position.set(Helicopter.getScreenWidth() / 2, Helicopter.getScreenHeight() / 2);
                leftScore += 1;             //add 1 to left side's score
                upHeading = true;
            }
        }
        //If the ball is heading left, subtract from the x-coordinate of the balls position
        else{
            position.sub((float)Math.cos(angle) * SPEED * dt, 0);
            //check if outside left edge of screen
            if(position.x <= 0){
                //reset ball to center of screen
                position.set(Helicopter.getScreenWidth(), Helicopter.getScreenHeight());
                rightScore += 1;            //add 1 to right side's score
                rightHeading = true;
                upHeading = true;
            }
        }

        //set Rectangle to balls current position
        bounds.setPosition(position.x, position.y);

    }

    /*check for collision with right or left paddle. If collision is detected, change heading
    in x-direction*/
    public void collision(PongRightPaddle rightPaddle, PongLeftPaddle leftPaddle){
        if(bounds.overlaps(rightPaddle.getBounds())){
            position.set(position.x - 10, position.y);
            rightHeading = !rightHeading;
            angle = Math.atan(Math.abs(rightPaddle.getCenter().y - getBallCenter().y) /
                    (rightPaddle.getCenter().x - getBallCenter().x));
        }
        if(bounds.overlaps(leftPaddle.getBounds())){
            position.set(position.x + 10, position.y);
            rightHeading = !rightHeading;
            angle = Math.atan(Math.abs(leftPaddle.getCenter().y - getBallCenter().y) /
                    (leftPaddle.getCenter().x - getBallCenter().x));
        }
    }

    public int getLeftScore(){ return leftScore; }

    public int getRightScore(){ return rightScore; }

    public void setBalllCenter(){
        center.set(getPosition().x + getBallTexture().getWidth() / 2, getPosition().y +
                getBallTexture().getHeight() / 2);
    }

    public Vector2 getBallCenter(){
        setBalllCenter();
        return center;
    }

    public Texture getBallTexture(){ return ballTexture; }

    public Vector2 getPosition(){ return position; }

    public void dispose(){ ballTexture.dispose();}
}
