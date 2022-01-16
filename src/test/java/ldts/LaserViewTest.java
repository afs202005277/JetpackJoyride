package ldts;

import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;
import ldts.model.Obstacle.Laser;
import ldts.model.Position;
import ldts.view.LaserView;
import ldts.view.View;
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
    void setUp(){
        laser = new Laser();
        viewer = new LaserView();
        p1 = new Position(1, 2);
        p2 = new Position(4, 5);
        screen = Mockito.mock(Screen.class);
        g = Mockito.mock(TextGraphics.class);
        View.setScreen(screen);
        View.setGraphics(g);
    }

    @Test
    void drawTest() throws IOException {
        viewer.draw(p1, p2);
        TextColor back = TextColor.Factory.fromString("#336699");
        //TextColor fore = TextColor.Factory.fromString("#FFFFFF");
        Mockito.verify(g, Mockito.times(1)).setBackgroundColor(back);
        //Mockito.verify(g, Mockito.times(1)).setForegroundColor(fore);
        Mockito.verify(g, Mockito.times(1)).drawLine(p1.getX(), View.getRows() - p1.getY(), p2.getX(),View.getRows() - p2.getY(), 'L');
    }
}
