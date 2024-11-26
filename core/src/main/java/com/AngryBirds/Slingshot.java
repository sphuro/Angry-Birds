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
import java.util.Objects;

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
    private float maxStretchDistance = 200;
    private Sprite birdSprite, slingshotSprite;
    private Stage stage;
    private InputProcessor first;
    private Instant no;

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
        birdSprite.setSize(30, 50);
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

    public Slingshot(World world,Stage stage,String bir,OrthographicCamera camera) {
        this.camera = camera;
        batch = new SpriteBatch();
        this.world = world;
        this.stage = stage;
        shapeRenderer = new ShapeRenderer();
        birdTexture = new Texture(bir+"_without.png");
        slingshotTexture = new Texture("Slingshot.png");
        birdSprite = new Sprite(birdTexture);
        slingshotSprite = new Sprite(slingshotTexture);
        if(Objects.equals(bir, "black")){
            birdSprite.setSize(40, 50);
        }
        else{
            birdSprite.setSize(40, 40);
        }
        slingshotSprite.setSize(60, 130);
        slingshotSprite.setOriginCenter();
        debugRenderer = new Box2DDebugRenderer();
        sling = new Vector2(400, 320);
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
        fixtureDef.friction = 0.1f;
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
        fixtureDef.restitution = 0.7f;
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
                if (dragPosition.dst(sling) > maxStretchDistance) {
                    dragPosition = sling.cpy()
                        .add(dragPosition.sub(sling).nor().scl(maxStretchDistance));
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

    private void launchBird(Vector2 force) {
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
        birdSprite.setRotation((float)Math.toDegrees(bird.getAngle()/2));
        slingshotSprite.setPosition(sling.x-30, sling.y-100);
        slingshotSprite.draw(batch);
        if (!curr) {
            birdpos.set(bird.getPosition().x * 100f, bird.getPosition().y * 100f);
        }
        birdSprite.setPosition(birdpos.x - birdSprite.getWidth() / 2, birdpos.y - birdSprite.getHeight() / 2);
        birdSprite.draw(batch);
        batch.end();
        if (curr) {
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
        birdSprite.setRotation((float)Math.toDegrees(bird.getAngle()/2));
        slingshotSprite.setPosition(sling.x-30, sling.y-100);
        slingshotSprite.draw(batch);
        if (!curr) {
            birdpos.set(bird.getPosition().x * 100f, bird.getPosition().y * 100f);
        }
        birdSprite.setPosition(birdpos.x - birdSprite.getWidth() / 2, birdpos.y - birdSprite.getHeight() / 2);
        birdSprite.draw(batch);
        batch.end();
        if (curr) {
            renderTrajectory();
        }
        debugRenderer.render(world, camera.combined);
//        if (!destroyed) setupInputProcessor();
    }



    private void renderTrajectory() {
        shapeRenderer.setProjectionMatrix(camera.combined);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
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
