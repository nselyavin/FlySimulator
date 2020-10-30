package com.sn.fly_sim.scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.sn.fly_sim.FlySimulator;
import com.sn.fly_sim.Player;
import com.sn.fly_sim.helpers.GameInfo;
import com.sn.fly_sim.helpers.PlayerHandler;

public class LoginScene implements Screen {
    final FlySimulator game;
    private Label login;
    private Label pass;
    private Stage stage;
    private Rectangle selectRect;
    private ShapeRenderer shapeRenderer;
    private boolean loginInputed;
    private String loginText;
    private String passText;
    private String starsText;

    public LoginScene(final FlySimulator gam) {
        game = gam;
        shapeRenderer = new ShapeRenderer();

        stage = new Stage();
        Label.LabelStyle style = new Label.LabelStyle();
        style.font = new BitmapFont();
        style.fontColor = Color.YELLOW;
        style.font.getData().setScale(3);
        login = new Label("NICKNAME", style);
        login.setPosition(
                GameInfo.WIDTH / 2.0f - login.getWidth()/2,
                GameInfo.HEIGHT / 2.0f
        );

        pass = new Label("PASSWORD", style);
        pass.setPosition(
                GameInfo.WIDTH / 2.0f - pass.getWidth()/2,
                GameInfo.HEIGHT / 2.0f - 50 - login.getHeight()
        );

        stage.addActor(login);
        stage.addActor(pass);

        loginInputed = false;
        loginText = "";
        passText = "";
        starsText = "";
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float v) {
        check_input();

        Gdx.gl.glClearColor(0.4f, 0.4f, 0.6f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.draw();
        stage.act();

        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(Color.YELLOW);
        if (!loginInputed) {
            shapeRenderer.rect(GameInfo.WIDTH / 2.0f - 150,
                    login.getY(),
                    300, 4);
        } else {
            shapeRenderer.rect(GameInfo.WIDTH / 2.0f - 150,
                    pass.getY(),
                    300, 4);
        }
        shapeRenderer.end();
    }

    private void check_input(){
        if (Gdx.input.isKeyJustPressed(Input.Keys.ENTER)) {
            if (loginInputed){
                // ToDo Реализовать класс текущего игрока
                Player player = PlayerHandler.getPlayer(loginText, passText);
                game.currentPlayer = player;
                game.setScreen(new MainMenu(game));
            }
            loginInputed = true;
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
            Gdx.app.exit();
        }

        for (int i = Input.Keys.A; i < Input.Keys.Z; i++) {
            if (Gdx.input.isKeyJustPressed(i)) {
                if (!loginInputed && loginText.length() < 10){
                    char c = (char) (i+36);
                    loginText += c;
                    login.setText(loginText);
                    login.setPosition(GameInfo.WIDTH / 2.0f - loginText.length() * 14,
                            GameInfo.HEIGHT / 2.0f);
                } else if (passText.length() < 10){
                    char c = (char) (i+36);
                    passText += c;
                    starsText += "*";
                    pass.setText(starsText);
                    pass.setPosition(GameInfo.WIDTH / 2.0f - starsText.length() * 9,
                            GameInfo.HEIGHT / 2.0f - 50 - login.getHeight());
                }
            }
        }
    }

    @Override
    public void resize(int i, int i1) {

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
