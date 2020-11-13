package com.sn.fly_sim;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.sn.fly_sim.helpers.GameInfo;

import java.util.Random;

public class Bullet extends Sprite {
    private Vector2 direction;
    private float speed = 3;
    private int damage;
    private Body body;
    private World world;

    public Bullet(World world, Vector2 pos, Vector2 direct){
        super(new Texture("E:\\Project\\Java\\FlySimulator\\core\\assets\\bullet16.png"));
        direction = direct;
        this.world = world;
        setPosition(pos.x, pos.y);
        createBody();

    }

    public Vector2 getDirection() {
        return direction;
    }

    public void setDirection(Vector2 direction) {
        this.direction = direction;
    }

    int currenttime = 0;
    public void update() {
        if(currenttime < 5 * 30){
            speed = 5;
            currenttime++;
        } else {
            speed = 0;
        }

        body.setLinearVelocity(speed * direction.x, speed * direction.y);
        this.setPosition(body.getPosition().x * GameInfo.PPM,
                body.getPosition().y * GameInfo.PPM);

    }


    private void createBody(){
        BodyDef bDef = new BodyDef();
        bDef.type = BodyDef.BodyType.DynamicBody;
        bDef.position.set(getX() / GameInfo.PPM, getY() / GameInfo.PPM);

        body = world.createBody(bDef);

        FixtureDef fDef = new FixtureDef();
        fDef.density = 1;
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(getWidth() / 2 / GameInfo.PPM, getHeight() / 2 / GameInfo.PPM);
        fDef.shape = shape;

        body.createFixture(fDef);
        body.setFixedRotation(true);
        //body.getFixtureList().get(0).setSensor(true);
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
