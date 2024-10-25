package com.AngryBirds;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
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
import com.badlogic.gdx.math.Vector2;

import javax.swing.event.ChangeListener;
import java.time.Duration;
import java.time.Instant;


public class LevelScreen extends Main implements Screen {
    private SpriteBatch batch;
    private OrthographicCamera camera;
    private Stage stage;
    private Viewport viewport;
    private Main game;
    private Texture background;
    private Image backgroundImage;
    private Texture one;
    private Texture two;
    private Texture three;
    private Image oneImage;
    private Image twoImage;
    private Image threeImage;
    private Texture four;
    private Texture five;
    private Texture six;
    private Texture seven;
    private Texture eight;
    private Texture back;
    private Image backImage;
    private Vector2 mouse;

    private boolean levelone,leveltwo,levelthree,backed;

    public LevelScreen(Main game) {
        this.game = game;
        batch = new SpriteBatch();
        camera = new OrthographicCamera();
        viewport = new StretchViewport(1600, 900, camera);
        stage = new Stage(viewport, batch);
        background = new Texture("levelbackground.png");
        backgroundImage = new Image(background);
        one = new Texture("levelone.png");
        two = new Texture("leveltwo.png");
        three = new Texture("levelthree.png");
        oneImage = new Image(one);
        twoImage = new Image(two);
        threeImage = new Image(three);
        four = new Texture("levelfour.png");
        five = new Texture("levelfive.png");
        six = new Texture("levelsix.png");
        seven = new Texture("levelseven.png");
        eight = new Texture("leveleight.png");
        back = new Texture("back.png");
        backImage = new Image(back);
        backImage.setPosition(660,125);
        backImage.setSize(272.5f,125);
        oneImage.setPosition(200,600);
        oneImage.setSize(150,150);
        twoImage.setPosition(550,600);
        twoImage.setSize(150,150);
        threeImage.setPosition(900,600);
        threeImage.setSize(150,150);
        levelone = false;
        leveltwo = false;
        levelthree = false;
        backed = false;
        oneImage.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("One clicked");
                levelone = true;
            }
        });
        twoImage.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("Two clicked");
                leveltwo = true;
            }
        });
        threeImage.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("Three clicked");
                levelthree = true;
            }
        });
        backImage.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("Back clicked");
                backed=true;
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
        stage.addActor(oneImage);
        stage.addActor(twoImage);
        stage.addActor(threeImage);
        stage.addActor(backImage);
        Gdx.input.setInputProcessor(stage);
        if (backed) {
            game.setScreen(new MenuScreen(game));
        }
        else if (levelone) {
            game.setScreen(new Levelone(game));
        }
        else if (leveltwo) {
            game.setScreen(new Leveltwo(game));
        }
        else if (levelthree) {
            game.setScreen(new Levelthree(game));
        }
        else {
            batch.draw(background, 0, 0, viewport.getWorldWidth(), viewport.getWorldHeight());
            batch.draw(back, 660, 125, 272.5f, 125);
            batch.draw(one, 200, 600, 150, 150);
            batch.draw(two, 550, 600, 150, 150);
            batch.draw(three, 900, 600, 150, 150);
            batch.draw(four, 1250, 600, 150, 150);
            batch.draw(five, 200, 350, 150, 150);
            batch.draw(six, 550, 350, 150, 150);
            batch.draw(seven, 900, 350, 150, 150);
            batch.draw(eight, 1250, 350, 150, 150);
            batch.end();
        }
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
