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

public class LevelStructure extends Main implements Screen {
    private SpriteBatch batch;
    private OrthographicCamera camera;
    private Stage stage;
    private Viewport viewport;
    private Main game;
    private Texture box;
    private ArrayList<Log> logs;
    private ArrayList<Box> boxes;
    private ArrayList<Pig> pigs;
    private ArrayList<KingPig> kingpigs;
    private ArrayList<HelmetPig> helmetPigs;
    private ArrayList<Stonebox> stonebox;
    private ArrayList<StoneLog> stonelog;
    private ArrayList<GlassBox> glassbox;
    private World world;

    public LevelStructure(Main game,World world,OrthographicCamera camera) {
        this.game = game;
        this.world = world;
        batch = new SpriteBatch();
        this.camera = camera;
        viewport = new StretchViewport(1600, 900, camera);
        stage = new Stage(viewport, batch);
        box = new Texture("box.png");
        logs = new ArrayList<>();
        boxes = new ArrayList<>();
        pigs = new ArrayList<>();
        kingpigs = new ArrayList<>();
        helmetPigs = new ArrayList<>();
        stonebox = new ArrayList<>();
        stonelog = new ArrayList<>();
        glassbox = new ArrayList<>();
    }
    @Override
    public void show() {

    }

    public void destroyboxes() {
        for (Box box : boxes) {
            box.setHealth(0);
            world.destroyBody(box.getbody());
        }
    }

    public boolean checkpig() {
        int cnt = 0;
        for (Pig pig : pigs) {
            if (pig.gethealth()>0) cnt++;
        }
        for (KingPig pig : kingpigs) {
            if (pig.gethealth()>0) cnt++;
        }
        for (HelmetPig pig : helmetPigs) {
            if (pig.gethealth()>0) cnt++;
        }
        return cnt == 0;
    }

    public void draw() {
        batch.setProjectionMatrix(camera.combined);
        viewport.apply();
        batch.begin();
        for (Log i:logs) {
            i.draw();
        }
        for (Box i:boxes) {
            i.draw();
        }
        for (Pig i:pigs) {
            i.draw();
        }
        for (KingPig i:kingpigs) {
            i.draw();
        }
        for (HelmetPig i:helmetPigs) {
            i.draw();
        }
        for (Stonebox i:stonebox) {
            i.draw();
        }
        for (StoneLog i:stonelog) {
            i.draw();
        }
        for (GlassBox i:glassbox) {
            i.draw();
        }
        batch.end();
    }

    public void add(String material,float x,float y,float width,float height){
        if (Objects.equals(material, "log")) {
            logs.add(new Log(game,x,y,width,height,world,camera));
        }
        else if (Objects.equals(material, "box")) {
            boxes.add(new Box(game,x,y,width,height,world,camera));
        }
        else if (Objects.equals(material, "pig")) {
            pigs.add(new Pig(game,x,y,width,height,world,camera));
        }
        else if (Objects.equals(material, "kingpig")) {
            kingpigs.add(new KingPig(game,x,y,width,height,world,camera));
        }
        else if (Objects.equals(material, "helmetpig")) {
            helmetPigs.add(new HelmetPig(game,x,y,width,height,world,camera));
        }
        else if (Objects.equals(material, "stonebox")) {
            stonebox.add(new Stonebox(game,x,y,width,height,world,camera));
        }
        else if (Objects.equals(material, "stonelog")) {
            stonelog.add(new StoneLog(game,x,y,width,height,world,camera));
        }
        else if (Objects.equals(material, "glassbox")) {
            glassbox.add(new GlassBox(game,x,y,width,height,world,camera));
        }
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

    public int calculate_score() {
        int ans = 0;
        for (Log i:logs) {
            ans+=i.getInitial_health()-i.gethealth();
        }
        for (Box i:boxes) {
            ans+=i.getInitial_health()-i.gethealth();
        }
        for (Pig i:pigs) {
            ans+=i.getInitial_health()-i.gethealth();
        }
        for (KingPig i:kingpigs) {
            ans+=i.getInitial_health()-i.gethealth();
        }
        for (HelmetPig i:helmetPigs) {
            ans+=i.getInitial_health()-i.gethealth();
        }
        for (Stonebox i:stonebox) {
            ans+=i.getInitial_health()-i.gethealth();
        }
        for (StoneLog i:stonelog) {
            ans+=i.getInitial_health()-i.gethealth();
        }
        for (GlassBox i:glassbox) {
            ans+=i.getInitial_health()-i.gethealth();
        }
        return ans;
    }

    public ArrayList<Box> getBoxes() {
        return boxes;
    }

    public ArrayList<Log> getLogs() {
        return logs;
    }

    public ArrayList<Pig> getPigs() {
        return pigs;
    }

    public ArrayList<KingPig> getKingpigs() {
        return kingpigs;
    }

    public ArrayList<HelmetPig> getHelmetPigs() {
        return helmetPigs;
    }

    public ArrayList<Stonebox> getStonebox() {
        return stonebox;
    }

    public ArrayList<StoneLog> getStonelog() {
        return stonelog;
    }

    public ArrayList<GlassBox> getGlassbox() {
        return glassbox;
    }
}
