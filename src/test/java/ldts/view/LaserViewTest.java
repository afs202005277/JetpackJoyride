package ldts.view;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;
import ldts.model.Laser;
import ldts.model.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;

public class LaserViewTest {
    private Laser laser;
    private LaserView viewer;
    private Position p1, p2;
    private Screen screen;
    private TextGraphics g;

    @BeforeEach
    void setUp() {
        screen = Mockito.mock(Screen.class);
        Mockito.when(screen.getTerminalSize()).thenReturn(new TerminalSize(51, 18));
        g = Mockito.mock(TextGraphics.class);
        View.setScreen(screen);
        View.setGraphics(g);
        laser = new Laser();
        viewer = new LaserView("#FFFB54", ' ');
        p1 = new Position(1, 2);
        p2 = new Position(4, 5);
    }

    @Test
    void drawTest() throws IOException {
        viewer.draw(p1, p2);
        TextColor back = TextColor.Factory.fromString("#FFFB54");
        Mockito.verify(g, Mockito.times(1)).setBackgroundColor(back);
        Mockito.verify(g, Mockito.times(1)).drawLine(p1.getX(), View.getRows() - p1.getY(), p2.getX(), View.getRows() - p2.getY(), ' ');
    }
}
