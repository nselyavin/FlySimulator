package com.sn.fly_sim;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.sn.fly_sim.helpers.DBConfigs;
import com.sn.fly_sim.helpers.PlayerHandler;
import com.sn.fly_sim.scenes.LoginScene;

import java.sql.SQLException;
import java.util.Date;
import java.util.Timer;

public class FlySimulator extends Game  {
	// ToDo подключение к бд
	public PlayerHandler playerHandler;
	public Player currentPlayer;
	public SpriteBatch batch;
	public BitmapFont font;

	@Override
	public void create() {
		batch = new SpriteBatch();
		font = new BitmapFont();
		try {
			playerHandler = new PlayerHandler(); // Uniq object of playerDB handler

		} catch (SQLException | ClassNotFoundException throwables) {
			System.out.println("ERROR: DB connection failed");
			throwables.printStackTrace();
			Gdx.app.exit();
		}

		this.setScreen(new LoginScene(this));
	}

	@Override
	public void render() {
		super.render();
	}

	@Override
	public void dispose() {
		super.dispose();
		batch.dispose();
		font.dispose();
		System.out.println("end");
	}


}