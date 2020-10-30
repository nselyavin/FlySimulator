package com.sn.fly_sim.scenes;

import com.badlogic.gdx.Screen;
import com.sn.fly_sim.FlySimulator;

public interface Scene extends Screen {
    @Override
    public void show();

    @Override
    public void render(float v);

    void check_input();

    @Override
    public void resize(int i, int i1);

    @Override
    public void pause();

    @Override
    public void resume();

    @Override
    public void hide();

    @Override
    public void dispose();
}
