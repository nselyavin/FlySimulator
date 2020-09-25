package com.sn.fly_sim;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

public class Bullet extends Sprite {
    private Vector2 direction;
    private float speed;
    private int damage;

    public Bullet(Texture texture){
        super(texture);
    }

    public Vector2 getDirection() {
        return direction;
    }

    public void setDirection(Vector2 direction) {
        this.direction = direction;
    }

    public void move() {
        // ToDo сделать перемещение с учетом направления
        setPosition(getX() + (speed * direction.x), getY() + (speed * direction.y));
    }

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }


    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }


}
