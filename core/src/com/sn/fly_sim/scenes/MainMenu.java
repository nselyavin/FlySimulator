package com.sn.fly_sim.scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.sn.fly_sim.FlySimulator;
import com.sn.fly_sim.helpers.GameInfo;

public class MainMenu implements Screen {
    private FlySimulator game;
    private Texture bg;
    private Sprite player;

    public MainMenu(FlySimulator game){
        this.game = game;

        bg = new Texture("Game BG.png");
        player = new Sprite(new Texture("Player 1.png"));
        player.setPosition(GameInfo.WIDTH / 2.0f , GameInfo.HEIGHT / 2.0f);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.getBatch().begin();
        game.getBatch().draw(bg, 0, 0);
        game.getBatch().draw(player, player.getX(), player.getY());
        game.getBatch().end();
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
