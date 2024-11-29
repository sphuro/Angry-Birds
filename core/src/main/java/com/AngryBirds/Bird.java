package com.AngryBirds;

public interface Bird {
    void show();

    void draw(float x, float y, float width, float height);

    void draw_slingshot(float x, float y, float width, float height);

    void render(float delta);

    void resize(int i, int i1);

    void pause();

    void resume();

    void hide();

    void dispose();
}
