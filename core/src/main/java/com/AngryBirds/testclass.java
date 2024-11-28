
package com.AngryBirds;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;

public class testclass {
    private Slingshot slingshot;
    private Main game;
    private OrthographicCamera camera;
    private World world;
    private int initscore;
    private int score;
    private LevelStructure levelStructure;
    private ArrayList<Box> box;
    private Stage stage;

    @BeforeEach
    public void setUp() {
        game = new Main();
        camera = new OrthographicCamera();
        world = new World(new Vector2(0, -9.8f), true);
        slingshot = new Slingshot(world,stage,"red",camera,game);
        levelStructure = new LevelStructure(game, world, camera);
    }

    @Test
    public void testScoreCalculationAndBirdLaunch() {
        Vector2 inititalbirdPosition = slingshot.getbody().getPosition();
        slingshot.launchBird(new Vector2(10, 20));
        Assertions.assertNotSame((Vector2)inititalbirdPosition, (Vector2) slingshot.getbody().getPosition(), "Bird should be launched");
    }

    @Test
    public void checkscore(){
        levelStructure.destroyboxes();
        score = levelStructure.calculate_score();
        initscore = 0;
        box = levelStructure.getBoxes();
        for(Box b : box){
            initscore += b.getHealth();
        }
        Assertions.assertEquals(initscore, score);
    }


}
