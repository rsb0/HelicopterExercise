package com.mygdx.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.Helicopter;
import com.mygdx.game.sprites.Heli;
import com.mygdx.game.sprites.PongBall;
import com.mygdx.game.sprites.PongLeftPaddle;
import com.mygdx.game.sprites.PongRightPaddle;

public class Pong_PlayState extends State{
    private PongRightPaddle rightPaddle;
    private PongLeftPaddle leftPaddle;
    private PongBall ball;
    private BitmapFont scoreOutput;
    private Texture medianDevider;
    private Texture background;

    public Pong_PlayState(GameStateManager gsm){
        super(gsm);
        ball = new PongBall();
        rightPaddle = new PongRightPaddle(ball);
        leftPaddle = new PongLeftPaddle(ball);
        scoreOutput = new BitmapFont();
        medianDevider = new Texture("C:\\Users\\Rune\\Documents\\skole\\v2019\\programvarearkitektur\\oving1\\Source Codes\\Helicopter\\android\\assets\\Drawing.png");
        background = new Texture("C:\\Users\\Rune\\Documents\\skole\\v2019\\programvarearkitektur\\oving1\\Source Codes\\Helicopter\\android\\assets\\PongBackground.png");
    }

    @Override
    public void upDate(float dt) {
        if(Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)){
            gsm.set(new Pong_MenuState(gsm));
        }
        ball.upDate(dt);

        //Test to see if either side has a winning score
        if(ball.getLeftScore() == 21 || ball.getRightScore() == 21){
            //pop current state and push gameOver-state on top of GameStateManager's state-stack
            gsm.set(new Pong_EndState(gsm, ball.getRightScore(), ball.getLeftScore()));
        }
        //update paddles and ball
        rightPaddle.manualUpdate(dt);
        leftPaddle.autoUpdate(dt);
        ball.collision(rightPaddle.getBounds(), leftPaddle.getBounds());
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.begin();
        sb.draw(background, 0,0,Helicopter.getScreenWidth(), Helicopter.getScreenHeight());
        //Draw vertical line in middle of screen
        sb.draw(medianDevider,Helicopter.getScreenWidth() / 2,0,30, Helicopter.getScreenHeight() - 50);

        //draw current score
        scoreOutput.draw(sb,
                "       score:\n" + ball.getLeftScore() + "                   " + ball.getRightScore(),
                (Helicopter.getScreenWidth() / 2) - 30,Helicopter.getScreenHeight() - 20);

        //draw ball and paddles
        sb.draw(ball.getBallTexture(), ball.getPosition().x, ball.getPosition().y);
        sb.draw(rightPaddle.getTexture(), rightPaddle.getPosition().x, rightPaddle.getPosition().y);
        sb.draw(leftPaddle.getTexture(), leftPaddle.getPosition().x, leftPaddle.getPosition().y);
        sb.end();
    }

    @Override
    public void dispose() {
        medianDevider.dispose();
        ball.dispose();
        leftPaddle.dispose();
        rightPaddle.dispose();
    }
}
