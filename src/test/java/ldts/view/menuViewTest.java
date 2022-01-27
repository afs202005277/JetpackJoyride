package ldts.view;

import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;

public class menuViewTest {
    private MenuView menuView;

    @BeforeEach
    void setUp() {
        menuView = new MenuView();
    }

    @Test
    void moveSelected() {
        Assertions.assertEquals(0, menuView.getSelected());

        menuView.moveSelected(1);
        Assertions.assertEquals(1, menuView.getSelected());

        menuView.moveSelected(1);
        Assertions.assertEquals(2, menuView.getSelected());

        menuView.moveSelected(1);
        Assertions.assertEquals(0, menuView.getSelected());

        menuView.moveSelected(-1);
        Assertions.assertEquals(2, menuView.getSelected());

        menuView.moveSelected(-1);
        Assertions.assertEquals(1, menuView.getSelected());

        menuView.moveSelected(-1);
        Assertions.assertEquals(0, menuView.getSelected());
    }

    @Test
    void draw() throws IOException {
        Screen screen = Mockito.mock(Screen.class);
        TextGraphics graphics = Mockito.mock(TextGraphics.class);
        View.setScreen(screen);
        View.setGraphics(graphics);

        BackgroundView backgroundView = Mockito.mock(BackgroundView.class);
        Mockito.when(backgroundView.getBackGroundWall()).thenReturn(Mockito.mock(TextColor.class));
        menuView.draw(Mockito.mock(PlayerView.class), backgroundView, Mockito.mock(LaserView.class), Mockito.mock(CoinView.class));

        Mockito.verify(screen, Mockito.times(2)).clear();
        Mockito.verify(graphics, Mockito.times(4)).setBackgroundColor(Mockito.any(TextColor.class));
        Mockito.verify(graphics, Mockito.times(1)).setForegroundColor(Mockito.any(TextColor.class));
        Mockito.verify(graphics, Mockito.times(4)).putString(Mockito.anyInt(), Mockito.anyInt(),Mockito.any(String.class));
        Mockito.verify(screen, Mockito.times(1)).refresh();
    }
}
