package ldts.model;

import ldts.view.View;

public class Rocket implements Element, Obstacle {
    private Position position;

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
    public int getX() {return position.getX();}

    @Override
    public void setX(int x) {position.setX(x);}

    @Override
    public int getY() {return position.getY();}

    @Override
    public void setY(int y) {position.setY(y);}

    @Override
    public Position getLastPosition() {
        return null;
    }

    public Rocket() {
        int y = (int) (Math.random() * (View.getRows() - 2)) + 2;
        position = new Position(View.getScreen().getTerminalSize().getColumns(),y);
    }
    public Rocket(int x, int y) {
        position= new Position(x,y);
    }

    @Override
    public boolean isLaser() {
        return false;
    }
}

