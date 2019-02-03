package com.mygdx.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.Helicopter;
import com.mygdx.game.sprites.Heli;
import com.mygdx.game.sprites.HeliAnimation;

public class Task3 extends State{

    private Texture heliSheet;
    private HeliAnimation heli1;
    private HeliAnimation heli2;
    private HeliAnimation heli3;
    private BitmapFont text;

    public Task3(GameStateManager gsm) {
        super(gsm);

        text = new BitmapFont();
        heliSheet = new Texture("C:\\Users\\Rune\\Documents\\skole\\v2019\\programvarearkitektur\\oving1\\Source Codes\\Helicopter\\android\\assets\\HeliSpriteSheet.png");

        //create 3 new helicopters using the Animation class from the sprites-package
        heli1 = new HeliAnimation(new TextureRegion(heliSheet),
                4, 0.4f, 300,300);
        heli2 = new HeliAnimation(new TextureRegion(heliSheet),
                4, 0.4f, 300,20);
        heli3 = new HeliAnimation(new TextureRegion(heliSheet),
                4, 0.4f, 420,420);
    }

    @Override
    public void upDate(float dt) {
        if(Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)){
            gsm.set(new MenuState(gsm));
        }

        //check for collision between heli 1 and 2, and update with collision condition = true
        if (heli1.getBounds().overlaps(heli2.getBounds())){
            heli1.update(dt, true);
            heli2.update(dt, true);
            heli3.update(dt, false);
        }
        //check for collision between heli 1 and 3, and call update with collision condition = true
        else if(heli1.getBounds().overlaps(heli3.getBounds())){
            heli1.update(dt, true);
            heli3.update(dt, true);
            heli2.update(dt, false);
        }
        //check for collision between heli 2 and 3, and call update with collision condition = true
        else if(heli3.getBounds().overlaps(heli2.getBounds())){
            heli3.update(dt, true);
            heli2.update(dt, true);
            heli1.update(dt, false);
        }
        //no collision detected: call update with all collision conditions = false
        else{
            heli1.update(dt, false);
            heli2.update(dt, false);
            heli3.update(dt, false);
        }
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.begin();

        /*Draw helicopters on screen. If the helicopters are heading right on the screen the
		TextureRegion is drawn with negative width to make the texture appear flipped around the
		x-axis*/

        //draw heli1 flipped around x-axis
        if(heli1.isRightHeading()) {
            sb.draw(heli1.getFrame(), heli1.getPosition().x + heli1.getRegionWidth(),
                    heli1.getPosition().y, - heli1.getRegionWidth(), heli1.getRegionHeight());
        }
        //draw heli1 without flipping around x-axis
        else{
            sb.draw(heli1.getFrame(), heli1.getPosition().x, heli1.getPosition().y,
                    heli1.getRegionWidth(), heli1.getRegionHeight());
        }

        //draw heli2 flipped around x-axis
        if(heli2.isRightHeading()) {
            sb.draw(heli2.getFrame(), heli2.getPosition().x + heli2.getRegionWidth(),
                    heli2.getPosition().y, - heli2.getRegionWidth(), heli2.getRegionHeight());
        }
        //draw heli2 without flipping around x-axis
        else{
            sb.draw(heli2.getFrame(), heli2.getPosition().x, heli2.getPosition().y,
                    heli2.getRegionWidth(), heli2.getRegionHeight());
        }

        //draw heli3 flipped around x-axis
        if(heli3.isRightHeading()) {
            sb.draw(heli3.getFrame(), heli3.getPosition().x + heli3.getRegionWidth(),
                    heli3.getPosition().y, - heli3.getRegionWidth(), heli3.getRegionHeight());
        }
        //draw heli3 without flipping around x-axis
        else{
            sb.draw(heli3.getFrame(), heli3.getPosition().x, heli3.getPosition().y,
                    heli3.getRegionWidth(), heli3.getRegionHeight());
        }

        text.draw(sb, "Press Esc to exit to menu screen",
                Helicopter.getScreenWidth() - 220, Helicopter.getScreenHeight() - 20);
        sb.end();

    }

    @Override
    public void dispose() {
    }
}
