package ldts.model;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.graphics.TextGraphics;

public class Rocket implements Obstacle{
    private Position position;

    public Position getPosition() {
        return position;
    }
    public int getX() {return position.getX();}
    public void setX(int x) {position.setX(x);}
    public int getY() {return position.getY();}
    public void setY(int y) {position.setY(y);}

    @Override
    public Position getLastPosition() {
        return null;
    }

    public Rocket() {
        int y = (int) (Math.random() * (10 - 1)) + 1;
        position = new Position(60,y);
    }
    @Override
    public void move() {
        position.setX(position.getX()-1);
    }

    @Override
    public void draw(TextGraphics graphics) {
        graphics.drawRectangle(new TerminalPosition(this.getX(), this.getY()), new TerminalSize(1,2), 'R');
    }

    @Override
    public boolean type() {
        return false;
    }
}

