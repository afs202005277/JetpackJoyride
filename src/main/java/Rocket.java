//Rocket e 6x4



public class Rocket extends Obstacle{
    private Position position;

    public int getX() {return position.getX();}
    public void setX(int x) {position.setX(x);}
    public int getY() {return position.getY();}
    public void setY(int y) {position.setY(y);}

    public Rocket() {
        int y = (int) (Math.random() * (74 - 1)) + 1;
        position = new Position(132,y);
    }
    public void moveRocket() {
        position.setX(position.getX()-1);
    }
}
