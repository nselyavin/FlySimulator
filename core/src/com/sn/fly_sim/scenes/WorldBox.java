package com.sn.fly_sim.scenes;

import com.badlogic.gdx.physics.box2d.*;
import com.sn.fly_sim.helpers.GameInfo;

public class WorldBox {
    private World world;
    private Body body;

    public WorldBox(World world){
        this.world = world;
        createBody(0, 0, GameInfo.WIDTH, 0); // Down
        createBody(0, 0, 0, GameInfo.HEIGHT); // left
        createBody(0, GameInfo.HEIGHT, GameInfo.WIDTH,0); // Top
        createBody(GameInfo.WIDTH, 0, 0, GameInfo.HEIGHT); // Right
    }

    void createBody(float posX, float posY, float width, float height){
        BodyDef bDef = new BodyDef();
        bDef.type = BodyDef.BodyType.StaticBody;
        bDef.position.set(posX / GameInfo.PPM, posY / GameInfo.PPM);

        body = world.createBody(bDef);

        FixtureDef fDef = new FixtureDef();
        fDef.density = 0;
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(width / GameInfo.PPM, height  / GameInfo.PPM);
        fDef.shape = shape;

        body.createFixture(fDef);
    }
}
