package com.AngryBirds;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Stage;

import java.time.Instant;

public class Slingshot extends ApplicationAdapter implements Screen {
    private OrthographicCamera camera;
    private SpriteBatch batch;
    private Texture birdTexture, slingshotTexture;
    private World world;
    private Vector2 sling;
    private Vector2 birdpos;
    private Box2DDebugRenderer debugRenderer;
    private ShapeRenderer shapeRenderer;
    private Body bird;
    private boolean destroyed = false;
    private boolean curr;
    private Sprite birdSprite, slingshotSprite;
    private Stage stage;
    private InputProcessor first;
    private Instant no;
    private Main game;
    private String color;

    public Instant getNo() {
        return no;
    }

    public InputProcessor getFirst() {
        return first;
    }

    public boolean isDestroyed() {
        return destroyed;
    }
    @Override
    public void create() {
        batch = new SpriteBatch();
        shapeRenderer = new ShapeRenderer();
        birdTexture = new Texture("black_without.png");
        slingshotTexture = new Texture("Slingshot.png");
        birdSprite = new Sprite(birdTexture);
        slingshotSprite = new Sprite(slingshotTexture);
        birdSprite.setSize(60, 60);
        slingshotSprite.setSize(80, 200);
        slingshotSprite.setOriginCenter();
        world = new World(new Vector2(0, -9.8f), true);
        debugRenderer = new Box2DDebugRenderer();
        sling = new Vector2(400, 200);
        birdpos = new Vector2(sling);
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.StaticBody;
        bodyDef.position.set(0, 0);
        Body floorBody = world.createBody(bodyDef);
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(1600, 0.4f);
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = 5f;
        fixtureDef.friction = 0.1f;
        floorBody.createFixture(fixtureDef);
        createBirdBody();
        setupInputProcessor();
    }

    public String getColor() {
        return color;
    }

    public Slingshot(World world, Stage stage, String bir, OrthographicCamera camera, Main game) {
        this.camera = camera;
        batch = new SpriteBatch();
        this.world = world;
        this.stage = stage;
        this.game = game;
        shapeRenderer = new ShapeRenderer();
        birdTexture = new Texture(bir+"_without.png");
        this.color = bir;
        slingshotTexture = new Texture("Slingshot.png");
        birdSprite = new Sprite(birdTexture);
        slingshotSprite = new Sprite(slingshotTexture);
        birdSprite.setSize(60, 60);
        slingshotSprite.setSize(80, 200);
        slingshotSprite.setOriginCenter();
        debugRenderer = new Box2DDebugRenderer();
        sling = new Vector2(400, 380);
        birdpos = new Vector2(sling);
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.StaticBody;
        bodyDef.position.set(0, 0);
        Body floorBody = world.createBody(bodyDef);
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(1600, 2.15f);
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = 5f;
        fixtureDef.friction = 0.2f;
        floorBody.createFixture(fixtureDef);
        bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.StaticBody;
        bodyDef.position.set(17.3f, 0);
        floorBody = world.createBody(bodyDef);
        shape = new PolygonShape();
        shape.setAsBox(1, 9);
        fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = 5f;
        fixtureDef.friction = 0.2f;
        floorBody.createFixture(fixtureDef);
        createBirdBody();
        setupInputProcessor();
    }

    private void createBirdBody() {
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(birdpos.x / 100f, birdpos.y / 100f);
        bird = world.createBody(bodyDef);
        CircleShape shape = new CircleShape();
        shape.setRadius(0.25f);
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = 1f;
        fixtureDef.restitution = 0.8f;
        fixtureDef.friction = 0.5f;
        bird.createFixture(fixtureDef);
        shape.dispose();
        bird.setActive(false);
    }

