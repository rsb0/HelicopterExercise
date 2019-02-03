package com.mygdx.game.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.Helicopter;

public class Heli {

    private Texture heliTexture;
    private Sprite heliSprite;
    private int SPEED;
    private Vector2 position;
    private boolean rightHeading;
    private boolean upHeading;

    public Heli(int speed, int startX, int startY){
        this.position = new Vector2(startX, startY);
        this.SPEED = speed;
        heliTexture = new Texture("C:\\Users\\Rune\\Documents\\skole\\v2019\\programvarearkitektur\\oving1\\Source Codes\\Helicopter\\android\\assets\\attackhelicopter.png");
        heliSprite = new Sprite(heliTexture);
        rightHeading = false;
        upHeading = true;
    }

    //update method used for Task1:
    public void autoUpdate(float dt){
        if(rightHeading) {
            position.x += SPEED * Gdx.graphics.getDeltaTime();

            //Check if helicopter has passed the right edge of the screen.
            if(position.x > Helicopter.getScreenWidth() - (heliTexture.getWidth() * 0.7f)){
                //set x-position to right edge of screen
                position.x = Helicopter.getScreenWidth() - (heliTexture.getWidth() * 0.7f);
                rightHeading = false;   //reverse heading in x-direction
                heliSprite.flip(true,false);
            }
        }

        else{
            position.x -= SPEED * dt;
            if (position.x < 0){       //Check if helicopter has passed the left edge of the screen.
                position.x = 0;        //set x-position to left edge of screen
                rightHeading = true;   //Reverse heading in x-direction
                heliSprite.flip(true,false);
            }
        }

        if(upHeading) {
            position.y += SPEED * dt;
            //Check if helicopter has passed the top edge of the screen.
            if(position.y > Helicopter.getScreenHeight() - (heliTexture.getHeight() * 0.7f)) {
                //set y-position to top edge of screen
                position.y = Helicopter.getScreenHeight() - (heliTexture.getHeight() * 0.7f);
                upHeading = false;              //Reverse heading in y-direction
            }
        } else{
            position.y -= SPEED * dt;
            //Check if helicopter has passed the top edge of the screen.
            if(position.y < 0) {
                position.y = 0;              //set y-position to top edge of screen
                upHeading = true;            //Reverse heading in y-direction
            }
        }
    }

    public void manualUpdate(float dt){
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            //Check helicopter's heading
            if (!rightHeading) {
                heliSprite.flip(true, false);
                rightHeading = true;
            }
            position.x += SPEED * dt;
		    /*Check if helicopter has passed right edge of screen, and if so set x-coordinate to
		    right edge of screen*/
            if (position.x > Helicopter.getScreenWidth() - (heliTexture.getWidth() * 0.7f)) {
                position.x = Helicopter.getScreenWidth() - (heliTexture.getWidth() * 0.7f);
            }
        }

        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            if (rightHeading) {
                heliSprite.flip(true, false);
                rightHeading = false;
            }
            position.x -= SPEED * dt;
            if (position.x < 0) {
                position.x = 0;
            }
        }
        if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            position.y += SPEED * dt;
            if (position.y > Helicopter.getScreenHeight() - (heliTexture.getHeight() * 0.7f)) {
                position.y = Helicopter.getScreenHeight() - (heliTexture.getHeight() * 0.7f);
            }
        }
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            position.y -= SPEED * dt;
            if (position.y < 0) {
                position.y = 0;
            }
        }
    }


    public Sprite getHeliSprite(){
        return heliSprite;
    }

    public int getTextureWidth(){
        return heliTexture.getWidth();
    }

    public int getTextureHeight(){
        return heliTexture.getHeight();
    }

    public Vector2 getPosition(){
        return position;
    }

    public void dispose(){
        heliTexture.dispose();
    }

}
