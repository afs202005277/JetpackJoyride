//Rocket e 6x4


import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.graphics.TextGraphics;

public class Rocket extends Obstacle{
    public Position getPosition() {
        return position;
    }

    private Position position;

    public int getX() {return position.getX();}
    public void setX(int x) {position.setX(x);}
    public int getY() {return position.getY();}
    public void setY(int y) {position.setY(y);}

    @Override
    public Position getLastPosition() {
        return null;
    }

    public Rocket() {
        int y = (int) (Math.random() * (74 - 1)) + 1;
        position = new Position(132,y);
    }
    @Override
    public void move() {
        position.setX(position.getX()-1);
    }

    @Override
    public void draw(TextGraphics graphics) {
        graphics.drawRectangle(new TerminalPosition(this.getX(), this.getY()), new TerminalSize(6,4), 'R');
    }
}
