package com.sn.fly_sim;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.sn.fly_sim.helpers.GameInfo;

public class Plane extends Sprite {
    private float scale;
    private int damage;
    private int health;
    private float speed;

    private World world;
    private Body body;
    private float deltaSpeed;
    private float speedY;
    private float speedX;

    public Plane (World world, float xPos, float yPos){
        super(new Texture("E:\\Project\\Java\\FlySimulator\\core\\assets\\plane64.png"));
        setPosition(xPos, yPos);
        this.world = world;
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

    public void updatePlane() {
        body.setLinearVelocity(speedX, speedY);
        this.setPosition(body.getPosition().x * GameInfo.PPM,
                body.getPosition().y * GameInfo.PPM);

    }

    public Bullet fire(){
        return new Bullet(world, new Vector2(getX(), getY() + getHeight()), new Vector2(0, 1));
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

    public void setHorizSpeed(float speed) {
        speedX = speed;
    }

    public void setVertSpeed(float speed) {
        speedY = speed;
    }

    public void stop() {
        speedX = 0;
        speedY = 0;
    }
}
