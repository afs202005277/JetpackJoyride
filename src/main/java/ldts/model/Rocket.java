package ldts.model;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.graphics.TextGraphics;

public class Rocket implements Obstacle{
    private Position position;

    @Override
    public Position getPosition() {
        return position;
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
        int y = (int) (Math.random() * (18 - 2)) + 2;
        position = new Position(60,y);
    }
    @Override
    public void move() {
        position.setX(position.getX()-3);
    }


    @Override
    public boolean type() {
        return false;
    }
}

