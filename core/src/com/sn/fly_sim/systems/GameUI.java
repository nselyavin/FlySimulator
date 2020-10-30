package com.sn.fly_sim.systems;

import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.TextArea;
import com.sn.fly_sim.helpers.GameInfo;

public class GameUI {
    SpriteBatch spriteBatch;
    BitmapFont font;
    String health;
    String score;

    public GameUI() {
        health = "100";
        score = "0";
        spriteBatch = new SpriteBatch();
        font = new BitmapFont();
    }

    public void setHealth (int health){
        this.health = Integer.toString(health);
    }

    public void setScore (int score){
        this.score =  Integer.toString(score);
    }

    public void draw() {
        spriteBatch.begin();
        font.getRegion().getTexture().setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);
        font.getData().setScale(2);

        font.draw(spriteBatch,"Health " +  health, 10, GameInfo.HEIGHT - 20);
        font.draw(spriteBatch, "Score " + score, GameInfo.WIDTH-250, GameInfo.HEIGHT - 20);
        spriteBatch.end();
    }

    public int getHealth() {
        return Integer.parseInt(health);
    }

    public int getScore() {
        return Integer.parseInt(score);
    }
}
