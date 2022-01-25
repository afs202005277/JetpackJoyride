package ldts.control;

import ldts.model.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class CollisionCheckerTest {
    private CollisionChecker collisionChecker;
    
    @BeforeEach
    void setUp(){
        collisionChecker = new CollisionChecker();
    }
    
    @Test
    void checkCollisions() {
        ArrayList<Element> elements = new ArrayList<>();
        // Lasers:
        elements.add(new Laser(new Position(3, 5), 1, 3));   // Horizontal laser at (3, 5)
        elements.add(new Laser(new Position(10, 15), 2, 2)); // Vertical laser at (10, 15)
        elements.add(new Laser(new Position(20, 30), 3, 5)); // 45 degrees to the left laser at (20, 30)
        elements.add(new Laser(new Position(35, 40), 4, 6)); // 45 degrees to the right laser at (35, 40)

        // Rockets:
        elements.add(new Rocket(50, 20)); // Rocket at (50, 20)

        ArrayList<Player> players = new ArrayList<>();
        players.add(new Player(3, 5));
        players.add(new Player(10, 15));
        players.add(new Player(20, 30));
        players.add(new Player(35, 40));
        players.add(new Player(50, 20));
        players.add(new Player(60, 60));

        //Player 0:
        Assertions.assertTrue(collisionChecker.checkCollision(elements.get(0), players.get(0)));
        for (int i=1;i<elements.size();i++)
            Assertions.assertFalse(collisionChecker.checkCollision(elements.get(i), players.get(0)));

        //Player 1:
        Assertions.assertFalse(collisionChecker.checkCollision(elements.get(0), players.get(1)));
        Assertions.assertTrue(collisionChecker.checkCollision(elements.get(1), players.get(1)));
        for (int i=2;i< elements.size();i++)
            Assertions.assertFalse(collisionChecker.checkCollision(elements.get(i), players.get(1)));

        //Player 2:
        Assertions.assertFalse(collisionChecker.checkCollision(elements.get(0), players.get(2)));
        Assertions.assertFalse(collisionChecker.checkCollision(elements.get(1), players.get(2)));
        Assertions.assertTrue(collisionChecker.checkCollision(elements.get(2), players.get(2)));
        for (int i=3;i< elements.size();i++){
            Assertions.assertFalse(collisionChecker.checkCollision(elements.get(i), players.get(2)));
        }

        //Player 3:
        for (int i=0;i< elements.size();i++){
            if (i==3)
                Assertions.assertTrue(collisionChecker.checkCollision(elements.get(i), players.get(3)));
            else
                Assertions.assertFalse(collisionChecker.checkCollision(elements.get(i), players.get(3)));
        }

        //Player 4:
        for (int i=0;i< elements.size();i++){
            if (i==4)
                Assertions.assertTrue(collisionChecker.checkCollision(elements.get(i), players.get(4)));
            else
                Assertions.assertFalse(collisionChecker.checkCollision(elements.get(i), players.get(4)));
        }

        //Player 5:
        for (Element element : elements) {
            Assertions.assertFalse(collisionChecker.checkCollision(element, players.get(5)));
        }

        //Coins:
        Coin coin = new Coin(3, 5);
        Assertions.assertTrue(collisionChecker.checkCollision(coin, players.get(0)));
        Assertions.assertFalse(collisionChecker.checkCollision(coin, players.get(1)));
    }
}
