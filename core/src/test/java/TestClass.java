import com.AngryBirds.*;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Arrays;

public class TestClass {
//    private Slingshot slingshot;
//    private Main game;
//    private OrthographicCamera camera;
//    private World world;
    private int initscore;
    private int score;
    private Levelone levelone;
//    private LevelStructure levelStructure;
//    private ArrayList<Box> box;
//    private Stage stage;
//    private Levelone levelone;

//    @BeforeEach
//    public void setUp() {
//        game = new Main();
//        levelone = new Levelone(game);
//    }

//    @Test
//    public void testAndBirdLaunch() {
//        Vector2 initialBirdPosition = slingshot.getbody().getPosition();
//        slingshot.launchBird(new Vector2(10, 20));
//        Assertions.assertNotSame(initialBirdPosition, slingshot.getbody().getPosition(), "Bird should be launched");
//    }

//    @Test
//    public void checkscore() {
//        levelStructure.destroyboxes();
//        score = levelStructure.calculate_score();
//        initscore = 0;
//        box = levelStructure.getBoxes();
//        for (Box b : box) {
//            initscore += b.getInitial_health();
//        }
//        Assertions.assertEquals(initscore, score);
//    }


    @Test
    public void checkbirdlength() {
        ArrayList<String> bird = new ArrayList<>(Arrays.asList("black", "blue", "red", "yellow"));
        levelone = new Levelone(bird);
        int length = levelone.getbirds().size();
        Assertions.assertEquals(4, length);
    }

}
