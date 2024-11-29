package com.AngryBirds;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import org.w3c.dom.Text;

import javax.swing.event.ChangeListener;
import java.time.Duration;
import java.time.Instant;

public class BlueBird extends Main implements Screen,Bird {
    private SpriteBatch batch;
    private OrthographicCamera camera;
    private Stage stage;
    private Viewport viewport;
    private Main game;
    private Texture without;
    private Texture with;
    private int x,y,width,height;

    public BlueBird(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;

    }

    public BlueBird(Main game) {
        this.game = game;
        batch = new SpriteBatch();
        camera = new OrthographicCamera();
        viewport = new StretchViewport(1600, 900, camera);
        stage = new Stage(viewport, batch);
        without = new Texture("blue_without.png");
        with = new Texture("blue_with.png");
    }
    @Override
    public void show() {

    }

    public int getPosX() {
        System.out.println("x: " + x );
        return x;
    }

    public int getPosY() {
        System.out.println("y: " + y );
        return y;
    }

    public int getWidth() {
        System.out.println("width: " + width );
        return width;
    }

    public int getHeight() {
        System.out.println("height: " + height );
        return height;
    }

    public void draw(float x,float y,float width,float height) {
        batch.setProjectionMatrix(camera.combined);
        viewport.apply();
        batch.begin();
        batch.draw(without, x, y, width, height);
        batch.end();
    }

    public void draw_slingshot(float x,float y,float width,float height) {
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
