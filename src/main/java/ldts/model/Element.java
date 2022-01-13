package ldts.model;

public interface Element {
    public Position getPosition();
    void move();
    int getX();
    void setX(int x);
    int getY();
    void setY(int y);
}
