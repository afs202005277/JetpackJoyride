package ldts.model;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.screen.Screen;
import ldts.view.View;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class CoinTest {
    private Coin coin;

    @BeforeEach
    void setUp(){
        coin = new Coin();
    }
    @Test
    public void moveCoin() {
        int x = coin.getX() - 1;
        int y = coin.getY() + 1;
        coin.move(-1,1);
        Assertions.assertEquals(x,coin.getX());
        Assertions.assertEquals(coin.getY(), y);
    }

    @Test
    public void type() {
        Assertions.assertFalse(coin.isLaser());
        Assertions.assertFalse(coin.isRocket());
        Assertions.assertTrue(coin.isCoin());
    }

    @Test
    public void constructor() {
        Screen screen = Mockito.mock(Screen.class);
        View.setScreen(screen);
        Mockito.when(screen.getTerminalSize()).thenReturn(new TerminalSize(51, 18));

        Assertions.assertTrue(coin.getY() >= 2 && coin.getY() <= 18);
        Assertions.assertEquals(51, coin.getX());
    }

    @Test
    void collectTest() {
        Assertions.assertFalse(coin.isCollected());
        coin.collect();
        Assertions.assertTrue(coin.isCollected());
    }
}
