package com.AngryBirds;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class RedBird extends Main implements Screen,Bird {
    private SpriteBatch batch;
    private OrthographicCamera camera;
    private Stage stage;
    private Viewport viewport;
    private Main game;
    private Texture without;
    private Texture with;

    public RedBird(Main game) {
        this.game = game;
        batch = new SpriteBatch();
        camera = new OrthographicCamera();
        viewport = new StretchViewport(1600, 900, camera);
        stage = new Stage(viewport, batch);
        without = new Texture("red_without.png");
        with = new Texture("red_with.png");
    }

    @Override
    public void show() {

    }

    public void draw(float x,float y,float width,float height) {
        batch.setProjectionMatrix(camera.combined);
        viewport.apply();
        batch.begin();
        batch.draw(without, x, y, width, height);
        batch.end();
    }

    public void draw_slingshot(float x, float y, float width, float height) {
        batch.setProjectionMatrix(camera.combined);
        viewport.apply();
        batch.begin();
        batch.draw(with, x, y, width, height);
        batch.end();
    }

    @Override
    public void render(float delta) {

    }

    @Override
    public void resize(int i, int i1) {
        viewport.update(i, i1);
        camera.position.set(viewport.getWorldWidth() / 2 , viewport.getWorldHeight() / 2, 0);
        camera.update();
        batch.setProjectionMatrix(camera.combined);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        batch.dispose();
        stage.dispose();
    }
}
