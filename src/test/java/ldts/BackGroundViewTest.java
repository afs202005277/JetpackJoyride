package ldts;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;
import ldts.model.Position;
import ldts.view.BackgroundView;
import ldts.view.View;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;

public class BackGroundViewTest {
    private BackgroundView viewer;
    private Screen screen;
    private TextGraphics g;

    @BeforeEach
    void setUp() {
        viewer = new BackgroundView(3);
        screen = Mockito.mock(Screen.class);
        g = Mockito.mock(TextGraphics.class);
        View.setScreen(screen);
        View.setGraphics(g);
    }

    @Test
    void drawTest() throws IOException {
        Position pos = new Position(0, viewer.getLower());
        viewer.draw(pos, 0);
        TextColor backWall = TextColor.Factory.fromString("#000C66");
        Mockito.verify(g, Mockito.times(1)).fillRectangle(new TerminalPosition(pos.getX(), 0), screen.getTerminalSize(), ' ');
        Mockito.verify(g, Mockito.times(1)).setBackgroundColor(backWall);

        TextColor backBase = TextColor.Factory.fromString("#808080");
        Mockito.verify(g, Mockito.times(1)).setBackgroundColor(backBase);
        Mockito.verify(g, Mockito.times(1)).fillRectangle(new TerminalPosition(pos.getX(), View.getRows() - pos.getY()), new TerminalSize(View.getCollumns(), viewer.getLower()), ' ');
    }
}
