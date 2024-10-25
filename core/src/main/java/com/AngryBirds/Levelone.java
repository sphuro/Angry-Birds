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
import java.util.Objects;

public class Levelone extends Main implements Screen {
    private SpriteBatch batch;
    private OrthographicCamera camera;
    private Stage stage;
    private Viewport viewport;
    private Main game;
    private Texture background;
    private Image backgroundImage;
    private Texture blocks;
    private Image blocksImage;
    private Texture birds;
    private Image birdsImage;
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
    private PauseScreen ne;
    private LevelPassed pa;
    private LevelFailed fa;
    private RedBird slingshot;
    private RedBird first;
    private BlueBird second;
    private BlackBird third;
    private LevelStructure structure;

    private boolean paused,exited=false,restarting=false,failed=false,passed=false,nextc=false;

    public Levelone(Main game) {
        this.game = game;
        batch = new SpriteBatch();
        camera = new OrthographicCamera();
        viewport = new StretchViewport(1600, 900, camera);
        stage = new Stage(viewport, batch);
        background = new Texture("levelonebackground.png");
        backgroundImage = new Image(background);
        blocks = new Texture("leveloneblocks.png");
        blocksImage = new Image(blocks);
        blocksImage.setSize(617*0.75f,512*0.75f);
        blocksImage.setPosition(900,230);
        birds = new Texture("levelonebirds.png");
        birdsImage = new Image(birds);
        birdsImage.setSize(104,42);
        birdsImage.setPosition(180,230);
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
        slingshot=new RedBird(game);
        first=new RedBird(game);
        second=new BlueBird(game);
        third=new BlackBird(game);
        structure=new LevelStructure(game);
        structure.add("box",1050,220,81,81);
        structure.add("box",1131,220,81,81);
        structure.add("box",1212,220,81,81);
        structure.add("box",1050,301,81,81);
        structure.add("box",1131,301,81,81);
        structure.add("box",1090,382,81,81);
        structure.add("log",1050,463,162,19);
        structure.add("box",1050,482,81,81);
        structure.add("box",1131,482,81,81);
        structure.add("log",1171,382,162,19);
        structure.add("pig",1106,398,48,48);
        structure.add("pig",1066,236,48,48);
        structure.add("pig",1236,301,48,48);
        structure.add("kingpig",1240,401,64,72);
        structure.add("verticallog",1300,220,19,162);
        pauseImage.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("Pause clicked");
                ne = new PauseScreen(game);
                paused=!paused;
            }
        });
        musicoffImage.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("Music clicked");
                game.setMusicopen(!game.isMusicopen());
            }
        });
        musiconImage.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("Music clicked");
                game.setMusicopen(!game.isMusicopen());
            }
        });
        soundoffImage.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("Music clicked");
                game.setSoundopen(!game.isSoundopen());
            }
        });
        soundonImage.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("Music clicked");
                game.setSoundopen(!game.isSoundopen());
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

    }
    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
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
        stage.addActor(birdsImage);
        stage.addActor(blocksImage);
        Gdx.input.setInputProcessor(stage);
        if (Gdx.input.isKeyPressed(Input.Keys.W)) {
            pa = new LevelPassed(game);
            passed=true;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.L)) {
            fa = new LevelFailed(game);
            failed=true;
        }
        if (exited) {
            game.setScreen(new MenuScreen(game));
        }
        else if (restarting) {
            game.setScreen(new Levelone(game));
        }
        else if (nextc) {
            game.setScreen(new Leveltwo(game));
        }
        else if (paused) {
            birdsImage.remove();
            blocksImage.remove();
            batch.draw(background, 0, 0, viewport.getWorldWidth(), viewport.getWorldHeight());
            batch.draw(blocks, 0, 0, 0, 0);
            slingshot.draw_slingshot(300,220,46,129);
            first.draw(180,220,43,42);
            second.draw(215,220,38,42);
            third.draw(245,220,43,45);
            structure.draw();
//            batch.draw(birds, 180, 230, 104, 42);
            String out = ne.draw();
            if (Objects.equals(out, "paused")) paused=false;
            else if (Objects.equals(out, "exit")) game.setScreen(new MenuScreen(game));
            else if (Objects.equals(out, "restart")) game.setScreen(new Levelone(game));
        }
        else if (failed) {
            musicoffImage.remove();
            musiconImage.remove();
            soundoffImage.remove();
            soundonImage.remove();
            restartImage.remove();
            exitImage.remove();
            blocksImage.remove();
            birdsImage.remove();
            pauseImage.remove();
            batch.draw(background, 0, 0,viewport.getWorldWidth(), viewport.getWorldHeight());
            batch.draw(blocks, 0, 0, 0, 0);
            slingshot.draw_slingshot(300,220,46,129);
            first.draw(180,220,43,42);
            second.draw(215,220,38,42);
            third.draw(245,220,43,45);
            structure.draw();
//            batch.draw(birds, 180, 230, 104, 42);
            String out = fa.draw();
            if (Objects.equals(out, "restart")) game.setScreen(new Levelone(game));
            else if (Objects.equals(out, "exit")) game.setScreen(new MenuScreen(game));
        }
        else if (passed) {
            musicoffImage.remove();
            musiconImage.remove();
            soundoffImage.remove();
            soundonImage.remove();
            restartImage.remove();
            exitImage.remove();
            blocksImage.remove();
            birdsImage.remove();
            pauseImage.remove();
            batch.draw(background, 0, 0,viewport.getWorldWidth(), viewport.getWorldHeight());
            batch.draw(blocks, 0, 0, 0, 0);
            slingshot.draw_slingshot(300,220,46,129);
            first.draw(180,220,43,42);
            second.draw(215,220,38,42);
            third.draw(245,220,43,45);
            structure.draw();
//            batch.draw(birds, 180, 230, 104, 42);
            String out = pa.draw();
            if (Objects.equals(out, "exit")) game.setScreen(new MenuScreen(game));
            else if (Objects.equals(out, "restart")) game.setScreen(new Levelone(game));
            else if (Objects.equals(out, "next")) game.setScreen(new Leveltwo(game));
        }
        else {
            musicoffImage.remove();
            musiconImage.remove();
            soundoffImage.remove();
            soundonImage.remove();
            restartImage.remove();
            exitImage.remove();
            batch.draw(background, 0, 0, viewport.getWorldWidth(), viewport.getWorldHeight());
            batch.draw(blocks,0, 0, 0, 0);
            slingshot.draw_slingshot(300,220,46,129);
            first.draw(180,220,43,42);
            second.draw(215,220,38,42);
            third.draw(245,220,43,45);
            structure.draw();
//            batch.draw(birds, 180, 230, 104, 42);
            batch.draw(pause, 50, 750, 80, 80);
        }
        batch.end();
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
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
