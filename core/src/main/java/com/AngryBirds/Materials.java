package com.AngryBirds;

import com.badlogic.gdx.physics.box2d.Body;

public interface Materials {
    void show();

    void draw();

    void render(float delta);

    void resize(int i, int i1);

    void pause();

    void resume();

    void hide();

    void dispose();

    void destroy();

    Body getbody();

    int gethealth();

    void sethealth(int health);
}
