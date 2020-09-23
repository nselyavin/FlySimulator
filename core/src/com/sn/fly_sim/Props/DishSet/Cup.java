package com.sn.fly_sim.Props.DishSet;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.badlogic.gdx.utils.Align;

public class Cup extends Dish implements Animatable {
    private int deltaAnim;
    private Texture actionTexture;

    public Cup(String txpath) {
        super(txpath);
        actionTexture = new Texture("Cup_action.png");
        deltaAnim = 0;
    }

    @Override
    void create(String txpath) {
        texture = new Texture(txpath);
        image = new Image(texture);
        image.setOrigin(Align.center);
    }

    @Override
    public void action() {
        System.out.println("Cup is pour");

        if (deltaAnim >= 50)
            deltaAnim = 0;
    }

    @Override
    public void perTick() {
        if (deltaAnim <= 25){
            setRot(rot += 3);
            deltaAnim += 2;
            image.setDrawable(new SpriteDrawable(new Sprite(actionTexture)));
        }
        else if ( 25 <= deltaAnim && deltaAnim <= 50){
            setRot(rot -= 3);
            deltaAnim += 2;
        }else {
            image.setDrawable(new SpriteDrawable(new Sprite(texture)));
        }
    }
}
