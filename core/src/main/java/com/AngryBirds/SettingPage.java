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

public class SettingPage implements Screen {

    private SpriteBatch batch;
    private OrthographicCamera camera;
    private Stage stage;
    private Viewport viewport;
    private Main game;
    private Texture background;
    private Image backgroundImage;
    private Texture setting;
    private Image settingImage;
    private Texture settingpage;
    private Image settingpageImage;
    private Texture quit;
    private Image quitImage;
    private Texture save;
    private Image saveImage;
    private Texture play;
    private Image playImage;
    private Texture soundon;
    private Image soundonImage;
    private Texture soundoff;
    private Image soundoffImage;
    private Texture musicon;
    private Image musiconImage;
    private Texture musicoff;
    private Image musicoffImage;

    private boolean settingsopen;

    public SettingPage(Main game) {
        this.game = game;
        batch = new SpriteBatch();
        camera = new OrthographicCamera();
        viewport = new StretchViewport(1600, 900, camera);
        stage = new Stage(viewport, batch);
        background = new Texture("menubackground.png");
        backgroundImage = new Image(background);
        settingpage = new Texture("settingbackground.png");
        settingpageImage = new Image(settingpage);
        save = new Texture("save.png");
        saveImage = new Image(save);
        saveImage.setSize(250,99);
        saveImage.setPosition(700,325);
        play = new Texture("play.png");
        playImage = new Image(play);
        playImage.setSize(250,99);
        playImage.setPosition(700,450);
        quit = new Texture("quit.png");
        quitImage = new Image(quit);
        quitImage.setSize(250,99);
        quitImage.setPosition(700,200);
        setting = new Texture("setting.png");
        settingImage = new Image(setting);
        settingImage.setSize(100,100);
        settingImage.setPosition(40,23);
        soundon = new Texture("soundon.png");
        soundonImage = new Image(soundon);
        soundonImage.setSize(80, 80);
        soundonImage.setPosition(260, 35);
        soundoff = new Texture("soundoff.png");
        soundoffImage = new Image(soundoff);
        soundoffImage.setSize(80, 80);
        soundoffImage.setPosition(260, 35);
        musicon = new Texture("musicon.png");
        musiconImage = new Image(musicon);
        musiconImage.setSize(80, 80);
        musiconImage.setPosition(160, 35);
        musicoff = new Texture("musicoff.png");
        musicoffImage = new Image(musicoff);
        musicoffImage.setSize(80, 80);
        musicoffImage.setPosition(160, 35);
        settingsopen=false;
        musiconImage.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("Music clicked");
                game.setMusicopen(!game.isMusicopen());
                MusicHandler.save(game);
            }
        });
        musicoffImage.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("Music clicked");
                game.setMusicopen(!game.isMusicopen());
                MusicHandler.save(game);
            }
        });
        soundoffImage.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("Sound clicked");
                game.setSoundopen(!game.isSoundopen());
                SoundHandler.save(game);
            }
        });
        soundonImage.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("Sound clicked");
                game.setSoundopen(!game.isSoundopen());
                SoundHandler.save(game);
            }
        });
        settingImage.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("Setting clicked");
                settingsopen = !settingsopen;
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
        stage.addActor(soundonImage);
        stage.addActor(soundoffImage);
        stage.addActor(musiconImage);
        stage.addActor(musicoffImage);
        stage.addActor(playImage);
        stage.addActor(quitImage);
        stage.addActor(settingImage);
        stage.addActor(saveImage);
        Gdx.input.setInputProcessor(stage);
        if (settingsopen) {
            game.setScreen(new MenuScreen(game));
        }
        else {
            playImage.remove();
            saveImage.remove();
            quitImage.remove();
            batch.draw(settingpage, 0, 0,1600,900);
            batch.draw(setting,40,23,100,100);
            if (game.isMusicopen()) batch.draw(musicon,160,35,80,80);
            else batch.draw(musicoff,160,35,80,80);
            if (game.isSoundopen()) batch.draw(soundon,260,35,80,80);
            else batch.draw(soundoff,260,35,80,80);
            batch.draw(play,700,450,250,99);
            batch.draw(save,700,325,250,99);
            batch.draw(quit,700,200,250,99);
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
