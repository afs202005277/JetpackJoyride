package ldts.model;

public class Player {
    Position position;

    public Player() {
        position = new Position(4, 2);
    }
    public void goHigher(){
        position.y ++;
    }

    public void goLower() {
        position.y--;
    }
}
