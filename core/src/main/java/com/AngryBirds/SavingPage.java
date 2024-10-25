package com.AngryBirds;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import java.time.Duration;
import java.time.Instant;

public class SavingPage implements Screen {

    private SpriteBatch batch;
    private OrthographicCamera camera;
    private Stage stage;
    private Viewport viewport;
    private Main game;
    private Instant start2;
    private Texture savebackground;
    private Texture loadingblank;
    private Texture loadingfull;

    public SavingPage(Main game) {
        start2=Instant.now();
        this.game = game;
        batch = new SpriteBatch();
        camera = new OrthographicCamera();
        viewport = new StretchViewport(1600, 900, camera);
        stage = new Stage(viewport, batch);
        savebackground=new Texture("savebackground.png");
        loadingblank=new Texture("loadingblank.png");
        loadingfull=new Texture("loadingfull.png");
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float v) {
        viewport.apply();
        batch.begin();
        Gdx.input.setInputProcessor(stage);
        batch.draw(savebackground, 0, 0,1600,900);
        batch.draw(loadingblank, 620, 200,400,40);
        if (Duration.between(start2, Instant.now()).toMillis() > 3920) {
            game.setScreen(new MenuScreen(game));
        }
        batch.draw(loadingfull, 624, 204, Duration.between(start2,Instant.now()).toMillis()*0.1f,32);
        batch.end();
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height, true);
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

    }
}
