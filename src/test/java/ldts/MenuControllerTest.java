package ldts;

import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import ldts.control.MenuController;
import ldts.control.MenuController;
import ldts.view.*;
import ldts.view.MenuView;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;

public class MenuControllerTest {
    private MenuController menuController;
    private MenuView menuView;
    private Screen screen;

    @BeforeEach
    void setUp(){
        screen = Mockito.mock(Screen.class);
        View.setScreen(screen);
        View.setGraphics(Mockito.mock(TextGraphics.class));
        menuView = Mockito.mock(MenuView.class);
        menuController = new MenuController(Mockito.mock(PlayerView.class), Mockito.mock(BackgroundView.class), Mockito.mock(CoinView.class), Mockito.mock(LaserView.class));
    }

    @Test
    void step() throws IOException {
        Mockito.when(screen.readInput()).thenReturn(new KeyStroke(KeyType.ArrowLeft));
        menuController.step();
        Mockito.verify(menuView, Mockito.times(1)).draw(Mockito.mock(PlayerView.class), Mockito.mock(BackgroundView.class), Mockito.mock(LaserView.class) ,Mockito.mock(CoinView.class));
        Assertions.assertFalse(menuController.isEnterPressed());
    }

    @Test
    void inputArrowUp() throws IOException, URISyntaxException, InterruptedException, FontFormatException {
        Mockito.when(screen.readInput()).thenReturn(new KeyStroke(KeyType.ArrowUp));
        menuController.input(new KeyStroke(KeyType.ArrowUp));
        Mockito.verify(menuView, Mockito.times(1)).moveSelected(-1);
    }

    @Test
    void inputArrowDown() throws IOException, URISyntaxException, InterruptedException, FontFormatException {
        Mockito.when(screen.readInput()).thenReturn(new KeyStroke(KeyType.ArrowDown));
        menuController.input(new KeyStroke(KeyType.ArrowDown));
        Mockito.verify(menuView, Mockito.times(1)).moveSelected(1);
    }

    @Test
    void inputEnter() throws IOException, URISyntaxException, InterruptedException, FontFormatException {
        Mockito.when(screen.readInput()).thenReturn(new KeyStroke(KeyType.Enter));
        menuController.input(new KeyStroke(KeyType.Enter));
        Mockito.verify(menuView, Mockito.times(0)).moveSelected(Mockito.anyInt());
        Assertions.assertTrue(menuController.isEnterPressed());
    }
}
