package com.sn.fly_sim.scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.sn.fly_sim.FlySimulator;
import com.sn.fly_sim.helpers.GameInfo;
import org.graalvm.compiler.lir.LabelRef;

import javax.swing.text.Style;
import java.util.ArrayList;
import java.util.List;

public class MainMenu implements Screen {

    final FlySimulator game;
    OrthographicCamera camera;
    private Label hellouser;
    private Texture logo;
    private Texture startBtn;
    private Texture reloginBtn;
    private Texture exitBtn;
    private Texture startBtn_active;
    private Texture reloginBtn_active;
    private Texture exitBtn_active;
    private List<Texture> btns;
    private List<Texture> btns_active;
    private int currentbtn;

    public MainMenu(final FlySimulator gam) {
        game = gam;

        camera = new OrthographicCamera();
        camera.setToOrtho(false, GameInfo.WIDTH, GameInfo.HEIGHT);

        Label.LabelStyle style  = new Label.LabelStyle();
        style.font = new BitmapFont();
        style.fontColor = Color.YELLOW;
        style.font.getData().setScale(1.4f);
        hellouser = new Label("Welcome " + game.currentPlayer.getName() + "!", style);
        hellouser.setPosition(10, 5 + hellouser.getHeight());

        logo = new Texture("logo.png");
        startBtn = new Texture("startbtn.png");
        reloginBtn = new Texture("reloginbtn.png");
        exitBtn = new Texture("exitbtn.png");
        startBtn_active = new Texture("startbtn_active.png");
        reloginBtn_active = new Texture("reloginbtn_active.png");
        exitBtn_active = new Texture("exitbtn_active.png");

        btns = new ArrayList<>();
        btns.add(startBtn);
        btns.add(reloginBtn);
        btns.add(exitBtn);

        btns_active = new ArrayList<>();
        btns_active.add(startBtn_active);
        btns_active.add(reloginBtn_active);
        btns_active.add(exitBtn_active);

        currentbtn = 0;
    }

    @Override
    public void show() {}

    @Override
    public void render(float delta) {
        // Проверка ввода и геймлпей часть до отрисовки
        check_input();

        Gdx.gl.glClearColor(0.4f, 0.4f, 0.6f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();
        game.batch.setProjectionMatrix(camera.combined);
        game.batch.begin();
        game.batch.draw(logo,
                GameInfo.WIDTH / 2.0f - logo.getWidth() / 2.0f,
                GameInfo.HEIGHT * 2.0f / 3.0f,
                logo.getWidth(),
                logo.getHeight());

        // Отрисовка кнопок меню
        for (int i = 0; i < btns.size(); i++){
            Texture tex;
            if (i == currentbtn){
                tex = btns_active.get(i);
            } else {
                tex = btns.get(i);
            }
            game.batch.draw(tex,
                    GameInfo.WIDTH/ 2.0f - tex.getWidth() / 2.0f,
                    GameInfo.HEIGHT/ 2.0f - i * (tex.getHeight() + 20),
                    tex.getWidth(),
                    tex.getHeight()
                    );
        }
        hellouser.draw(game.batch, 1.0f);
        game.batch.end();
    }

    private void check_input(){
        if (Gdx.input.isKeyJustPressed(Input.Keys.DOWN)) {
            currentbtn = Math.min(btns.size() -1 , currentbtn + 1);
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.UP)) {
            currentbtn =  Math.max(0 , currentbtn - 1);
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.ENTER)){
            switch (currentbtn){
                case 0:
                    game.setScreen(new GameScene(game));
                    break;
                case 1:
                    game.setScreen(new LoginScene(game));
                    break;
                case 2:
                    Gdx.app.exit();
                    break;
            }
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE))
            Gdx.app.exit();
    }

    @Override
    public void resize(int width, int height) {}

    @Override
    public void pause() {}

    @Override
    public void resume() { }

    @Override
    public void hide() { }

    @Override
    public void dispose() {

    }
}