package com.sn.fly_sim.Props.DishSet;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;


public abstract class Dish implements Usable {
    protected Texture texture;
    protected Image image;
    protected float scaleX, scaleY;
    protected float posX, posY;
    protected float rot;

    public Dish(String txpath) {
        scaleX = 1;
        scaleY = 1;
        posX = 0;
        posY = 0;
        create(txpath);
    }

    abstract void create(String txpath);
    public abstract void action();

    public Texture getTexture() {
        return texture;
    }

    public void setTexture(Texture texture) {
        this.texture = texture;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public void setScale(float scaleX, float scaleY) {
        this.scaleX = scaleX;
        this.scaleY = scaleY;
    }

    public float[] getScale() {
        return new float[] {scaleX, scaleY};
    }

    public void setPos(float posX, float posY) {
        this.posX = posX;
        this.posY = posY;
        image.setPosition(posX, posY);
    }

    public float getRot() {
        return rot;
    }

    public void setRot(float rot) {
        this.rot = rot;
        image.setRotation(rot);
    }

    public float[] getPos(){
        return new float [] {posX, posY};
    }
}
