package ldts.model;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.screen.Screen;
import ldts.view.View;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Scanner;

public class CoinTest {

    @Test
    public void moveCoin() {
        Coin coin = new Coin();
        int x = coin.getX() - 1;
        coin.move(-1,0);
        Assertions.assertEquals(x,coin.getX());
    }

    @Test
    public void type() {
        Coin coin = new Coin();
        Assertions.assertFalse(coin.isLaser());
        Assertions.assertFalse(coin.isRocket());
        Assertions.assertTrue(coin.isCoin());
    }


    @Test
    public void constructor() {
        Screen screen = Mockito.mock(Screen.class);
        View.setScreen(screen);
        Mockito.when(screen.getTerminalSize()).thenReturn(new TerminalSize(51, 18));

        Coin coin = new Coin();
        Assertions.assertTrue(coin.getY() >= 2 && coin.getY() <= 18);
        Assertions.assertEquals(51, coin.getX());
    }
}
