package com.sn.fly_sim;

import java.awt.*;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

public class StartMenu {
    private Table canvas;
    private Button menu;
    private Button setting;
    private Button start;

    StartMenu(){
        Button.ButtonStyle style = new Button.ButtonStyle();
        menu = new Button(style);
        setting = new Button(style);
        start = new Button(style);

        canvas.add(menu);
        canvas.add(setting);
        canvas.add(start);
    }

    void draw(Batch batch){
    }
}
