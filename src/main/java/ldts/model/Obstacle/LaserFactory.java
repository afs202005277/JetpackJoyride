package ldts.model.Obstacle;

public class LaserFactory extends ObstacleFactory {

    @Override
    public Obstacle createObstacle() {
        return new Laser();
    }
}
