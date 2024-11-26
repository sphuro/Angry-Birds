package com.AngryBirds;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;


public class GlassBox extends Main implements Screen,Materials {
    private SpriteBatch batch;
    private OrthographicCamera camera;
    private Stage stage;
    private Viewport viewport;
    private Main game;
    private Texture log;
    float x,y,width,height;
    private Body logb;
    private ShapeRenderer shapeRenderer;
    private float PPM;
    private World world;
    private boolean destroyed = false;
    private float density;
    private float restitution;
    private float friction;
    private float healthPoint;
    private Body body;
    private BodyDef bodyDef;
    private FixtureDef fixtureDef;
    private Sprite block_sprite;
    private Box2DDebugRenderer debugRenderer;

    public GlassBox(Main game, float x, float y, float width, float height, World world, OrthographicCamera camera) {
        this.game = game;
        this.camera = camera;
        debugRenderer = new Box2DDebugRenderer();
        batch = new SpriteBatch();
        camera = new OrthographicCamera();
        this.world = world;
        viewport = new StretchViewport(1600, 900, camera);
        stage = new Stage(viewport, batch);
        log = new Texture("glassbox.png");
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        density = 0.5f;
        restitution = 0.5f;
        friction = 10f;
        healthPoint = 200f;
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(x/100, y/100);
        body = world.createBody(bodyDef);
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(width/200, height/200);
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = 0.5f;
        fixtureDef.friction = 0.1f;
        Fixture fixture = body.createFixture(fixtureDef);
        fixture.setUserData(this);
        create();
    }
    @Override
    public void show() {

    }

    public Body getbody() {
        return body;
    }

    public void destroy() {
        destroyed = true;
    }

    public void draw() {
        if (destroyed) return;
        viewport.apply();
        batch.begin();
        Vector2 position = body.getPosition();
        batch.draw(log, (position.x * 100)-width/2, (position.y * 100)-height/2, width, height);
        batch.end();
        debugRenderer.render(world, camera.combined);
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
