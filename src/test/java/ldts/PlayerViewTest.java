package ldts;

import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;
import ldts.model.Player;
import ldts.model.Position;
import ldts.view.PlayerView;
import ldts.view.View;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;

public class PlayerViewTest {
    private Player player;
    private PlayerView viewer;
    private Screen screen;
    private TextGraphics g;

    @BeforeEach
    void setUp() throws IOException, URISyntaxException, FontFormatException {
        player = new Player();
        viewer = new PlayerView();
        screen = Mockito.mock(Screen.class);
        g = Mockito.mock(TextGraphics.class);
        View.setScreen(screen);
        View.setGraphics(g);
    }

    @Test
    void draw() throws IOException {
        viewer.draw(player.getPosition());
        TextColor fore = TextColor.Factory.fromString("#FFFF33");
        TextColor back = TextColor.Factory.fromString("#000C66");
        Mockito.verify(g, Mockito.times(1)).setForegroundColor(fore);
        Mockito.verify(g, Mockito.times(1)).setBackgroundColor(back);
        Mockito.verify(g, Mockito.times(1)).putString(player.getPosition().getX(), View.getRows()-player.getPosition().getY(), "k");
    }
}
