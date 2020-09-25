package com.sn.fly_sim.scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.Array;
import com.sn.fly_sim.Bullet;
import com.sn.fly_sim.FlySimulator;
import com.sn.fly_sim.characters.nonPlayer.Enemies;
import com.sn.fly_sim.characters.Plane;
import com.sn.fly_sim.helpers.GameInfo;
import com.sn.fly_sim.systems.isInputCheck;

public class GameScene implements Screen, isInputCheck {
    private FlySimulator game;
    private Texture bg;
    private Plane player;
    private Array<Bullet> bullets;
    private Array<Enemies> enemies;
    private float deltaSpeedX;
    private float deltaSpeedY;

    public GameScene(FlySimulator game){
        this.game = game;

        //bg = new Texture("Options BG.png");
        // Инициализируем самолет игрока
        player = new Plane(new Texture("Plane.png"));
        player.setPosition(GameInfo.WIDTH  * 0.1f , GameInfo.HEIGHT / 2.0f);
        player.getTexture().setAnisotropicFilter(GL20.GL_LINEAR);
        player.setScale(5.0f);

        // Инициализируем массив пуль в котором будут хранится существующие выстрелы
        // сделаннне игроком или ИИ
        bullets = new Array<>();
        enemies = new Array<>();

        deltaSpeedX = 0;
        deltaSpeedY = 0;

    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        inputCheck();

        for (Bullet i : bullets){
            i.move();
            collisionCheck(i);
        }

        Gdx.gl.glClearColor(.2f, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.getBatch().begin();
        game.getBatch().draw(player, player.getX(), player.getY(),
                            player.getWidth() * 1.2f, player.getHeight() * 1.2f);
        for (Bullet i : bullets){
            game.getBatch().draw(i, i.getX(), i.getY(), i.getWidth(), i.getHeight());
        }
        game.getBatch().end();
    }

    public void collisionCheck(Bullet bullet){
        for (Enemies i : enemies){
            if ( bullet.getX() > i.getX() && bullet.getX() < i.getWidth()){
                if ( bullet.getY() > i.getY() && bullet.getY() < i.getHeight()){
                    i.takeDamage(bullet);
                }
            }
        }
    }

    @Override
    public void inputCheck() {
        checkVerticaly();
        checkHorisontaly();

        if (Gdx.input.isKeyPressed(Input.Keys.SPACE)){
            bullets.add(player.fire());
        }
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
