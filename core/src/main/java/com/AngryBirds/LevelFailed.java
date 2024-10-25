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

public class LevelFailed extends Main implements Screen {
    private SpriteBatch batch;
    private OrthographicCamera camera;
    private Stage stage;
    private Viewport viewport;
    private Main game;
    private Texture menu2;
    private Image menu2Image;
    private Texture restart3;
    private Image restart3Image;
    private Texture failbackground;

    private boolean paused,exited=false,restarting=false,nextc=false;

    public LevelFailed(Main game) {
        this.game = game;
        batch = new SpriteBatch();
        camera = new OrthographicCamera();
        viewport = new StretchViewport(1600, 900, camera);
        stage = new Stage(viewport, batch);
        failbackground=new Texture("levelfailbackground.png");
        menu2=new Texture("afterlevelmenu.png");
        menu2Image=new Image(menu2);
        menu2Image.setSize(100,100);
        menu2Image.setPosition(620,100);
        restart3=new Texture("restart.png");
        restart3Image=new Image(restart3);
        restart3Image.setSize(100,100);
        restart3Image.setPosition(880,100);
        menu2Image.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("Menu clicked");
                exited=!exited;
            }
        });
        restart3Image.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("Restart clicked");
                restarting=!restarting;
            }
        });
    }
    @Override
    public void show() {

    }

    public String draw() {
        batch.setProjectionMatrix(camera.combined);
        viewport.apply();
        stage.addActor(restart3Image);
        stage.addActor(menu2Image);
        batch.begin();
        Gdx.input.setInputProcessor(stage);
        if (exited) {
            return "exit";
        }
        else if (restarting) {
            return "restart";
        }
        else if (nextc) {
            return "next";
        }
        else {
            batch.draw(failbackground, 0, 0,viewport.getWorldWidth(), viewport.getWorldHeight());
            batch.draw(menu2,620,100,100,100);
            batch.draw(restart3,880,100,100,100);
        }
        batch.end();
        return "none";
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
