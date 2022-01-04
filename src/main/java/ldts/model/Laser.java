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
        int y = (int) (Math.random() * (18 - 2)) + 2;
        int x = 60;
        position = new Position(x,y);
        int ori = (int) (Math.random() * (10 - 1)) + 1;
        if (ori <= 2) {
            orient = 3;
            if (y < 7) size = (int) (Math.random() * ((y-1) - 1)) + 1;
            else size = (int) (Math.random() * (5 - 1)) + 1;
        }
        else if (ori <= 5) {
            orient = 2;
            if (y < 7) size = (int) (Math.random() * ((y-1) - 1)) + 1;
            else size = (int) (Math.random() * (5 - 1)) + 1;

        }
        else if (ori <= 8) {
            orient = 1;
            size = (int) (Math.random() * (5 - 1)) + 1;
        }
        else {
            orient = 4;
            if (y > 14) size = (int) (Math.random() * ((19-y) - 1)) + 1;
            else size = (int) (Math.random() * (5 - 1)) + 1;

        }
    }
    @Override
    public void move() {
        position.setX(position.getX()-1);
    }

    public boolean type() {return true;}
}

