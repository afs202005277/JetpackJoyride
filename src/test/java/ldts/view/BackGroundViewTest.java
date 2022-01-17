package ldts.view;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;
import ldts.model.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class BackGroundViewTest {
    private BackgroundView viewer;
    private Screen screen;
    private TextGraphics g;

    @BeforeEach
    void setUp() {
        viewer = new BackgroundView("#595959", "#57AAF8", ' ', ' ', 1);
        screen = Mockito.mock(Screen.class);
        Mockito.when(screen.getTerminalSize()).thenReturn(new TerminalSize(51, 18));
        g = Mockito.mock(TextGraphics.class);
        View.setScreen(screen);
        View.setGraphics(g);
    }

    @Test
    void drawTest() {
        Position pos = new Position(0, viewer.getLower());
        viewer.draw(pos);
        TextColor backWall = TextColor.Factory.fromString("#57AAF8");
        Mockito.verify(g, Mockito.times(1)).fillRectangle(new TerminalPosition(pos.getX(), 0), screen.getTerminalSize(), ' ');
        Mockito.verify(g, Mockito.times(1)).setBackgroundColor(backWall);

        TextColor backBase = TextColor.Factory.fromString("#595959");
        Mockito.verify(g, Mockito.times(1)).setBackgroundColor(backBase);
        Mockito.verify(g, Mockito.times(1)).fillRectangle(new TerminalPosition(pos.getX(), View.getRows() - pos.getY()), new TerminalSize(View.getCollumns(), viewer.getLower()), ' ');
    }
}
