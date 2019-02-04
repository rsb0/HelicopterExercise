package com.mygdx.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.Helicopter;

public class MenuState extends State{

    private Texture task1;
    private Texture task2;
    private Texture task3;
    private Texture pong;
    private Texture task1Highlight;
    private Texture task2Highlight;
    private Texture task3Highlight;
    private Texture pongHighlight;
    private int task;
    private GameStateManager gsm;


    public MenuState() {
        task1 = new Texture("C:\\Users\\Rune\\Documents\\skole\\v2019\\programvarearkitektur\\oving1\\Source Codes\\Helicopter\\android\\assets\\Task1.png");
        task2 = new Texture("C:\\Users\\Rune\\Documents\\skole\\v2019\\programvarearkitektur\\oving1\\Source Codes\\Helicopter\\android\\assets\\Task2.png");
        task3 = new Texture("C:\\Users\\Rune\\Documents\\skole\\v2019\\programvarearkitektur\\oving1\\Source Codes\\Helicopter\\android\\assets\\Task3.png");
        pong = new Texture("C:\\Users\\Rune\\Documents\\skole\\v2019\\programvarearkitektur\\oving1\\Source Codes\\Helicopter\\android\\assets\\Pong.png");
        task1Highlight = new Texture("C:\\Users\\Rune\\Documents\\skole\\v2019\\programvarearkitektur\\oving1\\Source Codes\\Helicopter\\android\\assets\\Task1_highlight.png");
        task2Highlight = new Texture("C:\\Users\\Rune\\Documents\\skole\\v2019\\programvarearkitektur\\oving1\\Source Codes\\Helicopter\\android\\assets\\Task2_highlight.png");
        task3Highlight = new Texture("C:\\Users\\Rune\\Documents\\skole\\v2019\\programvarearkitektur\\oving1\\Source Codes\\Helicopter\\android\\assets\\Task3_highlight.png");
        pongHighlight = new Texture("C:\\Users\\Rune\\Documents\\skole\\v2019\\programvarearkitektur\\oving1\\Source Codes\\Helicopter\\android\\assets\\PongHighlight.png");
        task = 1;
        gsm = GameStateManager.getInstance();
    }

    @Override
    public void upDate(float dt) {
        if(task == 1){
            if(Gdx.input.isKeyJustPressed(Input.Keys.ENTER)){
                gsm.set(new Task1());
            }

           if(Gdx.input.isKeyJustPressed(Input.Keys.LEFT)){
               task = 4;

           } else if(Gdx.input.isKeyJustPressed(Input.Keys.RIGHT)) {
               task = 2;
           }
       }
       else if(task == 2){
            if(Gdx.input.isKeyJustPressed(Input.Keys.ENTER)){
                gsm.set(new Task2());
            }

            if(Gdx.input.isKeyJustPressed(Input.Keys.LEFT)){
                task = 1;

            } else if(Gdx.input.isKeyJustPressed(Input.Keys.RIGHT)) {
                task = 3;
            }
        }
        else if(task == 3){
            if(Gdx.input.isKeyJustPressed(Input.Keys.ENTER)){
                gsm.set(new Task3());
            }

            if(Gdx.input.isKeyJustPressed(Input.Keys.LEFT)){
                task = 2;

            } else if(Gdx.input.isKeyJustPressed(Input.Keys.RIGHT)) {
                task = 4;
            }
        }
        else if(task == 4){
            if(Gdx.input.isKeyJustPressed(Input.Keys.ENTER)){
                gsm.set(new Pong_MenuState());
            }

            if(Gdx.input.isKeyJustPressed(Input.Keys.LEFT)){
                task = 3;

            } else if(Gdx.input.isKeyJustPressed(Input.Keys.RIGHT)) {
                task = 1;
            }
        }



    }

