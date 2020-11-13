package com.sn.fly_sim.scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.sn.fly_sim.FlySimulator;
import com.sn.fly_sim.helpers.GameInfo;
import com.sn.fly_sim.helpers.PlayerHandler;

public class LoginScene implements Screen {
    final FlySimulator game;
    final private Texture loginTittle;
    final private Texture signupTittle;
    final private Label login;
    final private Label pass;
    final private Label help;
    final private Label error;
    final private Stage stage;
    final private ShapeRenderer shapeRenderer;
    Label.LabelStyle style2;
    private String loginText;
    private String passText;
    private String starsText;
    private boolean loginInputted;
    private boolean signup;

    public LoginScene(final FlySimulator gam) {
        game = gam;
        shapeRenderer = new ShapeRenderer();
        stage = new Stage();

        // Initial scene tittles
        loginTittle = new Texture("login.png");
        signupTittle = new Texture("signup.png");

        // Initial font styke
        Label.LabelStyle style = new Label.LabelStyle();
        style.font = new BitmapFont();
        style.fontColor = Color.YELLOW;
        style.font.getData().setScale(3);

        style2 = new Label.LabelStyle();
        style2.font = new BitmapFont();
        style2.fontColor = new Color(1.0f, 0.0f, 0.0f, 1.0f);
        style2.font.getData().setScale(2);

        help = new Label("F1 - to sign-up. F2 - to reset. ENTER - to input", style2);
        help.setPosition(GameInfo.WIDTH / 2.0f - help.getWidth() / 2.0f, help.getHeight());
        error = new Label(" ", style2);
        error.setPosition(GameInfo.WIDTH / 2.0f - help.getWidth() / 2.0f,
                help.getHeight() + 10 + error.getHeight());

        // Initial input text area
        login = new Label("NICKNAME", style);
        login.setPosition(
                GameInfo.WIDTH / 2.0f - login.getWidth() / 2,
                GameInfo.HEIGHT / 2.0f
        );

        pass = new Label("PASSWORD", style);
        pass.setPosition(
                GameInfo.WIDTH / 2.0f - pass.getWidth() / 2,
                GameInfo.HEIGHT / 2.0f - 50 - login.getHeight()
        );

        stage.addActor(login);
        stage.addActor(pass);
        stage.addActor(help);
        stage.addActor(error);

        // Initial variables
        loginInputted = false;
        loginText = "";
        passText = "";
        starsText = "";
        signup = false;
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float v) {
        check_input();

        // Clear stage
        Gdx.gl.glClearColor(0.4f, 0.4f, 0.6f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // Stage drawing
        stage.draw();
        stage.act();

        // Batch drawing
        game.batch.begin();
        Texture currentTittle;
        if (!signup) {
            currentTittle = loginTittle;
        } else {
            currentTittle = signupTittle;
        }
        game.batch.draw(currentTittle,
                GameInfo.WIDTH / 2.0f - loginTittle.getWidth() / 2.0f,
                GameInfo.HEIGHT * 3.0f / 4.0f - loginTittle.getHeight() / 2.0f,
                loginTittle.getWidth(),
                loginTittle.getHeight()
        );
        game.batch.end();

        // Drawing a shapes
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(Color.YELLOW);
        // Если логгин еще не введен рендерить полоску под полем логина
        if (!loginInputted) {
            shapeRenderer.rect(GameInfo.WIDTH / 2.0f - 150,
                    login.getY(),
                    300, 4);
        } else {
            shapeRenderer.rect(GameInfo.WIDTH / 2.0f - 150,
                    pass.getY(),
                    300, 4);
        }
        shapeRenderer.end();

        // Other animation
        style2.fontColor = new Color(1.0f, 0.0f, 0.0f,
                Math.abs((float) Math.sin(0.001 * System.currentTimeMillis())));

    }

    private void check_input() {
        // Reading ENTER input and calling login function
        if (Gdx.input.isKeyJustPressed(Input.Keys.ENTER)) {
            if (loginInputted) {
                // Check pass input
                if (passText.length() == 0) {
                    error.setText("Password cannot be empty");
                } else {
                    if (!signup) { // Login
                        game.currentPlayer = game.playerHandler.getPlayer(loginText, passText);

                        if (game.currentPlayer != null) {
                            game.setScreen(new MainMenu(game));
                        } else {
                            error.setText("Failed login. Check your login");
                        }
                    } else { // Register
                        if (game.playerHandler.checkForLogin(loginText)) {
                            error.setText("User with this nickname already created");
                        } else {
                            game.playerHandler.createPlayer(loginText, passText);
                            error.setText("");
                            signup = false;
                        }
                    }
                }
            }

            // Check login input
            if (loginText.length() == 0) {
                error.setText("Nickname cannot be empty");
            } else {
                loginInputted = true;
            }
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
            Gdx.app.exit();
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.F1)) {
            signup = !signup;
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.F2)) {
            loginText = "";
            passText = "";
            starsText = "";
            loginInputted = false;
            login.setText("NICKNAME");
            pass.setText("PASSWORD");
        }

        // Reading input text
        for (int i = Input.Keys.A; i < Input.Keys.Z; i++) {
            if (Gdx.input.isKeyJustPressed(i)) {
                if (!loginInputted && loginText.length() < 10) {
                    char c = (char) (i + 36);
                    loginText += c;
                    login.setText(loginText);

                } else if (loginInputted && passText.length() < 10) {
                    char c = (char) (i + 36);
                    passText += c;
                    starsText += "*";
                    pass.setText(starsText);
                }
            }
        }

        // Reading backspace
        if (Gdx.input.isKeyJustPressed(Input.Keys.BACKSPACE)) {
            if (!loginInputted && loginText.length() > 0) {
                loginText = loginText.substring(0, loginText.length() - 1);
                login.setText(loginText);
            } else if (loginInputted && passText.length() > 0) {
                passText = passText.substring(0, passText.length() - 1);
                starsText = starsText.substring(0, starsText.length() - 1);
                pass.setText(starsText);
            }
        }
        login.setPosition(GameInfo.WIDTH / 2.0f - login.getText().length() * 14,
                GameInfo.HEIGHT / 2.0f);
        pass.setPosition(GameInfo.WIDTH / 2.0f - pass.getText().length() * 13,
                GameInfo.HEIGHT / 2.0f - 50 - login.getHeight());
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
