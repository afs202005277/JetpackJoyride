package ldts.control;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;
import ldts.control.PlayerController;
import ldts.control.States.RunningState;
import ldts.model.*;
import ldts.view.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;

public class RunningStateTest {
    private RunningState state;
    private Screen screen;
    private TextGraphics graphics;

    @BeforeEach
    void setUp() throws IOException, URISyntaxException, FontFormatException {
        screen = Mockito.mock(Screen.class);
        Mockito.when(screen.getTerminalSize()).thenReturn(new TerminalSize(51, 18));
        View.setScreen(screen);
        View.setGraphics(graphics);

        state = new RunningState(screen,new PlayerController(new Player(), new PlayerView("#57AAF8", "#D5433C", "!")));
        state.setScreen(screen);
        BackgroundView backViewer = Mockito.mock(BackgroundView.class);
        graphics = Mockito.mock(TextGraphics.class);
        state.setBackgroundView(backViewer);
    }

    ArrayList<Element> setElements(){
        ArrayList<Element> elements = new ArrayList<>();
        elements.add(new Laser(new Position(40, 3), 3, 2));
        elements.add(new Laser(new Position(50, 15), 4, 3));
        elements.add(new Rocket(70, 60));
        elements.add(new Coin(4, 3));
        elements.add(new Coin(10, 5));
        elements.add(new Coin(11, 5));
        state.setElements(elements);
        return elements;
    }

    @Test
    void drawElements() throws IOException {
        ArrayList<Element> elements = setElements();

        //SET UP MOCKS
        CounterView coinCounter = Mockito.mock(CounterView.class), distanceCounter = Mockito.mock(CounterView.class);
        Mockito.when(coinCounter.getUnits()).thenReturn("coins");
        Mockito.when(distanceCounter.getUnits()).thenReturn("metros");
        LaserView lView = Mockito.mock(LaserView.class);
        RocketView rView = Mockito.mock(RocketView.class);
        CoinView coinView = Mockito.mock(CoinView.class);
        BackgroundView backgroundView = Mockito.mock(BackgroundView.class);
        state.setCoinsCounterView(coinCounter);
        state.setDistanceCounterView(distanceCounter);
        state.setLaserView(lView);
        state.setRocketView(rView);
        state.setCoinView(coinView);
        state.setBackgroundView(backgroundView);

        //CALL THE METHOD
        state.drawElements(2, 5);

        //VERIFICATIONS
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
