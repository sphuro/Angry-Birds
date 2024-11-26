package com.AngryBirds;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
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

public class Log2 extends Main implements Screen,Materials {
    SpriteBatch batch;
    Texture texture;
    Sprite sprite;
    World world;
    Body body;

    @Override
    public void create() {
        batch = new SpriteBatch();
        texture = new Texture("log.png");
        sprite = new Sprite(texture);
        sprite.setSize(1, 1); // Set size in meters
        sprite.setOriginCenter();

        world = new World(new Vector2(0, -9.8f), true);

        // Create body
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(0, 5);

        body = world.createBody(bodyDef);

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(0.5f, 0.5f); // Half-width and height

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = 1f;
        fixtureDef.friction = 0.5f;

        body.createFixture(fixtureDef);
        shape.dispose();
    }

    public Body getbody() {
        return body;
    }

    public void destroy() {
        boolean destroyed = true;
    }

    @Override
    public void render() {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // Update sprite position
        sprite.setPosition(
            body.getPosition().x - sprite.getWidth() / 2,
            body.getPosition().y - sprite.getHeight() / 2
        );

        // Render sprite
        batch.begin();
        sprite.draw(batch);
        batch.end();

        // Step physics world
        world.step(1 / 60f, 6, 2);
    }

    @Override
    public void draw() {

    }

    @Override
    public void show() {

    }

    @Override
    public void render(float v) {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        batch.dispose();
        texture.dispose();
        world.dispose();
    }
}
