import ldts.model.Laser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class LaserTest {

    @Test
    public void moveLaser() {
        Laser laser = new Laser();
        int x = laser.getX() - 1;
        int y = laser.getY();
        laser.move();
        Assertions.assertEquals(x,laser.getX());
    }


}
