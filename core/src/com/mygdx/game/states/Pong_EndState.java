package com.mygdx.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Pong_EndState extends State{
    private Texture gameOverTexture;
    private BitmapFont endOutput;
    private String winner;
    private int scoreRight;
    private int scoreLeft;
    private GameStateManager gsm;

    public Pong_EndState(int scoreRight, int scoreLeft) {
        gsm = GameStateManager.getInstance();

        gameOverTexture = new Texture("C:\\Users\\Rune\\Documents\\skole\\v2019\\" +
                "programvarearkitektur\\oving1\\Source Codes\\Helicopter\\android\\assets\\gameover.png");
        this.scoreLeft = scoreLeft;
        this.scoreRight = scoreRight;
        endOutput = new BitmapFont();
        if(scoreRight == 21){
            winner = "Right Side wins!";
        }
        if(scoreLeft == 21){
            winner = "Left Side Wins!";
        }
    }
    public void upDate(float dt){
        if(Gdx.input.isKeyJustPressed(Input.Keys.ENTER)){
            gsm.set(new MenuState());
        }
    }
    public void render(SpriteBatch sb){
        sb.begin();
        sb.draw(gameOverTexture, 0,0,1080,720);

        //draw final score on screen.
        endOutput.draw(sb, "score left side: " + scoreLeft +
                        ".\nscore right side: " + scoreRight +
                        ".\n" + winner + "\nPress ENTER to play again",
                200, 580);
        sb.end();
    }

    public void dispose(){
        gameOverTexture.dispose();
    }
}

