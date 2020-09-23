package com.sn.fly_sim.Props.DishSet;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import org.w3c.dom.ranges.Range;

public class Fork extends Dish implements Animatable{
    private int deltaAnim;

    public Fork(String txpath) {
        super(txpath);
        deltaAnim = 0;
    }

    @Override
    void create(String txpath) {
        texture = new Texture(txpath);
        image = new Image(texture);
    }

    @Override
    public void action() {
        System.out.println("Fork is jebing");

        if (deltaAnim >= 50)
            deltaAnim = 0;
    }

    @Override
    public void perTick() {
        if (deltaAnim <= 25){
            setPos(posX, posY += 3);
            deltaAnim += 2;
        }
        if ( 25 <= deltaAnim && deltaAnim <= 50){
            setPos(posX, posY -= 3);
            deltaAnim += 2;
        }
    }
}
