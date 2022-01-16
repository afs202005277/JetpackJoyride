package ldts.model;

public class Player extends Element {

    public Player() {
        position = new Position(4, 2);
    }

    public Player(int x, int y) {position = new Position(x, y);}

    public void goHigher(){
        position.setY(position.getY()+1);
    }

    public void goLower() {
        position.setY(position.getY()-1);
    }

    @Override
    public boolean isLaser() {
        return false;
    }

    @Override
    public boolean isRocket() {
        return false;
    }

    @Override
    public boolean isCoin() {
        return false;
    }
}
