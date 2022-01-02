package ldts.model;

public class Player extends Element{
    public Player() {
        super(new Position(0, 2));
    }
    public void goHigher(){
        position.y ++;
    }

    public void goLower() {
        position.y--;
    }
}
