package com.sn.fly_sim.scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.sn.fly_sim.Bullet;
import com.sn.fly_sim.Enemy;
import com.sn.fly_sim.FlySimulator;
import com.sn.fly_sim.Plane;
import com.sn.fly_sim.helpers.GameInfo;

import java.util.ArrayList;

public class GameScene implements Screen, ContactListener {
    final FlySimulator game;
    private final WorldBox worldBox;
    private World world;
    private Plane plane;
    private ArrayList<Bullet> bullets;
    private ArrayList<Enemy> enemies;
    private OrthographicCamera camera;
    private Box2DDebugRenderer dgRenderer;

    GameScene(FlySimulator game) {
        this.game = game;
        camera = new OrthographicCamera();
        camera.setToOrtho(false, GameInfo.WIDTH / GameInfo.PPM,
                GameInfo.HEIGHT / GameInfo.PPM );
        camera.position.set(GameInfo.WIDTH / 2f, GameInfo.HEIGHT / 2f, 0);
        dgRenderer = new Box2DDebugRenderer();

        world = new World(new Vector2(0f, 0f), true);
        BodyDef groundBodyDef = new BodyDef();
        groundBodyDef.position.set(new Vector2(0f, -9.8f));

        worldBox = new WorldBox(world);
        plane = new Plane(world,GameInfo.WIDTH / 2f, 100);
        enemies = new ArrayList<>();
        enemies.add(new Enemy(world, GameInfo.WIDTH / 2f, GameInfo.HEIGHT - 50));
        bullets = new ArrayList<>();

    }

    @Override
    public void show() {}

    @Override
    public void render(float v) {
        check_input();
        plane.updatePlane();

        Gdx.gl.glClearColor(0.4f, 0.4f, 0.6f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.batch.begin();
        game.batch.draw(plane, plane.getX()-plane.getWidth() / 2, plane.getY() - plane.getHeight()/2);
        for (Enemy i : enemies){
            i.update();
            game.batch.draw(i, i.getX()-i.getWidth() / 2, i.getY() - i.getHeight()/2);
        }

        for (int  i = 0; i < bullets.size(); i++){
            Bullet tmp = bullets.get(i);
            tmp.update();

            game.batch.draw(tmp, tmp.getX()-tmp.getWidth() / 2, tmp.getY() - tmp.getHeight()/2);
            if (tmp.getY() > GameInfo.HEIGHT){
                //bullets.remove(i);
            }
        }
        game.batch.end();

        dgRenderer.render(world, camera.combined);
        world.step(1f / GameInfo.FPS, 6, 2);
    }

    private void check_input() {
        if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
            game.setScreen(new MainMenu(game));
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)){
            bullets.add(plane.fire());
        }

        // Перемещение самолета
        plane.stop();
        if(Gdx.input.isKeyPressed(Input.Keys.LEFT)){
            plane.setHorizSpeed(-2f);
        } else if(Gdx.input.isKeyPressed(Input.Keys.RIGHT) ){
            plane.setHorizSpeed(2f);
        }

        if(Gdx.input.isKeyPressed(Input.Keys.UP)){
            plane.setVertSpeed(2.5f);
        } else if(Gdx.input.isKeyPressed(Input.Keys.DOWN) ){
            plane.setVertSpeed(-2.5f);
        }
    }

    @Override
    public void beginContact(Contact contact) {
        Fixture firstB, secondB;
    }

    @Override
    public void endContact(Contact contact) {

    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

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