    @Override
    public void render(SpriteBatch sb) {
        sb.begin();

        if (task == 1) {
            sb.draw(task1Highlight,0,
                    (Helicopter.getScreenHeight() / 2) - (task1Highlight.getHeight() / 2),
                    task1Highlight.getWidth(), task1Highlight.getHeight());
            sb.draw(task2, ((2 * Helicopter.getScreenWidth()) / 4) - task2.getWidth(),
                    (Helicopter.getScreenHeight() / 2) - (task2.getHeight() / 2));
            sb.draw(task3, (3 * Helicopter.getScreenWidth() / 4) - task3.getWidth(),
                    (Helicopter.getScreenHeight() / 2) - (task3.getHeight() / 2));
            sb.draw(pong, Helicopter.getScreenWidth() - pong.getWidth(),
                    (Helicopter.getScreenHeight() / 2) - (pong.getHeight() / 2),
                    pong.getWidth(), pong.getHeight());
        }
        if (task == 2) {
            sb.draw(task1, (Helicopter.getScreenWidth() / 4) - (task1.getWidth()),
                    (Helicopter.getScreenHeight() / 2) - (task1.getHeight() / 2));
            sb.draw(task2Highlight, ((2 * Helicopter.getScreenWidth()) / 4) - task2Highlight.getWidth(),
                    (Helicopter.getScreenHeight() / 2) - (task2Highlight.getHeight() / 2),
                    task2Highlight.getWidth(), task2Highlight.getHeight());
            sb.draw(task3, ((3 * Helicopter.getScreenWidth()) / 4) - task3.getWidth(),
                    (Helicopter.getScreenHeight() / 2) - (task3.getHeight() / 2),
                    task3.getWidth(), task3.getHeight());
            sb.draw(pong, Helicopter.getScreenWidth() - pong.getWidth(),
                    (Helicopter.getScreenHeight() / 2) - (pong.getHeight() / 2),
                    pong.getWidth(), pong.getHeight());
        }
        if (task == 3) {
            sb.draw(task1, (Helicopter.getScreenWidth() / 4) - (task1.getWidth()),
                    (Helicopter.getScreenHeight() / 2) - (task1.getHeight() / 2),
                    task1.getWidth(), task1.getHeight());
            sb.draw(task2, ((2 * Helicopter.getScreenWidth()) / 4) - task2.getWidth(),
                    (Helicopter.getScreenHeight() / 2) - (task2.getHeight() / 2),
                    task2.getWidth(), task2.getHeight());
            sb.draw(task3Highlight, ((3 * Helicopter.getScreenWidth()) / 4) - task3Highlight.getWidth(),
                    (Helicopter.getScreenHeight() / 2) - (task3Highlight.getHeight() / 2),
                    task3Highlight.getWidth(), task3Highlight.getHeight());
            sb.draw(pong, Helicopter.getScreenWidth() - pong.getWidth(),
                    (Helicopter.getScreenHeight() / 2) - (pong.getHeight() / 2),
                    pong.getWidth(), pong.getHeight());
        }
        if (task == 4) {
            sb.draw(task1, Helicopter.getScreenWidth() / 4 - task1.getWidth(),
                    (Helicopter.getScreenHeight() / 2) - (task1.getHeight() / 2),
                    task1.getWidth(), task1.getHeight());
            sb.draw(task2, ((2 * Helicopter.getScreenWidth()) / 4) - task2.getWidth(),
                    (Helicopter.getScreenHeight() / 2) - (task2.getHeight() / 2),
                    task2.getWidth(), task2.getHeight());
            sb.draw(task3, ((3 * Helicopter.getScreenWidth()) / 4) - task3.getWidth(),
                    (Helicopter.getScreenHeight() / 2) - (task3.getHeight() / 2),
                    task3.getWidth(), task3.getHeight());
            sb.draw(pongHighlight, Helicopter.getScreenWidth() - pongHighlight.getWidth(),
                    (Helicopter.getScreenHeight() / 2) - (pongHighlight.getHeight() / 2),
                    pongHighlight.getWidth(), pongHighlight.getHeight());
        }
        sb.end();
    }

    @Override
    public void dispose() {
        task1.dispose();
        task2.dispose();
        task3.dispose();
        task1Highlight.dispose();
        task2Highlight.dispose();
        task3Highlight.dispose();
    }
}
