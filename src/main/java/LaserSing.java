public class LaserSing {
    private Position position;

    public Position getPosition() {
        return position;
    }
    public LaserSing(int x, int y) {
        position = new Position(x,y);
    }

    public void moveLaser() {
        position.setX(position.getX()-1);
    }


}
