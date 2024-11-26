package com.AngryBirds;

import com.badlogic.gdx.physics.box2d.*;

import java.util.ArrayList;

import static java.lang.Math.max;

public class CheckCollision implements ContactListener {

    private World world; // Reference to the world
    private ArrayList<Body> todestroy = new ArrayList<>();
    private ArrayList<Body> destroyed = new ArrayList<>();

    public ArrayList<Body> getTodestroy() {
        return todestroy;
    }

    public ArrayList<Body> getDestroyed() {
        return destroyed;
    }

    public CheckCollision(World world) {
        this.world = world;
    }

    @Override
    public void beginContact(Contact contact) {
    }

    @Override
    public void endContact(Contact contact) {
    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {
    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {
        Fixture fixtureA = contact.getFixtureA();
        Fixture fixtureB = contact.getFixtureB();
        float mx = 0;
        for (float normalImpulse : impulse.getNormalImpulses()) {
            mx = max(mx,normalImpulse);
        }
        float need=0.7f;
        if (fixtureA.getUserData() instanceof KingPig) need = 0.6f;
        else if (fixtureA.getUserData() instanceof Pig) need = 0.4f;
        else if (fixtureA.getUserData() instanceof Box) need = 0.7f;
        else if (fixtureA.getUserData() instanceof Log) need = 0.4f;
        else if (fixtureA.getUserData() instanceof HelmetPig) need = 0.5f;
        else if (fixtureA.getUserData() instanceof Stonebox) need = 1;
        else if (fixtureA.getUserData() instanceof GlassBox) need = 0.5f;
        else if (fixtureA.getUserData() instanceof StoneLog) need = 1;
        float need2=0.7f;
        if (fixtureA.getUserData() instanceof KingPig) need2 = 0.6f;
        else if (fixtureA.getUserData() instanceof Pig) need2 = 0.4f;
        else if (fixtureA.getUserData() instanceof Box) need2 = 0.7f;
        else if (fixtureA.getUserData() instanceof Log) need2 = 0.4f;
        else if (fixtureA.getUserData() instanceof HelmetPig) need2 = 0.5f;
        else if (fixtureA.getUserData() instanceof Stonebox) need2 = 1;
        else if (fixtureA.getUserData() instanceof GlassBox) need2 = 0.5f;
        else if (fixtureA.getUserData() instanceof StoneLog) need2 = 1;

        if (mx > 0.4f) {
            if (fixtureA.getUserData() instanceof Materials && mx>need) {
                Materials objA = (Materials) fixtureA.getUserData();
                objA.destroy();
                todestroy.add(objA.getbody());
            }
            if (fixtureB.getUserData() instanceof Materials && mx>need2) {
                Materials objB = (Materials) fixtureB.getUserData();
                objB.destroy();
                todestroy.add(objB.getbody());
            }
        }
    }
}
