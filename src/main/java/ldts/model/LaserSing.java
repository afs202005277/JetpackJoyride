package ldts.model;

public class LaserSing extends Element{
    public LaserSing(int x, int y) {
        super(new Position(x, y));
    }

    public void moveLaser() {
        position.setX(position.getX()-1);
    }
}