    public void setupInputProcessor() {
        first = new InputAdapter() {
            @Override
            public boolean touchDragged(int screenX, int screenY, int pointer) {
                if (destroyed) return false;
                Vector2 dragPosition = new Vector2(screenX, Gdx.graphics.getHeight() - screenY);
                if (dragPosition.dst(sling) < 10) {
                    curr = false;
                    return false;
                }
                curr = true;
                if (dragPosition.dst(sling) > 200) {
                    dragPosition = sling.cpy().add(dragPosition.sub(sling).nor().scl(200));
                }
                birdpos.set(dragPosition);
                return true;
            }

            @Override
            public boolean touchUp(int screenX, int screenY, int pointer, int button) {
                if (destroyed) return false;
                if (curr) {
                    curr = false;
                    Vector2 releaseForce = sling.cpy().sub(birdpos).scl(-1.1f);
                    launchBird(releaseForce);
                }
                return true;
            }
        };
    }

    public void launchBird(Vector2 force) {
        if (game.isSoundopen()) game.getBird().play();
        bird.setActive(true);
        bird.setTransform(birdpos.x / 100f, birdpos.y / 100f, 0);
        bird.setLinearVelocity(Vector2.Zero);
        bird.applyForceToCenter(force.scl(-1), true);
        destroyed = true;
        no = Instant.now();
    }

    @Override
    public void render(float v) {
        Gdx.gl.glClearColor(0, 0, 0, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        world.step(1 / 60f, 6, 2);
        camera.update();
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        birdSprite.setOriginCenter();
        birdSprite.setRotation((float)Math.toDegrees(bird.getAngle()/4));
        slingshotSprite.setPosition(sling.x-40, sling.y-170);
        slingshotSprite.draw(batch);
        if (!curr) {
            birdpos.set(bird.getPosition().x * 100f, bird.getPosition().y * 100f);
        }
        birdSprite.setPosition(birdpos.x - birdSprite.getWidth() / 2, birdpos.y - birdSprite.getHeight() / 2);
        birdSprite.draw(batch);
        batch.end();
        if (curr) {
            game.getSlingshot().play();
            renderTrajectory();
        }
        debugRenderer.render(world, camera.combined);
    }

    public Body gotbody() {
        return bird;
    }

    public void helper() {
//        Gdx.gl.glClearColor(0, 0, 0, 0);
//        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        world.step(1 / 60f, 6, 2);
        camera.update();
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        birdSprite.setOriginCenter();
        birdSprite.setRotation((float)Math.toDegrees(bird.getAngle()/4));
        slingshotSprite.setPosition(sling.x-40, sling.y-170);
        slingshotSprite.draw(batch);
        if (!curr) {
            birdpos.set(bird.getPosition().x * 100f, bird.getPosition().y * 100f);
        }
        birdSprite.setPosition(birdpos.x - birdSprite.getWidth() / 2, birdpos.y - birdSprite.getHeight() / 2);
        birdSprite.draw(batch);
        batch.end();
        if (curr) {
            if (game.isSoundopen()) game.getSlingshot().play();
            renderTrajectory();
        }
        debugRenderer.render(world, camera.combined);
//        if (!destroyed) setupInputProcessor();
    }



    private void renderTrajectory() {
        shapeRenderer.setProjectionMatrix(camera.combined);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(Color.BLACK);
        shapeRenderer.rectLine(new Vector2(birdpos.x+8, birdpos.y+8), new Vector2(sling.x-15,sling.y),10);
        shapeRenderer.rectLine(new Vector2(birdpos.x+8, birdpos.y+8), new Vector2(sling.x+22,sling.y),10);
        shapeRenderer.setColor(Color.RED);
        Vector2 velocity = sling.cpy().sub(birdpos).scl(-6f);
        Vector2 position = sling.cpy();
        for (int i = 0; i < 50 && position.y>50; i++) {
            Vector2 nextPosition = position.cpy().add(velocity.cpy().scl(-1 / 60f));
            if (i%3!=2) shapeRenderer.line(position, nextPosition);
            velocity.add(0, 9.8f);
            position = nextPosition;
        }
        shapeRenderer.end();
    }

    @Override
    public void show() {
    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        batch.dispose();
        birdTexture.dispose();
        slingshotTexture.dispose();
        debugRenderer.dispose();
        shapeRenderer.dispose();
    }

    public Body getbody() {
        return bird;
    }
}
