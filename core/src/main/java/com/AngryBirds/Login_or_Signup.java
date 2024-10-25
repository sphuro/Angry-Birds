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

import javax.swing.event.ChangeListener;
import java.time.Duration;
import java.time.Instant;


public class Login_or_Signup extends Main implements Screen {
    private SpriteBatch batch;
    private OrthographicCamera camera;
    private Stage stage;
    private Viewport viewport;
    private Main game;
    private Texture background;
    private Image backgroundImage;
    private Texture login;
    private Image loginImage;
    private Texture signup;
    private Image signupImage;

    private boolean login1,signup1;

    public Login_or_Signup(Main game) {
        this.game = game;
        batch = new SpriteBatch();
        camera = new OrthographicCamera();
        viewport = new StretchViewport(1600, 900, camera);
        stage = new Stage(viewport, batch);
        background = new Texture("loginorsignupbackground.png");
        backgroundImage = new Image(background);
        login = new Texture("login.png");
        loginImage = new Image(login);
        loginImage.setSize(289*0.75f,75);
        loginImage.setPosition(530,400);
        signup = new Texture("signup.png");
        signupImage = new Image(signup);
        signupImage.setSize(289*0.75f,75);
        signupImage.setPosition(860,400);
        loginImage.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("Login clicked");
                login1 = true;
            }
        });
        signupImage.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("Sign Up clicked");
                signup1 = true;
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
        stage.addActor(loginImage);
        stage.addActor(signupImage);
        Gdx.input.setInputProcessor(stage);
        if (login1) {
            game.setScreen(new Login(game));
        }
        else if (signup1) {
            game.setScreen(new Signup(game));
        }
        else {
            batch.draw(background,0,0,viewport.getWorldWidth(),viewport.getWorldHeight());
            batch.draw(login,530,400,289*0.75f,75);
            batch.draw(signup,860,400,289*0.75f,75);
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
