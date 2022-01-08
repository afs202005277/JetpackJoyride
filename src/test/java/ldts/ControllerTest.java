package ldts;

import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;
import ldts.control.Controller;
import ldts.model.Position;
import ldts.view.BackgroundView;
import ldts.view.PlayerView;
import ldts.view.View;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;

public class ControllerTest {
    private Controller control;
    private BackgroundView backViewer;
    private PlayerView playerViewer;
    private Screen screen;
    private TextGraphics graphics;

    @BeforeEach
    void setUp() throws IOException, URISyntaxException, FontFormatException {
        control = new Controller();
        backViewer = Mockito.mock(BackgroundView.class);
        playerViewer = Mockito.mock(PlayerView.class);
        screen = Mockito.mock(Screen.class);
        graphics = Mockito.mock(TextGraphics.class);
        View.setScreen(screen);
        View.setGraphics(graphics);
        control.setBackgroundView(backViewer);
        control.setPlayerView(playerViewer);
    }

    @Test
    void generateObstacles(){
        for (int i=0;i<100;i++)
        {
            control.generateObstacles(i);
        }
        Assertions.assertEquals(7, control.getObstacles().size());
    }

    @Test
    void drawElements() throws IOException {
        control.drawElements(2);
        Mockito.verify(screen, Mockito.times(1)).clear();
        Mockito.verify(backViewer, Mockito.times(1)).draw(new Position(0, 1), 2);
        Mockito.verify(playerViewer, Mockito.times(1)).draw(control.getPlayer().getPosition());
        Mockito.verify(screen, Mockito.times(1)).refresh();
    }
}
