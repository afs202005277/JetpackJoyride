package ldts.model;

import com.googlecode.lanterna.graphics.TextGraphics;

import java.util.ArrayList;
import java.util.List;

public class LaserGroup extends Obstacle {
    private List<LaserSing> lasers;
    private Position position;
    private final int orient;  // 1. -  2. |  3. \  4.  /
    private final int size;

    public List<LaserSing> getLasers() {return lasers;}

    public int getX() {return position.getX();}
    public void setX(int x) {position.setX(x);}
    public int getY() {return position.getY();}
    public void setY(int y) {position.setY(y);}
    public Position getPosition() {
        return position;
    }
    @Override
    public Position getLastPosition() {
        return lasers.get(lasers.size()-1).getPosition();
    }


    public int getOrient() {return orient;}
    public int getSize() {return size;}

    public LaserGroup() {
        size = (int) (Math.random() * (18 - 5)) + 5;
        int ori = (int) (Math.random() * (10 - 1)) + 1;
        if (ori <= 2) {
            orient = 3;
            int round = (int) Math.round(Math.sin(Math.PI / 4));
            int y = (int) (Math.random() * (74 - size * round)) + round;
            int x = 132;
            position = new Position(x,y);
            lasers = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                LaserSing sing = new LaserSing(x,y);
                lasers.add(sing);
                x++;y--;
            }
        }
        else if (ori <= 5) {
            orient = 2;
            int y = (int) (Math.random() * (74 - size)) + size;
            position = new Position(132,y);
            lasers = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                LaserSing sing = new LaserSing(132,y);
                lasers.add(sing);
                y--;
            }
        }
        else if (ori <= 8) {
            orient = 1;
            int y = (int) (Math.random() * (74 - 1)) + 1;
            int x = 132;
            position = new Position(x,y);
            lasers = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                LaserSing sing = new LaserSing(x,y);
                lasers.add(sing);
                x++;
            }
        }
        else {
            orient = 4;
            int round = (int) Math.round(Math.sin(Math.PI / 4));
            int y = (int) (Math.random() * ((74 - size * round) - 1)) + 1;
            int x = 132;
            position = new Position(x,y);
            lasers = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                LaserSing sing = new LaserSing(x,y);
                lasers.add(sing);
                x++;y++;
            }
        }
    }
    @Override
    public void move() {
        position.setX(position.getX()-1);
        for (LaserSing laser : lasers) {
            laser.moveLaser();
        }
    }

    @Override
    public void draw(TextGraphics graphics) {
        graphics.drawLine(this.getX(), this.getY(), this.getLastPosition().getX(), this.getLastPosition().getY(), 'L');
    }
}

