package ldts.view;

import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;
import ldts.view.View;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class ViewTest {
    private Screen screen;
    private TextGraphics graphics;

    @BeforeEach
    void setUp() {
        screen = Mockito.mock(Screen.class);
        graphics = Mockito.mock(TextGraphics.class);
        View.setScreen(screen);
        View.setGraphics(graphics);
    }

    @Test
    void getScreen() {
        Assertions.assertEquals(screen, View.getScreen());
    }

    @Test
    void getGraphics() {
        Assertions.assertEquals(graphics, View.getGraphics());
    }
}
