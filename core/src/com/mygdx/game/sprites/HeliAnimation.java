package com.mygdx.game.sprites;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.Helicopter;

import java.util.Random;

/*----This is the class used in task 1 and 2 to update, animate and render helicopter on screen----
Keeps an Array or TextureRegions. Loops through the Array of TextureRegion and renders the current
frame. Frames are switched every 100 ms to create animation effect.
*/
public class HeliAnimation {
    private int SPEED;

    private Array<TextureRegion> frames;
    private float maxFrameTime, currentFrameTime;

    /*
    frameCount = number of frames in TextureRegion.
    frame = integer value telling which frame in TextureRegion is currently being rendered.
    regionWidth = width of one frame = (width of whole TextureRegion) / frameCount
    regionHeight = height of textureRegion*/
    private int frameCount, frame, regionWidth, regionHeight;

    private Vector2 position;
    private boolean rightHeading = false, upHeading = true;
    private float changeDirectionCountdown;
    private Rectangle bounds;
    private Random rand;


    /*constructor takes in:
    - TextureRegion
    - total number of frames in TextureRegion.
    - cycleTime = total time it takes to cycle through all frames in TextureRegion.
    - x and y coordinates of where the texture will be rendered on the first call
    to render-method*/
    public HeliAnimation(TextureRegion region, int frameCount, float cycleTime, int startX, int startY){
        frames = new Array<TextureRegion>();
        regionWidth = region.getRegionWidth() / frameCount;
        regionHeight = region.getRegionHeight();

        //add frames to Array
        for(int i = 0; i< frameCount; i++){
            frames.add(new TextureRegion(region, i * regionWidth, 0, regionWidth, regionHeight));
        }

        this.frameCount = frameCount;   //set total number of frames in TextureRegion
        maxFrameTime = cycleTime / frameCount;

        //set current frame number. 0 being leftmost frame in TextureRegion, 3 being rightmost
        frame = 0;
        position = new Vector2(startX, startY);

        //set Rectangle "bounds" around Texture for collision detection
        bounds = new Rectangle(startX, startY, regionWidth, regionHeight);

        /*Set countdown timer used to change helicopters heading after a random time-period
        between 0 and 5 seconds, and initial speed to 20*/
        rand = new Random();
        SPEED = 20;
        changeDirectionCountdown = rand.nextFloat() * 5;
    }

    /*Update method. Takes in dt = time passed between start of last call to render method in the
    HelicopterPart3_2 class, and current call to render.
    Takes in boolean value telling whether a collision with another helicopters was
    detected on the last call to render.
     */
    public void update(float dt, boolean collision){
        currentFrameTime += dt;
        //check if current frame has been rendered for more than 100ms. If so, change to next frame.
        if(currentFrameTime > maxFrameTime){
            frame++;
            currentFrameTime = 0;
        }
        if(frame >= frameCount){    //Cycle to first frame if previous frame was the last frame
            frame = 0;
        }

        /*If a collision has been detected, the heading in x- and y-direction is reversed.
        The texture is moved 50 pixels back in x and y direction*/
        if (collision) {
            if(upHeading){
                position.sub(0,50);
            }else{
                position.add(0,50);
            }
            if(rightHeading){
                position.sub(50, 0);
            }else{
                position.add(50,0);
            }
            upHeading = !upHeading;
            rightHeading = !rightHeading;
        }

        //randomly change speed, and the heading in x- and y-direction if countdown timer reaches 0.
        changeDirectionCountdown -= dt;
        if(changeDirectionCountdown <= 0) {
            boolean random1 = rand.nextBoolean();
            if (random1) {
                rightHeading = !rightHeading;
            }
            boolean random2 = rand.nextBoolean();
            if (random2) {
                upHeading = !upHeading;
            }
            float time = rand.nextFloat();
            //reset countdown timer to random number between 0 and 10
            changeDirectionCountdown = (time * 10);
            SPEED = rand.nextInt(500) + 100;    //set new random speed between 100 and 600
        }

        //update position, check if position is past any edge of the screen, and if so change heading
        if(rightHeading) {
            position.add(SPEED * dt, 0);
            if(position.x > Helicopter.getScreenWidth() - regionWidth){
                position.x = Helicopter.WIDTH - regionWidth;
                rightHeading = false;
            }
        }else{
            position.sub(SPEED * dt, 0);
            if(position.x < 0){
                position.x = 0;
                rightHeading = true;
            }
        }
        if(upHeading){
            position.add(0,SPEED * dt);
            if(position.y >= Helicopter.getScreenHeight() - regionHeight){
                position.y = Helicopter.getScreenHeight() - regionHeight;
                upHeading = false;
            }
        }else {
            position.sub(0,SPEED * dt);
            if(position.y <= 0){
                position.y = 0;
                upHeading = true;
            }
        }

        //Set Rectangle bounds according to the new position
        bounds.setPosition(position.x, position.y);
    }

    public int getRegionWidth(){
        return regionWidth;
    }

    public int getRegionHeight(){
        return regionHeight;
    }

    public TextureRegion getFrame() {
        return frames.get(frame);
    }

    public Vector2 getPosition(){
        return position;
    }

    public Rectangle getBounds(){
        return bounds;
    }

    public boolean isRightHeading() {
        return rightHeading;
    }

}

