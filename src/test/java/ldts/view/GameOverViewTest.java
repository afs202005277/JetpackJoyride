package ldts.view;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;

public class GameOverViewTest {
    private GameOverView gameOverView;
    private TextGraphics graphics;
    private Screen screen;

    @BeforeEach
    void setUp() {
        gameOverView = new GameOverView();
        graphics = Mockito.mock(TextGraphics.class);
        screen = Mockito.mock(Screen.class);
        Mockito.when(screen.getTerminalSize()).thenReturn(new TerminalSize(51, 18));
        View.setScreen(screen);
        View.setGraphics(graphics);
    }

    @Test
    void moveSelected() {
        Assertions.assertEquals(0, gameOverView.getSelected());
        gameOverView.moveSelected(1);
        Assertions.assertEquals(1, gameOverView.getSelected());
        gameOverView.moveSelected(1);
        Assertions.assertEquals(0, gameOverView.getSelected());
        gameOverView.moveSelected(-1);
        Assertions.assertEquals(1, gameOverView.getSelected());
    }

    @Test
    void drawSelectReplay() throws IOException {
        gameOverView.draw();
        TextColor white = TextColor.Factory.fromString("#FFFFFF"), black = TextColor.Factory.fromString("#000000");
        Mockito.verify(graphics, Mockito.times(2)).setBackgroundColor(white);
        Mockito.verify(graphics, Mockito.times(1)).fillRectangle(new TerminalPosition(19, 6), new TerminalSize(12, 6), ' ');

        Mockito.verify(graphics, Mockito.times(2)).setForegroundColor(black);
        Mockito.verify(graphics, Mockito.times(1)).putString(new TerminalPosition(21, 7), "GAMEOVER");

        Mockito.verify(graphics, Mockito.times(1)).setBackgroundColor(TextColor.Factory.fromString("#111111"));
        Mockito.verify(graphics, Mockito.times(1)).setForegroundColor(TextColor.Factory.fromString("#FFFFFF"));
        Mockito.verify(graphics, Mockito.times(1)).putString(new TerminalPosition(21, 8), "- REPLAY");

        Mockito.verify(graphics, Mockito.times(1)).putString(new TerminalPosition(21, 9), "- QUIT");

        Mockito.verify(screen, Mockito.times(1)).refresh();
    }
}
