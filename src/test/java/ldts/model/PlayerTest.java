package ldts.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PlayerTest {
    private Player player;

    @BeforeEach
    public void getTestPlayer() {
        player = new Player();
    }

    @Test
    public void goHigherTest() {
        int initX = player.getPosition().getX();
        int initY = player.getPosition().getY();

        player.goHigher();

        Assertions.assertEquals(initY + 1, player.getPosition().getY());
        Assertions.assertEquals(initX, player.getPosition().getX());
    }

    @Test
    public void goLowerTest() {
        int initX = player.getPosition().getX();
        int initY = player.getPosition().getY();

        player.goLower();

        Assertions.assertEquals(initY - 1, player.getPosition().getY());
        Assertions.assertEquals(initX, player.getPosition().getX());
    }

    @Test
    public void setTest() {
        Position pos = new Position(12, 33);
        player.setPosition(pos);
        Assertions.assertEquals(pos, player.getPosition());
    }

    @Test
    void isTest(){
        Assertions.assertFalse(player.isCoin());
        Assertions.assertFalse(player.isLaser());
        Assertions.assertFalse(player.isRocket());
    }


}
