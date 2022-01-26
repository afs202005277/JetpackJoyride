package ldts.control;

import ldts.model.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class CollisionCheckerTest {
    private List<Element> elements;
    private List<Player> players;
    
    @BeforeEach
    void setUp(){
        elements = new ArrayList<>();
        // Lasers:
        elements.add(new Laser(new Position(3, 5), 1, 3));   // Horizontal laser at (3, 5)
        elements.add(new Laser(new Position(10, 15), 2, 2)); // Vertical laser at (10, 15)
        elements.add(new Laser(new Position(20, 30), 3, 5)); // 45 degrees to the left laser at (20, 30)
        elements.add(new Laser(new Position(35, 40), 4, 6)); // 45 degrees to the right laser at (35, 40)
        // Rockets:
        elements.add(new Rocket(50, 20)); // Rocket at (50, 20)

        players = new ArrayList<>();
        players.add(new Player(3, 5));
        players.add(new Player(10, 15));
        players.add(new Player(20, 30));
        players.add(new Player(35, 40));
        players.add(new Player(50, 20));
        players.add(new Player(60, 60));
    }
    
    @Test
    void checkCollisions() {
        //Player 0:
        Assertions.assertTrue(elements.get(0).checkCollision(players.get(0).getPosition()));
        for (int i=1;i<elements.size();i++)
            Assertions.assertFalse(elements.get(i).checkCollision(players.get(0).getPosition()));

        //Player 1:
        Assertions.assertFalse(elements.get(0).checkCollision(players.get(1).getPosition()));
        Assertions.assertTrue(elements.get(1).checkCollision(players.get(1).getPosition()));
        for (int i=2;i< elements.size();i++)
            Assertions.assertFalse(elements.get(i).checkCollision(players.get(1).getPosition()));

        //Player 2:
        Assertions.assertFalse(elements.get(0).checkCollision(players.get(2).getPosition()));
        Assertions.assertFalse(elements.get(1).checkCollision(players.get(2).getPosition()));
        Assertions.assertTrue(elements.get(2).checkCollision(players.get(2).getPosition()));
        for (int i=3;i< elements.size();i++){
            Assertions.assertFalse(elements.get(i).checkCollision(players.get(2).getPosition()));
        }
        //Player 3:
        for (int i=0;i< elements.size();i++){
            if (i==3)
                Assertions.assertTrue(elements.get(i).checkCollision(players.get(3).getPosition()));
            else
                Assertions.assertFalse(elements.get(i).checkCollision(players.get(3).getPosition()));
        }
        //Player 4:
        for (int i=0;i< elements.size();i++){
            if (i==4)
                Assertions.assertTrue(elements.get(i).checkCollision(players.get(4).getPosition()));
            else
                Assertions.assertFalse(elements.get(i).checkCollision(players.get(4).getPosition()));
        }
        //Player 5:
        for (Element element : elements) {
            Assertions.assertFalse(element.checkCollision(players.get(5).getPosition()));
        }
        //Coins:
        Coin coin = new Coin(3, 5);
        Assertions.assertTrue(coin.checkCollision(players.get(0).getPosition()));
        Assertions.assertFalse(coin.checkCollision(players.get(1).getPosition()));
    }
}
