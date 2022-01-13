package ldts.model;

public interface Element {
    Position getPosition();
    void move(int x, int y);
    int getX();
    void setX(int x);
    int getY();
    void setY(int y);
    boolean isObstacle();
}
