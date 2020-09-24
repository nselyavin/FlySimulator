package com.sn.fly_sim;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.sn.fly_sim.helpers.GameInfo;
import com.sn.fly_sim.scenes.MainMenu;
import com.sn.fly_sim.scenes.GameScene;


public class FlySimulator extends Game {
	private SpriteBatch batch;

	private static final int centerX = GameInfo.WIDTH / 2;
	private static final int centerY = GameInfo.HEIGHT / 2;

	@Override
	public void create () {
		batch = new SpriteBatch();
		setScreen(new GameScene(this));
	}

	@Override
	public void render () {
		inputCheck();
		super.render();
	}

	private void inputCheck(){
        if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)){
            Gdx.app.exit();
        }
		if (Gdx.input.isKeyJustPressed(Input.Keys.NUM_1)){
			setScreen(new MainMenu(this));
		}
		if (Gdx.input.isKeyJustPressed(Input.Keys.NUM_2)){
			setScreen(new GameScene(this));
		}
	}

	public SpriteBatch getBatch() {
		return batch;
	}
}
