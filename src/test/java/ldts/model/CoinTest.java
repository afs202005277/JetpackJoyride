package ldts.model;

import ldts.model.Coin;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

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
        Coin coin = new Coin();
        Assertions.assertTrue(coin.getY() >= 2 && coin.getY() <= 18);
        Assertions.assertEquals(51, coin.getX());
    }
}
