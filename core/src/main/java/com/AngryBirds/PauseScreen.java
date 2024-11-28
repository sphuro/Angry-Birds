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

public class PauseScreen extends Main implements Screen {
    private SpriteBatch batch;
    private OrthographicCamera camera;
    private Stage stage;
    private Viewport viewport;
    private Main game;
    private Texture pause;
    private Image pauseImage;
    private Texture musicon;
    private Image musiconImage;
    private Texture musicoff;
    private Image musicoffImage;
    private Texture soundoff;
    private Image soundoffImage;
    private Texture soundon;
    private Image soundonImage;
    private Texture restart;
    private Image restartImage;
    private Texture exit;
    private Image exitImage;
    private Texture pausebackground;
    private Texture save;
    private Image saveImage;

    private boolean paused,exited=false,restarting=false,nextc=false,saved=false;

    public PauseScreen(Main game) {
        this.game = game;
        this.camera = new OrthographicCamera();
        batch = new SpriteBatch();
        viewport = new StretchViewport(1600,900, camera);
        stage = new Stage(viewport, batch);
        pause = new Texture("levelpause.png");
        pauseImage = new Image(pause);
        musicon = new Texture("musicon.png");
        musiconImage = new Image(musicon);
        musicoff = new Texture("musicoff.png");
        musicoffImage = new Image(musicoff);
        musiconImage.setSize(80,80);
        musicoffImage.setSize(80,80);
        musiconImage.setPosition(50,600);
        musicoffImage.setPosition(50,600);
        soundoff = new Texture("soundoff.png");
        soundoffImage = new Image(soundoff);
        soundon = new Texture("soundon.png");
        soundonImage = new Image(soundon);
        soundonImage.setSize(80,80);
        soundoffImage.setSize(80,80);
        soundonImage.setPosition(50,450);
        soundoffImage.setPosition(50,450);
        restart = new Texture("restart.png");
        restartImage = new Image(restart);
        restartImage.setSize(80,80);
        restartImage.setPosition(50,300);
        exit = new Texture("levelexit.png");
        exitImage = new Image(exit);
        exitImage.setSize(80,80);
        exitImage.setPosition(50,150);
        pauseImage.setSize(80,80);
        pauseImage.setPosition(50,750);
        save = new Texture("save2.png");
        saveImage = new Image(save);
        saveImage.setSize(80,80);
        saveImage.setPosition(50,20);
        pausebackground=new Texture("pausebackground.png");
        pauseImage.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("Pause clicked");
                paused=!paused;
            }
        });
        musicoffImage.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("Music clicked");
                game.setMusicopen(!game.isMusicopen());
                MusicHandler.save(game);
            }
        });
        musiconImage.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("Music clicked");
                game.setMusicopen(!game.isMusicopen());
                MusicHandler.save(game);
            }
        });
        soundoffImage.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("Music clicked");
                game.setSoundopen(!game.isSoundopen());
                SoundHandler.save(game);
            }
        });
        soundonImage.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("Music clicked");
                game.setSoundopen(!game.isSoundopen());
                SoundHandler.save(game);
            }
        });
        restartImage.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("Restart clicked");
                restarting=!restarting;
            }
        });
        exitImage.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("Exit clicked");
                exited=!exited;
            }
        });
        saveImage.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("Save clicked");
                saved=!saved;
            }
        });

    }
    @Override
    public void show() {

    }

    public String draw() {
        batch.setProjectionMatrix(camera.combined);
        viewport.apply();
        batch.begin();
        stage.addActor(pauseImage);
        stage.addActor(musiconImage);
        stage.addActor(soundonImage);
        stage.addActor(restartImage);
        stage.addActor(exitImage);
        stage.addActor(musicoffImage);
        stage.addActor(soundoffImage);
        stage.addActor(saveImage);
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
        else if (paused) {
            paused=false;
            return "paused";
        }
        else if (saved) {
            return "saved";
        }
        else {
            batch.draw(pausebackground,0,0,214,900);
            batch.draw(pause,50,750,80,80);
            if (game.isMusicopen()) batch.draw(musicon,50,600,80,80);
            else batch.draw(musicoff,50,600,80,80);
            if (game.isSoundopen()) batch.draw(soundon,50,450,80,80);
            else batch.draw(soundoff,50,450,80,80);
            batch.draw(restart,50,300,80,80);
            batch.draw(exit,50,150,80,80);
            batch.draw(save,50,20,80,80);
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
