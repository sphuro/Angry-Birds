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

public class ExitPage implements Screen {

    private SpriteBatch batch;
    private OrthographicCamera camera;
    private Stage stage;
    private Viewport viewport;
    private Main game;
    private Texture quitpage;
    private Image quitpageImage;
    private Texture tick;
    private Image tickImage;
    private Texture cross;
    private Image crossImage;

    private boolean settingsopen,quitopen,saveopen,playopen;

    public ExitPage(Main game) {
        this.game = game;
        batch = new SpriteBatch();
        camera = new OrthographicCamera();
        viewport = new StretchViewport(1600, 900, camera);
        stage = new Stage(viewport, batch);
        quitpage = new Texture("exitpage.png");
        quitpageImage = new Image(quitpage);
        tick = new Texture("tick.png");
        tickImage = new Image(tick);
        tickImage.setSize(128, 128);
        tickImage.setPosition(1000, 190);
        cross = new Texture("cross.png");
        crossImage = new Image(cross);
        crossImage.setSize(128, 128);
        crossImage.setPosition(500, 190);
        settingsopen=false;
        quitopen=true;
        saveopen=false;
        playopen=false;
        tickImage.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("Tick clicked");
                Gdx.app.exit();
            }
        });
        crossImage.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("Cross clicked");
                quitopen=false;
            }
        });
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float v) {
        viewport.apply();
        batch.begin();
        stage.addActor(crossImage);
        stage.addActor(tickImage);
        Gdx.input.setInputProcessor(stage);
        if (!quitopen) {
            game.setScreen(new MenuScreen(game));
        }
        else {
            batch.draw(quitpage,0,0,1600,900);
            batch.draw(cross,500,190,128,128);
            batch.draw(tick,1000,190,128,128);
        }
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
