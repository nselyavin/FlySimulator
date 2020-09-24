package com.sn.fly_sim.scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.sn.fly_sim.FlySimulator;
import com.sn.fly_sim.helpers.GameInfo;
import com.sn.fly_sim.systems.isInputCheck;

public class GameScene implements Screen, isInputCheck {
    private FlySimulator game;
    private Texture bg;
    private Sprite player;
    private float deltaSpeedX;
    private float deltaSpeedY;

    public GameScene(FlySimulator game){
        this.game = game;

        //bg = new Texture("Options BG.png");
        player = new Sprite(new Texture("Plane.png"));
        player.setPosition(GameInfo.WIDTH  * 0.1f , GameInfo.HEIGHT / 2.0f);
        player.getTexture().setAnisotropicFilter(GL20.GL_LINEAR);
        player.setScale(5.0f);

        deltaSpeedX = 0;
        deltaSpeedY = 0;
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        inputCheck();

        Gdx.gl.glClearColor(.2f, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.getBatch().begin();
        game.getBatch().draw(player, player.getX(), player.getY(),
                            player.getWidth() * 1.2f, player.getHeight() * 1.2f);
        game.getBatch().end();
    }

    @Override
    public void inputCheck() {
        checkVerticaly();
        checkHorisontaly();
    }

    private void checkHorisontaly(){
        if (Gdx.input.isKeyPressed(Input.Keys.D)){
            if ( deltaSpeedX < 1 && player.getX()< GameInfo.WIDTH * 0.3)
                deltaSpeedX += 0.1f;
        }
        else if (Gdx.input.isKeyPressed(Input.Keys.A)){
            if ( deltaSpeedX > -1 && 0 < player.getX())
                deltaSpeedX -= 0.1f;
        }
        else {
            if (deltaSpeedX > 0){
                deltaSpeedX -= 0.1;
            }
            if (deltaSpeedX < 0){
                deltaSpeedX += 0.1;
            }
        }

        player.setPosition(player.getX() + (float)Math.sin(deltaSpeedX) * 5, player.getY());
    }

    private void checkVerticaly() {
        if (Gdx.input.isKeyPressed(Input.Keys.W)){
            if ( deltaSpeedY < 1)
                deltaSpeedY += 0.1f;
        }
        else if (Gdx.input.isKeyPressed(Input.Keys.S)){
            if ( deltaSpeedY > -1)
                deltaSpeedY -= 0.1f;
        }
        else {
            if (deltaSpeedY > 0){
                deltaSpeedY -= 0.1;
            }
            if (deltaSpeedY < 0){
                deltaSpeedY += 0.1;
            }
        }
        player.setPosition(player.getX(), player.getY() + (float)Math.sin(deltaSpeedY) * 4);
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }

}
