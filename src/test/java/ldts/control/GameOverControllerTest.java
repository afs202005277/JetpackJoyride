package ldts.control;

import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import ldts.view.GameOverView;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;

public class GameOverControllerTest {
    private GameOverController gameOverController;
    private GameOverView gameOverView;

    @BeforeEach
    void setUp(){
        gameOverView = Mockito.mock(GameOverView.class);
        gameOverController = new GameOverController(gameOverView);
    }

    @Test
    void step() throws IOException {
        gameOverController.step();
        Mockito.verify(gameOverView, Mockito.times(1)).draw();
        Assertions.assertTrue(gameOverController.isGameOver());
        Assertions.assertFalse(gameOverController.isEnterPressed());
    }

    @Test
    void inputArrowUp() {
        gameOverController.input(new KeyStroke(KeyType.ArrowUp));
        Mockito.verify(gameOverView, Mockito.times(1)).moveSelected(-1);
    }

    @Test
    void inputArrowDown() {
        gameOverController.input(new KeyStroke(KeyType.ArrowDown));
        Mockito.verify(gameOverView, Mockito.times(1)).moveSelected(1);
    }

    @Test
    void inputEnter() {
        gameOverController.input(new KeyStroke(KeyType.Enter));
        Mockito.verify(gameOverView, Mockito.times(0)).moveSelected(Mockito.anyInt());
        Assertions.assertFalse(gameOverController.isGameOver());
        Assertions.assertTrue(gameOverController.isEnterPressed());
    }
}
