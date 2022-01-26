package ldts.model;


import ldts.control.States.RunningState;
import ldts.view.View;


public class Rocket extends Element {

    public Rocket() {
        int y = (int) (Math.random() * (View.getNumberRows() - 2)) + 2;
        position = new Position(View.getNumberColumns(),y);
    }
    public Rocket(int x, int y) {
        position= new Position(x,y);
    }

    @Override
    public boolean checkCollision(Position pos) {
        Position temp = new Position(this.getX() + 1, this.getPosition().getY());
        boolean collision = this.getPosition().equals(pos) || temp.equals(pos);
        if (collision)
            RunningState.endGame();
        return collision;
    }

    @Override
    public boolean isLaser() {
        return false;
    }

    @Override
    public boolean isRocket() {
        return true;
    }

    @Override
    public boolean isCoin() {
        return false;
    }
}

