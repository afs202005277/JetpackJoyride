import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class LaserGroupTest {

    @Test
    public void moveLasers() {
        LaserGroup laser = new LaserGroup();
        int x = laser.getX() - 1;
        int y = laser.getY();
        laser.move();
        Assertions.assertEquals(x,laser.getX());
        List<LaserSing> lasers = laser.getLasers();
        int orient = laser.getOrient();
        switch (orient) {
            case 1 :
                for (LaserSing laserS : lasers) {
                    Assertions.assertEquals(x,laserS.getPosition().getX());
                    x++;
                }
                break;

            case 2 :
                for (LaserSing laserS : lasers) {
                    Assertions.assertEquals(x,laserS.getPosition().getX());
                }
                break;
            case 3 :
                for (LaserSing laserS : lasers) {
                    Assertions.assertEquals(y,laserS.getPosition().getY());
                    Assertions.assertEquals(x,laserS.getPosition().getX());
                    x++;
                    y++;
                }
                break;
            case 4 :
                for (LaserSing laserS : lasers) {
                    Assertions.assertEquals(y,laserS.getPosition().getY());
                    Assertions.assertEquals(x,laserS.getPosition().getX());
                    x++;
                    y--;
                }
                break;
        }
    }


}
