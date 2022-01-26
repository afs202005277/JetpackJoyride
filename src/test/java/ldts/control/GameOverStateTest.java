package ldts.control;

import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import ldts.control.States.GameOverState;
import ldts.view.GameOverView;
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

    @BeforeEach
    void setUp(){
        gameOverView = Mockito.mock(GameOverView.class);
        gameOverState = new GameOverState(gameOverView);
    }

    @Test
    void step() throws IOException, URISyntaxException, FontFormatException, InterruptedException {
        gameOverState.step();
        Mockito.verify(gameOverView, Mockito.times(1)).draw();
        Assertions.assertFalse(gameOverState.isEnterPressed());
    }

    @Test
    void inputArrowUp() throws IOException, URISyntaxException, FontFormatException, InterruptedException {
        gameOverState.input(new KeyStroke(KeyType.ArrowUp));
        Mockito.verify(gameOverView, Mockito.times(1)).moveSelected(-1);
    }

    @Test
    void inputArrowDown() throws IOException, URISyntaxException, FontFormatException, InterruptedException {
        gameOverState.input(new KeyStroke(KeyType.ArrowDown));
        Mockito.verify(gameOverView, Mockito.times(1)).moveSelected(1);
    }

    @Test
    void inputEnter() throws IOException, URISyntaxException, FontFormatException, InterruptedException {
        gameOverState.input(new KeyStroke(KeyType.Enter));
        Mockito.verify(gameOverView, Mockito.times(0)).moveSelected(Mockito.anyInt());
        Assertions.assertTrue(gameOverState.isEnterPressed());
    }
}
