package ldts.model;

import com.googlecode.lanterna.graphics.TextGraphics;

import java.util.ArrayList;
import java.util.List;

public class Laser implements Obstacle {
    private Position position;
    private final int orient;  // 1. -  2. |  3. \  4.  /
    private final int size;

    public int getX() {return position.getX();}
    public void setX(int x) {position.setX(x);}
    public int getY() {return position.getY();}
    public void setY(int y) {position.setY(y);}
    public Position getPosition() {
        return position;
    }
    @Override
    public Position getLastPosition() {
        Position position1;
        switch (orient) {
            case 1:
                position1 = new Position(position.getX() + size -1 , position.getY());
                break;
            case 2:
                position1 =  new Position(position.getX(), position.getY() - size + 1);
                break;
            case 3:
                position1 = new Position(position.getX() + size - 1, position.getY() - size + 1);
                break;
            case 4:
                position1 = new Position(position.getX() + size - 1, position.getY() + size - 1);
                break;
            default:
                position1 = position;
                break;
        }
        return position1;
    }


    public int getOrient() {return orient;}
    public int getSize() {return size;}

    public Laser() {
        size = (int) (Math.random() * (18 - 5)) + 5;
        int ori = (int) (Math.random() * (10 - 1)) + 1;
        if (ori <= 2) {
            orient = 3;
            int round = (int) Math.round(Math.sin(Math.PI / 4));
            int y = (int) (Math.random() * (16 - size * round)) + round;
            int x = 60;
            position = new Position(x,y);

        }
        else if (ori <= 5) {
            orient = 2;
            int y = (int) (Math.random() * (16 - size)) + size;
            position = new Position(60,y);
        }
        else if (ori <= 8) {
            orient = 1;
            int y = (int) (Math.random() * (16 - 1)) + 1;
            int x = 60;
            position = new Position(x,y);
        }
        else {
            orient = 4;
            int round = (int) Math.round(Math.sin(Math.PI / 4));
            int y = (int) (Math.random() * ((16 - size * round) - 1)) + 1;
            int x = 60;
            position = new Position(x,y);
        }
    }
    @Override
    public void move() {
        position.setX(position.getX()-1);
    }

    @Override
    public void draw(TextGraphics graphics) {
        graphics.drawLine(this.getX(), this.getY(), this.getLastPosition().getX(), this.getLastPosition().getY(), 'L');
    }
}

