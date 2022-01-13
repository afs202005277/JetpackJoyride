package ldts.model;

import ldts.view.View;

public class Coin extends Element {

    public Coin(Position position) {
        super(position);
    }

    public void move() {
        position = new Position(position.getX()-1, position.getY());
    }
}
