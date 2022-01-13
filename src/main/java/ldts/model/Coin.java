package ldts.model;

import ldts.view.View;

public class Coin implements Element {

    private Position position;

    public Coin(Position position) {
        this.position = position;
    }

    @Override
    public Position getPosition() {
        return position;
    }

    @Override
    public void move() {
        position = new Position(position.getX()-1, position.getY());
    }

    @Override
    public int getX() {
        return 0;
    }

    @Override
    public void setX(int x) {

    }

    @Override
    public int getY() {
        return 0;
    }

    @Override
    public void setY(int y) {

    }
}
