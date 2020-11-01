package com.sn.fly_sim.desktop;

import com.badlogic.gdx.Files;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.sn.fly_sim.FlySimulator;
import com.sn.fly_sim.helpers.GameInfo;

import javax.accessibility.AccessibleRelation;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();

		config.width = GameInfo.WIDTH;
		config.height = GameInfo.HEIGHT;
		config.title = "Fly simulator";
		config.foregroundFPS = GameInfo.FPS;

		new LwjglApplication(new FlySimulator(), config);

		System.out.println("end");
	}
}
