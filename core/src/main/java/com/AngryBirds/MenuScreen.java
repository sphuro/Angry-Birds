package com.AngryBirds;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import org.w3c.dom.Text;

import javax.swing.event.ChangeListener;
import java.time.Duration;
import java.time.Instant;


public class MenuScreen extends Main implements Screen {
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
    private Texture quitpage;
    private Image quitpageImage;
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
    private Texture tick;
    private Image tickImage;
    private Texture cross;
    private Image crossImage;
    private Instant start2;
    private Texture savebackground;
    private Texture loadingblank;
    private Texture loadingfull;

    private boolean settingsopen,quitopen,saveopen,playopen;

    public MenuScreen(Main game) {
        this.game = game;
        batch = new SpriteBatch();
        camera = new OrthographicCamera();
        viewport = new StretchViewport(1600, 900, camera);
        stage = new Stage(viewport, batch);
        background = new Texture("menubackground.png");
        backgroundImage = new Image(background);
        settingpage = new Texture("settingbackground.png");
        settingpageImage = new Image(settingpage);
        quitpage = new Texture("exitpage.png");
        quitpageImage = new Image(quitpage);
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
        tick = new Texture("tick.png");
        tickImage = new Image(tick);
        tickImage.setSize(128, 128);
        tickImage.setPosition(1000, 190);
        cross = new Texture("cross.png");
        crossImage = new Image(cross);
        crossImage.setSize(128, 128);
        crossImage.setPosition(500, 190);
        savebackground=new Texture("savebackground.png");
        loadingblank=new Texture("loadingblank.png");
        loadingfull=new Texture("loadingfull.png");
        settingsopen=false;
        quitopen=false;
        saveopen=false;
        playopen=false;
        musiconImage.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("Music clicked");
                game.setMusicopen(!game.isMusicopen());
            }
        });
        musicoffImage.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("Music clicked");
                game.setMusicopen(!game.isMusicopen());
            }
        });
        soundoffImage.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("Sound clicked");
                game.setSoundopen(!game.isSoundopen());
            }
        });
        soundonImage.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("Sound clicked");
                game.setSoundopen(!game.isSoundopen());
            }
        });
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
        saveImage.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("Save clicked");
                saveopen=true;
                start2=Instant.now();
            }
        });
        quitImage.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("Quit clicked");
                quitopen=true;
            }
        });
        settingImage.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("Setting clicked");
                settingsopen = !settingsopen;
            }
        });
        playImage.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("Play clicked");
                playopen = true;
            }
        });
    }
    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        viewport.apply();
        batch.begin();
        stage.addActor(soundonImage);
        stage.addActor(soundoffImage);
        stage.addActor(musiconImage);
        stage.addActor(musicoffImage);
        stage.addActor(crossImage);
        stage.addActor(tickImage);
        stage.addActor(playImage);
        stage.addActor(quitImage);
        stage.addActor(settingImage);
        stage.addActor(saveImage);
        Gdx.input.setInputProcessor(stage);
        if (playopen) {
            game.setScreen(new LevelScreen(game));
        }
        else if (settingsopen) {
            game.setScreen(new SettingPage(game));
        }
        else if (quitopen) {
            game.setScreen(new ExitPage(game));
        }
        else if (saveopen) {
            if (game.getGameContainer().getLevel()==1) game.setScreen(new Leveloneload(game,game.getGameContainer()));
            else if (game.getGameContainer().getLevel()==2) game.setScreen(new Leveltwoload(game,game.getGameContainer()));
            else if (game.getGameContainer().getLevel()==3) game.setScreen(new Levelthreeload(game,game.getGameContainer()));
        }
        else {
            musicoffImage.remove();
            musiconImage.remove();
            soundoffImage.remove();
            soundonImage.remove();
            crossImage.remove();
            tickImage.remove();
            batch.draw(background,0,0,viewport.getWorldWidth(),viewport.getWorldHeight());
            batch.draw(play,700,450,250,99);
            batch.draw(save,700,325,250,99);
            batch.draw(quit,700,200,250,99);
            batch.draw(setting,40,23,100,100);
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
        batch.dispose();
        stage.dispose();

    }
}
