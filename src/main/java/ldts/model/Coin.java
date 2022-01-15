package ldts.model;

import ldts.view.View;

public class Coin implements Element {

    private Position position;
    private boolean collected;

    public Coin(Position position) {
        this.position = position;
    }

    public Coin()
    {
        collected = false;
        int y = (int) (Math.random() * (View.getRows() - 2)) + 2;
        int x = View.getScreen().getTerminalSize().getColumns();
        position = new Position(x,y);
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
        return true;
    }

    public boolean isCollected() {
        return collected;
    }

    public void collect(){
        collected = true;
    }
}
