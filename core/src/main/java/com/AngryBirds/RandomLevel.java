package com.AngryBirds;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
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
import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;

import static java.lang.Math.max;

public class RandomLevel extends Main implements Screen {
    private SpriteBatch batch;
    private OrthographicCamera camera;
    private Stage stage;
    private Viewport viewport;
    private Main game;
    private Texture background;
    private Image backgroundImage;
    private Texture blocks;
    private Image blocksImage;
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
    private Bird slingshot;
    private Bird first;
    private Bird second;
    private Bird third;
    private LevelStructure structure;
    private Slingshot sling;
    private World world;
    private InputMultiplexer multiplexer;
    private ArrayList<Slingshot> birds;
    private Instant birdy;
    private boolean setted = false;
    private CheckCollision lis;
    private Instant noo;

    private boolean paused,exited=false,restarting=false,failed=false,passed=false,nextc=false;

    public RandomLevel(Main game) {
        this.game = game;
        world = new World(new Vector2(0,-9.8f), true);
        batch = new SpriteBatch();
        camera = new OrthographicCamera();
        sling = new Slingshot(world,stage,"black",camera,game);
        viewport = new StretchViewport(1600, 900, camera);
        stage = new Stage(viewport, batch);
        blocks = new Texture("leveloneblocks.png");
        blocksImage = new Image(blocks);
        blocksImage.setSize(617*0.75f,512*0.75f);
        blocksImage.setPosition(900,230);
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
        Random random = new Random();
        birds = new ArrayList<>();
        for (int i=0;i<4;i++) {
            int curr = random.nextInt(4);
            String temp;
            if (curr==0) temp = "blue";
            else if (curr==1) temp = "red";
            else if (curr==2) temp = "yellow";
            else temp = "black";
            birds.add(new Slingshot(world,stage,temp,camera,game));
        }
        if (Objects.equals(birds.get(3).getColor(), "red"))  first = new RedBird(game);
        if (Objects.equals(birds.get(3).getColor(), "blue"))  first = new BlueBird(game);
        if (Objects.equals(birds.get(3).getColor(), "yellow"))  first = new YellowBird(game);
        if (Objects.equals(birds.get(3).getColor(), "black"))  first = new BlackBird(game);
        if (Objects.equals(birds.get(2).getColor(), "red"))  second = new RedBird(game);
        if (Objects.equals(birds.get(2).getColor(), "blue"))  second = new BlueBird(game);
        if (Objects.equals(birds.get(2).getColor(), "yellow"))  second = new YellowBird(game);
        if (Objects.equals(birds.get(2).getColor(), "black"))  second = new BlackBird(game);
        if (Objects.equals(birds.get(1).getColor(), "red"))  third = new RedBird(game);
        if (Objects.equals(birds.get(1).getColor(), "blue"))  third = new BlueBird(game);
        if (Objects.equals(birds.get(1).getColor(), "yellow"))  third = new YellowBird(game);
        if (Objects.equals(birds.get(1).getColor(), "black"))  third = new BlackBird(game);
//        slingshot=new RedBird(game);
//        first=new YellowBird(game);
//        second=new RedBird(game);
//        third=new BlackBird(game);
//        birdy = Instant.now();
//        birds = new ArrayList<>();
//        birds.add(new Slingshot(world,stage,"blue",camera,game));
//        birds.add(new Slingshot(world,stage,"black",camera,game));
//        birds.add(new Slingshot(world,stage,"red",camera,game));
//        birds.add(new Slingshot(world,stage,"yellow",camera,game));
        structure=new LevelStructure(game,world,camera);
        random = new Random();
        int ba = random.nextInt(3);
        if (ba==0) background = new Texture("levelonebackground.png");
        else if (ba==1) background = new Texture("leveltwobackground.png");
        else background = new Texture("levelthreebackground.png");
        backgroundImage = new Image(background);
        int rows = random.nextInt(5) + 3;
        String[] blockTypes = {"box", "stonebox", "glassbox"};
        String[] pigTypes = {"pig", "helmetpig", "kingpig"};
        boolean kingPigPlaced = false;

        for (int i = 0; i < rows; i++) {
            int height = (random.nextInt(5) + 3) * 81;

            for (int j = 0; j < height / 81; j++) {
                String blockType = blockTypes[random.nextInt(blockTypes.length)];
                structure.add(blockType, 900 + i * 100, 220 + j * 81, 81, 81);
            }

            String pigType;
            if (!kingPigPlaced && i == rows - 1) {
                pigType = "kingpig";
                kingPigPlaced = true;
            } else {
                pigType = pigTypes[random.nextInt(pigTypes.length - 1)];
            }

            structure.add(pigType, 900 + i * 100, 220 + height, 48, 48);
        }

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
        lis = new CheckCollision(world,game);
        world.setContactListener(lis);
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
        stage.addActor(blocksImage);
        multiplexer = new InputMultiplexer();
        if (birds.size()==0) multiplexer.setProcessors(stage);
        else multiplexer.setProcessors(stage, birds.get(0).getFirst());
        Gdx.input.setInputProcessor(multiplexer);
        if (Gdx.input.isKeyPressed(Input.Keys.W)) {
            int curr = 0;
            if (birds.size()<=1) curr = 1;
            else if (birds.size()==2) curr = 2;
            else curr = 3;
            pa = new LevelPassed(game,birds.size(),structure.calculate_score());
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
            game.setScreen(new RandomLevel(game));
        }
        else if (nextc) {
            game.setScreen(new RandomLevel(game));
        }
        else if (paused) {
            blocksImage.remove();
            batch.draw(background, 0, 0, viewport.getWorldWidth(), viewport.getWorldHeight());
            batch.draw(blocks, 0, 0, 0, 0);
//            slingshot.draw_slingshot(300,220,46,129);
//            first.draw(180,220,43,42);
//            second.draw(215,220,38,42);
//            third.draw(245,220,43,45);
            if (birds.size()>=2) first.draw(150,220,60,58);
            if (birds.size()>=3) second.draw(200,220,56,60);
            if (birds.size()>=4) third.draw(245,220,58,60);
            if (birds.size()>0) birds.get(0).helper();
            structure.draw();
//            batch.draw(birds, 180, 230, 104, 42);
            String out = ne.draw();
            if (Objects.equals(out, "paused")) paused=false;
            else if (Objects.equals(out, "exit")) game.setScreen(new MenuScreen(game));
            else if (Objects.equals(out, "restart")) game.setScreen(new RandomLevel(game));
        }
        else if (failed) {
            musicoffImage.remove();
            musiconImage.remove();
            soundoffImage.remove();
            soundonImage.remove();
            restartImage.remove();
            exitImage.remove();
            blocksImage.remove();
            pauseImage.remove();
            batch.draw(background, 0, 0,viewport.getWorldWidth(), viewport.getWorldHeight());
            batch.draw(blocks, 0, 0, 0, 0);
//            slingshot.draw_slingshot(300,220,46,129);
//            first.draw(180,220,43,42);
//            second.draw(215,220,38,42);
//            third.draw(245,220,43,45);
            structure.draw();
//            batch.draw(birds, 180, 230, 104, 42);
            String out = fa.draw();
            if (Objects.equals(out, "restart")) game.setScreen(new RandomLevel(game));
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
            pauseImage.remove();
            batch.draw(background, 0, 0,viewport.getWorldWidth(), viewport.getWorldHeight());
            batch.draw(blocks, 0, 0, 0, 0);
//            slingshot.draw_slingshot(300,220,46,129);
            if (birds.size()>=2) first.draw(150,220,60,58);
            if (birds.size()>=3) second.draw(200,220,56,60);
            if (birds.size()>=4) third.draw(245,220,58,60);
            structure.draw();
//            batch.draw(birds, 180, 230, 104, 42);
            String out = pa.draw();
            if (Objects.equals(out, "exit")) game.setScreen(new MenuScreen(game));
            else if (Objects.equals(out, "restart")) game.setScreen(new RandomLevel(game));
            else if (Objects.equals(out, "next")) game.setScreen(new RandomLevel(game));
        }
        else {
            musicoffImage.remove();
            musiconImage.remove();
            soundoffImage.remove();
            soundonImage.remove();
            restartImage.remove();
            exitImage.remove();
            batch.draw(background, 0, 0, viewport.getWorldWidth(), viewport.getWorldHeight());
            batch.draw(pause, 50, 750, 80, 80);
            stage.addActor(pauseImage);
            batch.draw(blocks,0, 0, 0, 0);
//            slingshot.draw_slingshot(300,220,46,129);
//            setScreen(new Slingshot());
//            sling.render();
            if (birds.size()>=2) first.draw(150,220,60,58);
            if (birds.size()>=3) second.draw(200,220,56,60);
            if (birds.size()>=4) third.draw(245,220,58,60);
            if (structure.checkpig()) {
                int curr = 0;
                if (birds.size()<=1) curr = 1;
                else if (birds.size()==2) curr = 2;
                else curr = 3;
                game.getStars().set(2,max(curr,game.getStars().get(2)));
                game.getScores().set(2,max(structure.calculate_score(),game.getScores().get(2)));
                pa = new LevelPassed(game,birds.size(),structure.calculate_score());
                passed=true;
            }
            if (birds.size()==0) {
                fa = new LevelFailed(game);
                failed = true;
            }
            if (birds.size()>0 && birds.get(0).isDestroyed() && Duration.between(birds.get(0).getNo(),Instant.now()).toSeconds()>5) {
                world.destroyBody(birds.get(0).getbody());
                birds.remove(0);
            }
            while (!lis.getTodestroy().isEmpty()) {
                if (!lis.getDestroyed().contains(lis.getTodestroy().get(0))) world.destroyBody(lis.getTodestroy().get(0));
                lis.getDestroyed().add(lis.getTodestroy().get(0));
                lis.getTodestroy().remove(0);
            }
            structure.draw();
            if (birds.size()>0) birds.get(0).helper();
//            batch.draw(birds, 180, 230, 104, 42);

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