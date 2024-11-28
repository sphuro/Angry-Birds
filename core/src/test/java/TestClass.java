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
    private int initscore;
    private int score;
    private Levelone levelone;
    private Box box;
    private Stonebox stonebox;
    private GlassBox glassbox;


    @BeforeEach
    public void setUp() {
        ArrayList<String> bird = new ArrayList<>(Arrays.asList("black", "blue", "red", "yellow"));
        ArrayList<String> materials = new ArrayList<>(Arrays.asList("glassbox", "stonebox", "box", "pig", "kingpig", "helmetpig", "log"));
        levelone = new Levelone(bird, materials);
    }



    @Test
    public void checkBirds() {
        int length = levelone.getbirds().size();
        Assertions.assertEquals(4, length, "Birds should be 4 as we added 4 types of the birds");

    }

    @Test
    public void checkMaterials(){
        int length = levelone.getmaterials().size();
        Assertions.assertEquals(7, length, "Materials should be 7 as we added 7 types of the materials");
    }

    @Test
    public void checkStoneHealth(){
        stonebox = new Stonebox();
        int health = stonebox.getHealth();
        Assertions.assertEquals(300, health, "Stone health should be 300");
        int damage = 100;
        stonebox.setHealth(health - damage);
        health = stonebox.getHealth();
        Assertions.assertEquals(200, health, "Stone health should be 200 after 100 damage");
    }

    @Test
    public void checkGlassHealth(){
        glassbox = new GlassBox();
        int health = glassbox.getHealth();
        Assertions.assertEquals(100, health, "Glass health should be 100");
        int damage = 50;
        glassbox.setHealth(health - damage);
        health = glassbox.getHealth();
        Assertions.assertEquals(50, health, "Glass health should be 50 after 50 damage");
    }

    @Test
    public void checkBoxHealth(){
        box = new Box();
        int health = box.getHealth();
        Assertions.assertEquals(150, health, "Box health should be 200");
        int damage = 100;
        box.setHealth(health - damage);
        health = box.getHealth();
        Assertions.assertEquals(50, health, "Box health should be 100 after 100 damage");
    }

    

}
