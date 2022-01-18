package ldts.view;

import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;
import ldts.model.Player;
import ldts.model.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;

public class PlayerViewTest {
    private Player player;
    private PlayerView viewer;
    private TextGraphics g;

    @BeforeEach
    void setUp() {
        player = new Player();
        viewer = new PlayerView("#57AAF8", "#D5433C", "!");
        Screen screen = Mockito.mock(Screen.class);
        g = Mockito.mock(TextGraphics.class);
        View.setScreen(screen);
        View.setGraphics(g);
    }

    @Test
    void draw() throws IOException {
        viewer.draw(player.getPosition());
        Mockito.verify(g, Mockito.times(1)).setForegroundColor(viewer.getForeGround());
        Mockito.verify(g, Mockito.times(1)).setBackgroundColor(viewer.getBackGround());
        Mockito.verify(g, Mockito.times(1)).putString(player.getPosition().getX(), View.getRows() - player.getPosition().getY(), viewer.getString());
    }

    @Test
    void drawLarge() {
        player.setPosition(new Position(10, 10));
        viewer.drawLarge(player.getPosition());
        Mockito.verify(g, Mockito.times(1)).setForegroundColor(viewer.getForeGround());
        Mockito.verify(g, Mockito.times(1)).setBackgroundColor(viewer.getBackGround());
        Mockito.verify(g, Mockito.times(1)).putString(player.getPosition().getX(), View.getRows() - player.getPosition().getY(), "&?(");
        Mockito.verify(g, Mockito.times(1)).putString(player.getPosition().getX(), View.getRows() - player.getPosition().getY() + 1, ")*+");
        Mockito.verify(g, Mockito.times(1)).putString(player.getPosition().getX(), View.getRows() - player.getPosition().getY() + 2, ",;/");
    }
}
