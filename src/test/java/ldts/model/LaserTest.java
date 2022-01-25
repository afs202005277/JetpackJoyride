package ldts.model;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.screen.Screen;
import ldts.view.View;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

public class LaserTest {
    @BeforeEach
    void setUp() {
        Screen screen = Mockito.mock(Screen.class);
        Mockito.when(screen.getTerminalSize()).thenReturn(new TerminalSize(51, 18));
        View.setScreen(screen);
    }


    @Test
    public void moveLaser() {
        Laser laser = new Laser();
        int x = laser.getX() - 1;
        int y = laser.getY();
        laser.move(-1, 0);
        Assertions.assertEquals(x, laser.getX());
        Assertions.assertEquals(laser.getY(), y);
    }

    @Test
    public void type() {
        Laser laser = new Laser();
        Assertions.assertTrue(laser.isLaser());
    }

    @Test
    public void lastPosition() {
        List<Laser> lasers = new ArrayList<>();
        for (int orient = 1; orient < 5; orient++) {
            for (int size = 1; size < 6; size++)
                lasers.add(new Laser(new Position(20, 8), orient, size));
        }

        for (Laser laser : lasers) {
            int size = laser.getSize();
            int orient = laser.getOrient();
            switch (orient) {
                case 1 -> {
                    Assertions.assertEquals(laser.getX() + size - 1, laser.getLastPosition().getX());
                    Assertions.assertEquals(laser.getY(), laser.getLastPosition().getY());
                }
                case 2 -> {
                    Assertions.assertEquals(laser.getX(), laser.getLastPosition().getX());
                    Assertions.assertEquals(laser.getY() - size + 1, laser.getLastPosition().getY());
                }
                case 3 -> {
                    Assertions.assertEquals(laser.getX() + size - 1, laser.getLastPosition().getX());
                    Assertions.assertEquals(laser.getY() - size + 1, laser.getLastPosition().getY());
                }
                case 4 -> {
                    Assertions.assertEquals(laser.getX() + size - 1, laser.getLastPosition().getX());
                    Assertions.assertEquals(laser.getY() + size - 1, laser.getLastPosition().getY());
                }
                default -> Assertions.fail();
            }
        }
    }

    @Test
    public void constructor() {
        Laser laser = new Laser();
        Assertions.assertTrue(laser.getY() >= 2 && laser.getY() <= 18);
        Assertions.assertTrue(laser.getOrient() >= 1 && laser.getOrient() <= 4);
        Assertions.assertTrue(laser.getSize() >= 1 && laser.getSize() <= 5);
        Assertions.assertEquals(51, laser.getX());
    }

    @Test
    public void sets() {
        Laser laser = new Laser();
        int x = 10;
        int y = 10;
        laser.setX(x);
        laser.setY(y);
        Assertions.assertEquals(x, laser.getPosition().getX());
        Assertions.assertEquals(y, laser.getPosition().getY());
    }

}
