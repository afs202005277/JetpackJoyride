//Rocket e 6x4



public class Rocket extends Obstacle{
    private int x;
    private int y;

    public int getX() {return x;}
    public void setX(int x) {this.x = x;}
    public int getY() {return y;}
    public void setY(int y) {this.y = y;}

    public Rocket() {
        y = (int) (Math.random() * (74 - 1)) + 1;
        x = 132;
    }
    public void moveRocket() {

    }
}
