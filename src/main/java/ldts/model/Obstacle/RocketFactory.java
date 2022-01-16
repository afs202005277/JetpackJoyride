package ldts.model.Obstacle;

public class RocketFactory extends ObstacleFactory{


    @Override
    public Obstacle createObstacle() {
        return new Rocket();
    }
}
