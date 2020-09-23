package com.sn.fly_sim;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.sn.fly_sim.Props.DishSet.Animatable;

import java.util.Vector;

public class FlySimulator extends ApplicationAdapter {
	private Stage stage;
	private long start = System.currentTimeMillis();

	@Override
	public void create () {
		stage = new Stage(new ScreenViewport());
	}

	@Override
	public void render () {
		inputCheck();

		Gdx.gl.glClearColor(0, 0.7f, 0.2f, 1.0f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		stage.act();
		stage.draw();
		int fps = 30;
		sleep(fps);
	}

	private void sleep(int fps) {
		if(fps>0){
			long diff = System.currentTimeMillis() - start;
			long targetDelay = 1000/fps;
			if (diff < targetDelay) {
				try{
					Thread.sleep(targetDelay - diff);
				} catch (InterruptedException ignored) {}
			}
			start = System.currentTimeMillis();
		}
	}

	void inputCheck(){
        if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)){
            Gdx.app.exit();
        }
	}

	@Override
	public void dispose () {
	}
}
