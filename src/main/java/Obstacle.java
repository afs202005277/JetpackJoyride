import com.googlecode.lanterna.graphics.TextGraphics;

public abstract class Obstacle {
    public abstract void move();
    public abstract int getX();
    public abstract void setX(int x);
    public abstract int getY();
    public abstract void setY(int y);
    public abstract Position getLastPosition();

    public abstract void draw(TextGraphics graphics);

}
