package ldts.view;

import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;
import ldts.model.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;

public class InstructionsViewTest {
    private InstructionsView instructionsView;
    private TextGraphics graphics;
    private Screen screen;
    private PlayerView playerView;
    private CoinView coinView;
    private BackgroundView backgroundView;
    private LaserView laserView;

    @BeforeEach
    void setUp() {
        instructionsView = new InstructionsView();
        graphics = Mockito.mock(TextGraphics.class);
        screen = Mockito.mock(Screen.class);
        playerView = Mockito.mock(PlayerView.class);
        coinView = Mockito.mock(CoinView.class);
        backgroundView = Mockito.mock(BackgroundView.class);
        Mockito.when(backgroundView.getLower()).thenReturn(1);
        Mockito.when(backgroundView.getBackGroundWall()).thenReturn(TextColor.Factory.fromString("#111111"));
        laserView = Mockito.mock(LaserView.class);
        View.setGraphics(graphics);
        View.setScreen(screen);
    }

    @Test
    void draw() throws IOException {
        TextColor bkColor = TextColor.Factory.fromString("#111111"), frColor = TextColor.Factory.fromString("#FFFFFF");
        instructionsView.draw(playerView, backgroundView, laserView, coinView);
        Mockito.verify(screen, Mockito.times(1)).clear();
        Mockito.verify(backgroundView, Mockito.times(1)).draw(new Position(0, 1));
        Mockito.verify(laserView, Mockito.times(2)).draw(Mockito.any(Position.class), Mockito.any(Position.class));
        Mockito.verify(coinView, Mockito.times(8)).draw(Mockito.any(Position.class));
        Mockito.verify(playerView, Mockito.times(1)).drawLarge(new Position(10, 4));

        Mockito.verify(graphics, Mockito.times(1)).setBackgroundColor(bkColor);
        Mockito.verify(graphics, Mockito.times(1)).setForegroundColor(frColor);

        Mockito.verify(graphics, Mockito.times(5)).putString(Mockito.anyInt(), Mockito.anyInt(), Mockito.anyString());

        bkColor = TextColor.Factory.fromString("#000000");
        Mockito.verify(graphics, Mockito.times(1)).setBackgroundColor(bkColor);

        Mockito.verify(screen, Mockito.times(1)).refresh();
    }
}
