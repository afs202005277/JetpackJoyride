package ldts.model;

public class Player implements Element {
    Position position;

    public Player() {
        position = new Position(4, 2);
    }
    public void goHigher(){
        position.setY(position.getY()+1);
    }

    public void goLower() {
        position.setY(position.getY()-1);
    }

    @Override
    public Position getPosition() {
        return position;
    }

    @Override
    public void move(int x, int y) {
        position.setX(position.getX()+x);
        position.setY(position.getY()+y);
    }

    @Override
    public int getX() {
        return position.getX();
    }

    @Override
    public void setX(int x) {
        position.setX(x);
    }

    @Override
    public int getY() {
        return position.getY();
    }

    @Override
    public void setY(int y) {
        position.setX(y);
    }
}
