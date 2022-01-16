package ldts.model.Obstacle;

import ldts.model.Position;

public class Rocket implements Obstacle {
    private Position position;

    public Position getPosition() {
        return position;
    }

    public int getX() {return position.getX();}

    public void setX(int x) {position.setX(x);}

    public int getY() {return position.getY();}

    public void setY(int y) {position.setY(y);}

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

    public void move() {
        position.setX(position.getX()-3);
    }

    public boolean isLaser() {
        return false;
    }
}

