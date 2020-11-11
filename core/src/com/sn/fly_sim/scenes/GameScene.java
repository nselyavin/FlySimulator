package com.sn.fly_sim.scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.sn.fly_sim.FlySimulator;
import com.sn.fly_sim.Plane;
import com.sn.fly_sim.helpers.GameInfo;

public class GameScene implements Screen {
    final FlySimulator game;
    private Plane plane;
    World world;
    Box2DDebugRenderer rend;
    OrthographicCamera camera;
    Body rect;

    GameScene(FlySimulator game) {
        this.game = game;
        world = new World(new Vector2(0, 0), true);
        camera = new OrthographicCamera(GameInfo.WIDTH, GameInfo.WIDTH);
        camera.position.set(new Vector2(0, 0f), 0);
        rend = new Box2DDebugRenderer();
        createRect(0, 0);
        createRect(300, 300);

        plane = new Plane(world);
    }

    private void createRect(float x, float y) {
        BodyDef bDef = new BodyDef();
        bDef.type = BodyDef.BodyType.DynamicBody;
        bDef.position.set(x, y);

        rect = world.createBody(bDef);

        FixtureDef fDef = new FixtureDef();
        fDef.density = 0;
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(50, 50);
        fDef.shape = shape;

        rect.createFixture(fDef);
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

        rend.render(world, camera.combined);
        world.step(1f/ GameInfo.FPS, 4, 4);
    }

    private void check_input(){
        if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
            game.setScreen(new MainMenu(game));
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