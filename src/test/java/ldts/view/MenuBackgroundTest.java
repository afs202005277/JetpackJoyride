package ldts.view;

import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;
import ldts.model.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;


public class MenuBackgroundTest {
    private MenuBackGround menuBackGround;
    private TextGraphics graphics;
    private Screen screen;
    private PlayerView playerView;
    private CoinView coinView;
    private BackgroundView backgroundView;
    private LaserView laserView;

    @BeforeEach
    void setUp() {
        menuBackGround = new MenuBackGround();
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
        MenuBackGround.draw(playerView, backgroundView, laserView, coinView);
        Mockito.verify(screen, Mockito.times(1)).clear();
        Mockito.verify(backgroundView, Mockito.times(1)).draw(new Position(0, 1));
        Mockito.verify(laserView, Mockito.times(2)).draw(Mockito.any(Position.class), Mockito.any(Position.class));
        Mockito.verify(coinView, Mockito.times(8)).draw(Mockito.any(Position.class));
        Mockito.verify(playerView, Mockito.times(1)).drawLarge(new Position(10, 4));
    }
}
