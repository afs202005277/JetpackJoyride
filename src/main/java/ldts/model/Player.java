package ldts.model;

public class Player extends Element {

    public Player() {
        position = new Position(4, 2);
    }

    public void goHigher() {
        position.setY(position.getY() + 1);
    }

    public void goLower() {
        position.setY(position.getY() - 1);
    }

    @Override
    public boolean isLaser() {
        return false;
    }

    @Override
    public boolean isRocket() {
        return false;
    }

    @Override
    public boolean isCoin() {
        return false;
    }
}
