package ldts.control;

import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import ldts.control.States.GameOverState;
import ldts.view.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;

public class GameOverStateTest {
    private GameOverState gameOverState;
    private GameOverView gameOverView;
    private Screen screen;
    private Controller controller;

    @BeforeEach
    void setUp(){
        gameOverView = Mockito.mock(GameOverView.class);
        gameOverState = new GameOverState(gameOverView);
        controller = Mockito.mock(Controller.class);
        Controller.setSingleton(controller);
        screen = Mockito.mock(Screen.class);
        View.setScreen(screen);
    }

    @Test
    void step() throws IOException, URISyntaxException, FontFormatException, InterruptedException, AWTException {
        Mockito.when(screen.readInput()).thenReturn(new KeyStroke(KeyType.Delete));
        gameOverState.setEnterPressed(true);
        gameOverState.setMainMenu(true);
        gameOverState.step();
        Mockito.verify(controller, Mockito.times(1)).runMenu();

        gameOverState.setEnterPressed(true);
        gameOverState.setMainMenu(false);
        gameOverState.step();
        Mockito.verify(gameOverView, Mockito.times(2)).draw();
        Mockito.verify(controller, Mockito.times(1)).run();
    }

    @Test
    void inputArrowUp() throws IOException, URISyntaxException, FontFormatException, InterruptedException {
        gameOverState.input(new KeyStroke(KeyType.ArrowUp));
        Mockito.verify(gameOverView, Mockito.times(1)).moveSelected(-1);
        Mockito.verify(gameOverView, Mockito.times(1)).draw();
    }

    @Test
    void inputArrowDown() throws IOException, URISyntaxException, FontFormatException, InterruptedException {
        gameOverState.input(new KeyStroke(KeyType.ArrowDown));
        Mockito.verify(gameOverView, Mockito.times(1)).moveSelected(1);
        Mockito.verify(gameOverView, Mockito.times(1)).draw();
    }

    @Test
    void inputEnter() throws IOException, URISyntaxException, FontFormatException, InterruptedException {
        gameOverState.input(new KeyStroke(KeyType.Enter));
        Mockito.verify(gameOverView, Mockito.times(1)).draw();
        Mockito.verify(gameOverView, Mockito.times(0)).moveSelected(Mockito.anyInt());
        Assertions.assertTrue(gameOverState.isEnterPressed());
    }

    @Test
    void enterPressedTest() {
        Assertions.assertFalse(gameOverState.isEnterPressed());
    }
}
