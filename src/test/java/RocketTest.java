import ldts.model.Rocket;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class RocketTest {

    @Test
    public void moveRocket() {
        Rocket rocket = new Rocket();
        int x = rocket.getX() - 3 ;
        int y = rocket.getY();
        rocket.move();
        Assertions.assertEquals(x,rocket.getX());
    }
}
