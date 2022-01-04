package ldts.model;

import com.googlecode.lanterna.graphics.TextGraphics;

public interface Obstacle {
    void move();
    int getX();
    void setX(int x);
    int getY();
    void setY(int y);
    Position getLastPosition();
    boolean type();
    public Position getPosition();

}
