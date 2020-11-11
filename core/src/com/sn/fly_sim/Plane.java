package com.sn.fly_sim;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;

public class Plane {
    public final static float SPEED = 5f;
    public final static float SIZE = 1f;
    private Vector2	velocity = new Vector2();

    public Fixture playerPhysicsTexture;
    BodyDef bDef;

    public Plane(World world){
        bDef = new BodyDef();
        bDef.type = BodyDef.BodyType.DynamicBody;
        bDef.position.set(10, 7);

        PolygonShape poly = new PolygonShape();
        poly.setAsBox(0.5f, 0.5f);

        setFriction(10f);
    }

    public void setFriction(float f){

    }


}
