package ldts.model;


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
