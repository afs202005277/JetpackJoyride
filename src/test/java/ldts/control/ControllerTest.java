package ldts.control;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;
import ldts.model.*;
import ldts.view.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;

public class ControllerTest {
    private Controller control;
    private BackgroundView backViewer;
    private PlayerView playerViewer;
    private Screen screen;
    private TextGraphics graphics;

    @BeforeEach
    void setUp() throws IOException, URISyntaxException, FontFormatException {
        control = Controller.getInstance();
        backViewer = Mockito.mock(BackgroundView.class);
        playerViewer = Mockito.mock(PlayerView.class);
        screen = Mockito.mock(Screen.class);
        Mockito.when(screen.getTerminalSize()).thenReturn(new TerminalSize(51, 18));
        graphics = Mockito.mock(TextGraphics.class);
        View.setScreen(screen);
        control.setScreen(screen);
        View.setGraphics(graphics);
        control.setBackgroundView(backViewer);
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
        Assertions.assertTrue(control.checkCollisions(elements.get(0), players.get(0)));
        for (int i=1;i<elements.size();i++)
            Assertions.assertFalse(control.checkCollisions(elements.get(i), players.get(0)));

        //Player 1:
        Assertions.assertFalse(control.checkCollisions(elements.get(0), players.get(1)));
        Assertions.assertTrue(control.checkCollisions(elements.get(1), players.get(1)));
        for (int i=2;i< elements.size();i++)
            Assertions.assertFalse(control.checkCollisions(elements.get(i), players.get(1)));

        //Player 2:
        Assertions.assertFalse(control.checkCollisions(elements.get(0), players.get(2)));
        Assertions.assertFalse(control.checkCollisions(elements.get(1), players.get(2)));
        Assertions.assertTrue(control.checkCollisions(elements.get(2), players.get(2)));
        for (int i=3;i< elements.size();i++){
            Assertions.assertFalse(control.checkCollisions(elements.get(i), players.get(2)));
        }

        //Player 3:
        for (int i=0;i< elements.size();i++){
            if (i==3)
                Assertions.assertTrue(control.checkCollisions(elements.get(i), players.get(3)));
            else
                Assertions.assertFalse(control.checkCollisions(elements.get(i), players.get(3)));
        }

        //Player 4:
        for (int i=0;i< elements.size();i++){
            if (i==4)
                Assertions.assertTrue(control.checkCollisions(elements.get(i), players.get(4)));
            else
                Assertions.assertFalse(control.checkCollisions(elements.get(i), players.get(4)));
        }

        //Player 5:
        for (Element element : elements) {
            Assertions.assertFalse(control.checkCollisions(element, players.get(5)));
        }

        //Coins:
        Coin coin = new Coin(3, 5);
        Assertions.assertTrue(control.checkCollisions(coin, players.get(0)));
        Assertions.assertFalse(control.checkCollisions(coin, players.get(1)));
    }

    @Test
    void generateObjects() {
        control.setElements(new ArrayList<>());
        for (int i=0;i<=30;i++){
            control.generateObjects(i);
        }
        Assertions.assertEquals(7, control.getElements().size());
    }

    @Test
    void drawElements() throws IOException {
        ArrayList<Element> elements = new ArrayList<>();
        elements.add(new Laser());
        elements.add(new Laser());
        elements.add(new Rocket());
        elements.add(new Coin());
        elements.add(new Coin());
        elements.add(new Coin());
        control.setElements(elements);
        CounterView coinCounter = Mockito.mock(CounterView.class), distanceCounter = Mockito.mock(CounterView.class);
        Mockito.when(coinCounter.getUnits()).thenReturn("coins");
        Mockito.when(distanceCounter.getUnits()).thenReturn("metros");
        LaserView lView = Mockito.mock(LaserView.class);
        RocketView rView = Mockito.mock(RocketView.class);
        CoinView coinView = Mockito.mock(CoinView.class);
        BackgroundView backgroundView = Mockito.mock(BackgroundView.class);
        control.setCoinsCounterView(coinCounter);
        control.setDistanceCounterView(distanceCounter);
        control.setLaserView(lView);
        control.setRocketView(rView);
        control.setCoinView(coinView);
        control.setBackgroundView(backgroundView);

        control.drawElements(2, 5);
        Mockito.verify(screen, Mockito.times(1)).clear();

        Mockito.verify(backgroundView, Mockito.times(1)).draw(new Position(0, 1));

        Mockito.verify(coinView, Mockito.times(1)).draw(elements.get(3).getPosition());
        Mockito.verify(coinView, Mockito.times(1)).draw(elements.get(4).getPosition());
        Mockito.verify(coinView, Mockito.times(1)).draw(elements.get(5).getPosition());

        Mockito.verify(lView, Mockito.times(1)).draw(elements.get(0).getPosition(), ((Laser) elements.get(0)).getLastPosition());
        Mockito.verify(lView, Mockito.times(1)).draw(elements.get(1).getPosition(), ((Laser) elements.get(1)).getLastPosition());

        Mockito.verify(rView, Mockito.times(1)).draw(elements.get(2).getPosition());

        Mockito.verify(distanceCounter, Mockito.times(1)).draw(new Position(screen.getTerminalSize().getColumns() - "metros".length() - 10, 0), 2);
        Mockito.verify(coinCounter, Mockito.times(1)).draw(new Position(0, 0), 5);
    }
}
