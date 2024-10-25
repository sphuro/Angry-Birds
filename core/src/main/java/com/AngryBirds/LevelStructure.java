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
    private ArrayList<VerticalLog> verticalLogs;

    public LevelStructure(Main game) {
        this.game = game;
        batch = new SpriteBatch();
        camera = new OrthographicCamera();
        viewport = new StretchViewport(1600, 900, camera);
        stage = new Stage(viewport, batch);
        box = new Texture("box.png");
        logs = new ArrayList<>();
        boxes = new ArrayList<>();
        pigs = new ArrayList<>();
        kingpigs = new ArrayList<>();
        verticalLogs = new ArrayList<>();
    }
    @Override
    public void show() {

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
        for (VerticalLog i:verticalLogs) {
            i.draw();
        }
        batch.end();
    }

    public void add(String material,float x,float y,float width,float height){
        if (Objects.equals(material, "log")) {
            logs.add(new Log(game,x,y,width,height));
        }
        else if (Objects.equals(material, "box")) {
            boxes.add(new Box(game,x,y,width,height));
        }
        else if (Objects.equals(material, "pig")) {
            pigs.add(new Pig(game,x,y,width,height));
        }
        else if (Objects.equals(material, "kingpig")) {
            kingpigs.add(new KingPig(game,x,y,width,height));
        }
        else if (Objects.equals(material, "verticallog")) {
            verticalLogs.add(new VerticalLog(game,x,y,width,height));
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
}
