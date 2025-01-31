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

import static java.lang.Math.max;

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
    private YellowBird first;
    private RedBird second;
    private BlackBird third;
    private LevelStructure structure;
    private Slingshot sling;
    private World world;
    private InputMultiplexer multiplexer;
    private ArrayList<Slingshot> birds;
    private Instant birdy;
    private boolean setted = false;
    private CheckCollision lis;
    private Instant noo;
    private static int score = 0;
    private ArrayList<String> addbird;
    private ArrayList<String> materials;

    private boolean paused,exited=false,restarting=false,failed=false,passed=false,nextc=false,saved=false;

    public Levelone(ArrayList<String> bird, ArrayList<String> materials) {
        this.addbird = bird;
        this.materials = materials;
    }

    public Levelone(Main game) {
        this.game = game;
        world = new World(new Vector2(0,-9.8f), true);
        batch = new SpriteBatch();
        camera = new OrthographicCamera();
        sling = new Slingshot(world,stage,"black",camera,game);
        viewport = new StretchViewport(1600, 900, camera);
        stage = new Stage(viewport, batch);
        background = new Texture("levelonebackground.png");
        backgroundImage = new Image(background);
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
        slingshot=new RedBird(game);
        first=new YellowBird(game);
        second=new RedBird(game);
        third=new BlackBird(game);
        birdy = Instant.now();
        birds = new ArrayList<>();
        addbird = new ArrayList<>();
        addbird.add("blue");
        addbird.add("black");
        addbird.add("red");
        addbird.add("yellow");
        addbirds();
        structure=new LevelStructure(game,world,camera);
        materials = new ArrayList<>();
        materials.add("box");
        materials.add("glassbox");
        materials.add("stonebox");
        materials.add("log");
        materials.add("pig");
        materials.add("kingpig");
        materials.add("helmetpig");
        makeStructure();
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

    public void addbirds(){
        for(String i:addbird){
            birds.add(new Slingshot(world,stage,i,camera,game));
        }
    }

    public ArrayList<String> getbirds() {
        System.out.println("length of birds: "+addbird.size());
        return addbird;
    }

    public void makeStructure(){
        for(String i: materials){
            if( i.equals("box")){
                structure.add(i,1050,220,81,81);
                structure.add(i,1212,220,81,81);
                structure.add(i,1050,301,81,81);
                structure.add(i,1131,301,81,81);
                structure.add(i,969,220,81,81);
                structure.add(i,969,301,81,81);
            }
            else if(i.equals("stonebox")){
                structure.add(i,1131,220,81,81);
                structure.add(i,969,382,81,81);
                structure.add(i,1050,463,81,81);
            }
            else if(i.equals("glassbox")){
                structure.add(i,1131,382,81,81);
            }
            else if(i.equals("log")){
                structure.add(i,1050,401,162,19);
            }
            else if(i.equals("pig")){
                structure.add(i,1212,268,60,60);
                structure.add(i,1050,349,60,60);
            }
            else if(i.equals("kingpig")){
                structure.add(i,1050,535,75,72);
            }
            else if(i.equals("helmetpig")){
                structure.add(i,969,430,60,60);
            }
        }
    }

    public ArrayList<String> getmaterials() {
        System.out.println("length of materials: "+materials.size());
        return materials;
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
            int curr = birds.size();
            if (curr>0 && birds.get(0).isDestroyed()) curr--;
            int stars;
            if (curr==0) stars = 1;
            else if (curr==1) stars = 2;
            else stars = 3;
            game.getStars().set(0,max(stars,game.getStars().get(0)));
            game.getScores().set(0,max(structure.calculate_score(),game.getScores().get(0)));
            pa = new LevelPassed(game,stars,structure.calculate_score());
            StarsHandler.save(game);
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
        else if (saved) {
            paused = false;
            game.setGameContainer(new Container());
            game.getGameContainer().setLevel(1);
            for (Log i:structure.getLogs()) {
                ArrayList<Float> toadd = new ArrayList<>();
                toadd.add(i.getbody().getPosition().x);
                toadd.add(i.getbody().getPosition().y);
                toadd.add(i.width);
                toadd.add(i.height);
                toadd.add((float)i.gethealth());
                game.getGameContainer().getLog().add(toadd);
            }
            for (Box i: structure.getBoxes()) {
                ArrayList<Float> toadd = new ArrayList<>();
                toadd.add(i.getbody().getPosition().x);
                toadd.add(i.getbody().getPosition().y);
                toadd.add(i.width);
                toadd.add(i.height);
                toadd.add((float)i.gethealth());
                game.getGameContainer().getBox().add(toadd);
            }
            for (Pig i:structure.getPigs()) {
                ArrayList<Float> toadd = new ArrayList<>();
                toadd.add(i.getbody().getPosition().x);
                toadd.add(i.getbody().getPosition().y);
                toadd.add(i.width);
                toadd.add(i.height);
                toadd.add((float)i.gethealth());
                game.getGameContainer().getPig().add(toadd);
            }
            for (KingPig i: structure.getKingpigs()) {
                ArrayList<Float> toadd = new ArrayList<>();
                toadd.add(i.getbody().getPosition().x);
                toadd.add(i.getbody().getPosition().y);
                toadd.add(i.width);
                toadd.add(i.height);
                toadd.add((float)i.gethealth());
                game.getGameContainer().getKingpig().add(toadd);
            }
            for (HelmetPig i:structure.getHelmetPigs()) {
                ArrayList<Float> toadd = new ArrayList<>();
                toadd.add(i.getbody().getPosition().x);
                toadd.add(i.getbody().getPosition().y);
                toadd.add(i.width);
                toadd.add(i.height);
                toadd.add((float)i.gethealth());
                game.getGameContainer().getHelmetpig().add(toadd);
            }
            for (Stonebox i: structure.getStonebox()) {
                ArrayList<Float> toadd = new ArrayList<>();
                toadd.add(i.getbody().getPosition().x);
                toadd.add(i.getbody().getPosition().y);
                toadd.add(i.width);
                toadd.add(i.height);
                toadd.add((float)i.gethealth());
                game.getGameContainer().getStonebox().add(toadd);
            }
            for (StoneLog i: structure.getStonelog()) {
                ArrayList<Float> toadd = new ArrayList<>();
                toadd.add(i.getbody().getPosition().x);
                toadd.add(i.getbody().getPosition().y);
                toadd.add(i.width);
                toadd.add(i.height);
                toadd.add((float)i.gethealth());
                game.getGameContainer().getStonelog().add(toadd);
            }
            for (GlassBox i: structure.getGlassbox()) {
                ArrayList<Float> toadd = new ArrayList<>();
                toadd.add(i.getbody().getPosition().x);
                toadd.add(i.getbody().getPosition().y);
                toadd.add(i.width);
                toadd.add(i.height);
                toadd.add((float)i.gethealth());
                game.getGameContainer().getGlassbox().add(toadd);
            }
            int curr = birds.size();
            if (curr>0 && birds.get(0).isDestroyed()) {
                curr--;
            }
            game.getGameContainer().setBirds(curr);
            ContainerHandler.save(game);
            saved = false;
            game.setScreen(new SavingPage(game));
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
            else if (Objects.equals(out, "restart")) game.setScreen(new Levelone(game));
            else if (Objects.equals(out,"saved")) saved = true;
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
                int curr = birds.size();
                if (curr>0 && birds.get(0).isDestroyed()) curr--;
                int stars;
                if (curr==0) stars = 1;
                else if (curr==1) stars = 2;
                else stars = 3;
                game.getStars().set(0,max(stars,game.getStars().get(0)));
                game.getScores().set(0,max(structure.calculate_score(),game.getScores().get(0)));
                pa = new LevelPassed(game,stars,structure.calculate_score());
                StarsHandler.save(game);
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
