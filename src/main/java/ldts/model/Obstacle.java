package ldts.model;


public interface Obstacle extends Element{
    Position getLastPosition();
    boolean isLaser();
}
