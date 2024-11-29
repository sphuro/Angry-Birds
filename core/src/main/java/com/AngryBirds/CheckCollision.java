package com.AngryBirds;

import com.badlogic.gdx.physics.box2d.*;

import java.util.ArrayList;

import static java.lang.Math.max;

public class CheckCollision implements ContactListener {

    private World world;
    private Main game;
    private ArrayList<Body> todestroy = new ArrayList<>();
    private ArrayList<Body> destroyed = new ArrayList<>();

    public ArrayList<Body> getTodestroy() {
        return todestroy;
    }

    public ArrayList<Body> getDestroyed() {
        return destroyed;
    }

    public CheckCollision(World world,Main game) {
        this.world = world;
        this.game = game;
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
        int mul = 1;
        if (fixtureA.getUserData()=="red") mul = 2;
        if (fixtureB.getUserData()=="red") mul = 2;
        if (fixtureB.getUserData() instanceof Materials && mx>0.4f) {
            Materials objB = (Materials) fixtureB.getUserData();
            if (objB.gethealth()<=mx*100*mul && game.isSoundopen()) game.getPig().play();
            objB.sethealth((int) (objB.gethealth()-mx*100*mul));
            if (objB.gethealth()<=0) {
                objB.destroy();
                todestroy.add(objB.getbody());
            }
        }
        if (fixtureA.getUserData() instanceof Materials && mx>0.4f) {
            Materials objA = (Materials) fixtureA.getUserData();
            if (objA.gethealth()<=mx*100*mul && game.isSoundopen()) game.getPig().play();
            objA.sethealth((int) (objA.gethealth()-mx*100*mul));
            if (objA.gethealth()<=0) {
                objA.destroy();
                todestroy.add(objA.getbody());
            }
        }
    }
}
