package ldts.view;

import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;
import ldts.model.Position;
import ldts.model.Rocket;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;

public class RocketViewTest {
    private Rocket rocket;
    private RocketView viewer;
    private Position pos;
    private Screen screen;
    private TextGraphics g;

    @BeforeEach
    void setUp() {
        rocket = new Rocket();
        viewer = new RocketView("#57AAF8", "#000000", "$%");
        pos = new Position(1, 2);
        screen = Mockito.mock(Screen.class);
        g = Mockito.mock(TextGraphics.class);
        View.setScreen(screen);
        View.setGraphics(g);
    }

    @Test
    void drawTest() throws IOException {
        viewer.draw(pos);
        TextColor back = TextColor.Factory.fromString("#57AAF8");
        TextColor fore = TextColor.Factory.fromString("#000000");
        Mockito.verify(g, Mockito.times(1)).setBackgroundColor(back);
        Mockito.verify(g, Mockito.times(1)).setForegroundColor(fore);
        Mockito.verify(g, Mockito.times(1)).putString(pos.getX(), View.getRows() - pos.getY(), "$%");
    }
}
