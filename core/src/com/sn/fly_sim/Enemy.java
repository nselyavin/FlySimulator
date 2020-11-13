package com.sn.fly_sim;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.sn.fly_sim.helpers.GameInfo;

import java.util.Random;

public class Enemy extends Sprite {
    private float scale;
    private int damage;
    private int health;
    private float speed;
    private World world;
    private Body body;
    private float brainTick;
    private float currentTick;
    private float speedY;
    private float speedX;


    public Enemy (World world, float xPos, float yPos){
        super(new Texture("E:\\Project\\Java\\FlySimulator\\core\\assets\\Rect64.png"));
        setPosition(xPos, yPos);
        this.world = world;

        currentTick = 0;
        brainTick = GameInfo.FPS * 2;
        speedY = -.5f;
        createBody();
    }

    private void createBody(){
        BodyDef bDef = new BodyDef();
        bDef.type = BodyDef.BodyType.DynamicBody;
        bDef.position.set(getX() / GameInfo.PPM, getY() / GameInfo.PPM);

        body = world.createBody(bDef);

        FixtureDef fDef = new FixtureDef();
        fDef.density = 5;
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(getWidth() / 2 / GameInfo.PPM, getHeight() / 2 / GameInfo.PPM);
        fDef.shape = shape;

        body.createFixture(fDef);
        body.setFixedRotation(true);
    }

    public void update() {
        currentTick++;

        if (currentTick > brainTick){
            int choose = new Random().nextInt() * 10 % 3;
            speedY = 0;
            switch (choose){
                case 1:
                    speedX -= 2;
                    break;
                case 2:
                    speedX = 2;
                    break;
                case 3:
                    speedX = 0;
            }
        }


        body.setLinearVelocity(speedX, speedY);
        this.setPosition(body.getPosition().x * GameInfo.PPM,
                body.getPosition().y * GameInfo.PPM);

    }

    public Body getBody() {
        return body;
    }

    public void takeDamage(int dmg){
        health -= dmg;
    }

    public boolean isAlive(){
        return health > 0;
    }

    public int getHealth(){
        return health;
    }

    public void stop() {
        speedX = 0;
        speedY = 0;
    }
}
