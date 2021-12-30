import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class RocketTest {

    @Test
    public void moveRocket() {
        Rocket rocket = new Rocket();
        int x = rocket.getX() - 1;
        int y = rocket.getY();
        rocket.moveRocket();
        Assertions.assertEquals(x,rocket.getX());
    }


}
