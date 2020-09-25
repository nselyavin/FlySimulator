package com.sn.fly_sim.characters.nonPlayer;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.sn.fly_sim.Bullet;

public class Shimon extends Enemies{
    Shimon (Texture texture){
        super(texture);
        tx_bullet = new Texture("Ball_2.png");
    }

    @Override
    public void spawn() {
        health = 20;
    }
    @Override
    public void takeDamage(Bullet bullet) {
        if (health - bullet.getDamage() >= 0){
            health -= bullet.getDamage();
        }
    }

    @Override
    public void move() {

    }

    @Override
    public Bullet fire() {
        Bullet bullet = new Bullet(tx_bullet);

        bullet.setPosition(getX() - 1, getY());
        bullet.setDirection(new Vector2(-1.0f, 0.0f));

        return null;
    }
}
