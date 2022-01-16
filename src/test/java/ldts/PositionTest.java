package ldts;

import ldts.model.Position;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class PositionTest {
    @Test
    public void equalsTester() {
        Position p1 = new Position(3, 2);
        Position p2 = new Position(3, 2);
        Position p3 = new Position(5, 2);

        Assertions.assertEquals(p1, p2);
        Assertions.assertNotEquals(p1, p3);
        Position p4 = p1;
        p1.setY(4);
        Assertions.assertEquals(p4.getX(), p1.getX());
        Assertions.assertEquals(4, p1.getY());
    }
}
