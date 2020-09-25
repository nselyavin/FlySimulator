package com.sn.fly_sim.characters;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.sn.fly_sim.Bullet;
import com.sun.org.apache.xpath.internal.operations.Bool;

public class Plane extends Sprite implements Damagable{
    private int health;
    private Texture tx_planeBullet;
    private int damage;

    public Plane(Texture texture){
        super(texture);
        tx_planeBullet = new Texture("Ball_1.png");
    }

    public Bullet fire(){
        Bullet planeBullet = new Bullet(tx_planeBullet);

        planeBullet.setPosition(getX() + getWidth() + 1, getY());
        planeBullet.setDirection(new Vector2(1.0f, 0.0f));
        planeBullet.setSpeed(1);
        planeBullet.setDamage(10);

        return planeBullet;
    }

    @Override
    public void takeDamage(Bullet bullet) {
        if (health - bullet.getDamage() > 0){
            health -= bullet.getDamage();
        }
    }

    public boolean isAlive(){
        return health > 0;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getHealth() {
        return health;
    }
}
