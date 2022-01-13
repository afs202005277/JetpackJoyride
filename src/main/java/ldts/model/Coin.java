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
    public void move(int x, int y) {
        position.setX(position.getX()+x);
        position.setY(position.getY()+y);
    }

    @Override
    public int getX() {
        return position.getX();
    }

    @Override
    public void setX(int x) {
        position.setX(x);
    }

    @Override
    public int getY() {
        return position.getY();
    }

    @Override
    public void setY(int y) {
        position.setY(y);
    }
}
