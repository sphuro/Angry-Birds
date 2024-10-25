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

public class LevelPassed extends Main implements Screen {
    private SpriteBatch batch;
    private OrthographicCamera camera;
    private Stage stage;
    private Viewport viewport;
    private Main game;
    private Texture passedbackground;
    private Texture star;
    private Instant start;
    private Texture next;
    private Image nextImage;
    private Texture menu;
    private Image menuImage;
    private Texture restart2;
    private Image restart2Image;

    private boolean paused,exited=false,restarting=false,nextc=false;

    public LevelPassed(Main game) {
        this.game = game;
        batch = new SpriteBatch();
        camera = new OrthographicCamera();
        viewport = new StretchViewport(1600, 900, camera);
        stage = new Stage(viewport, batch);
        passedbackground = new Texture("levelpassbackground.png");
        star = new Texture("filled star.png");
        next=new Texture("nextlevel.png");
        menu=new Texture("afterlevelmenu.png");
        restart2=new Texture("restart.png");
        restart2Image=new Image(restart2);
        nextImage=new Image(next);
        menuImage=new Image(menu);
        menuImage.setSize(100,100);
        menuImage.setPosition(520,150);
        nextImage.setSize(100,100);
        nextImage.setPosition(720,150);
        restart2Image.setSize(100,100);
        restart2Image.setPosition(920,150);
        menuImage.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("Menu clicked");
                exited=!exited;
            }
        });
        restart2Image.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("Restart clicked");
                restarting=!restarting;
            }
        });
        nextImage.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("Next clicked");
                nextc=true;
            }
        });
        start=Instant.now();
    }
    @Override
    public void show() {

    }

    public String draw() {
        batch.setProjectionMatrix(camera.combined);
        viewport.apply();
        stage.addActor(nextImage);
        stage.addActor(menuImage);
        stage.addActor(restart2Image);
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
            batch.draw(passedbackground,0,0,1600,900);
            if (Duration.between(start,Instant.now()).toMillis()>1000) {
                batch.draw(star,620,287,72,76);
            }
            if (Duration.between(start,Instant.now()).toMillis()>2000) {
                batch.draw(star,732,289,72,76);
            }
            if (Duration.between(start,Instant.now()).toMillis()>3000) {
                batch.draw(star,845,288,72,76);
            }
            if (Duration.between(start,Instant.now()).toMillis()>3000) {
                batch.draw(menu,520,150,100,100);
                batch.draw(restart2,920,150,100,100);
                batch.draw(next,720,150,100,100);
            }
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
