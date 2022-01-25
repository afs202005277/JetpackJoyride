package ldts.control;

import ldts.model.*;

public class CollisionChecker {
    public boolean checkCollision(Element object, Player player) {
        boolean collision = false;
        if (object.isLaser()) {
            collision = checkCollisionLaser((Laser) object, player);
        } else if (object.isRocket()) {
            collision = checkCollisionsRocket((Rocket) object, player);
        } else if (object.isCoin()) {
            collision = checkCollisionCoin((Coin) object, player);
        }
        return collision;
    }

    private boolean checkCollisionCoin(Coin coin, Player player) {
        return coin.getPosition().equals(player.getPosition());
    }

    public boolean checkCollisionLaser(Laser laser, Player player) {
        boolean collision = false;
        if (laser.getPosition().getX() <= player.getPosition().getX() && player.getPosition().getX() <= laser.getLastPosition().getX()) {
            int m = 0;
            if (laser.getPosition().getX() == laser.getLastPosition().getX() && laser.getLastPosition().getY() <= player.getPosition().getY() && player.getPosition().getY() <= laser.getPosition().getY())
                collision = true;
            else {
                if (laser.getPosition().getY() > laser.getLastPosition().getY()) m = -1;
                else if (laser.getPosition().getY() < laser.getLastPosition().getY()) m = 1;

                if (m == 0 && laser.getPosition().getY() == player.getPosition().getY()) collision = true;

                if (m != 0) {

                    Position secondPos = new Position(laser.getPosition().getX(), laser.getPosition().getY() + (m < 0 ? -1 : 1));
                    Position thirdPos = new Position(laser.getPosition().getX() + 1, laser.getPosition().getY());

                    int b = laser.getPosition().getY() - m * laser.getPosition().getX();
                    int secondB = secondPos.getY() - m * secondPos.getX();
                    int thirdB = thirdPos.getY() - m * thirdPos.getX();

                    if (player.getPosition().getX() * m + b == player.getPosition().getY() || player.getPosition().getX() * m + secondB == player.getPosition().getY() && player.getPosition().getX() <= laser.getLastPosition().getX() - 1 || player.getPosition().getX() * m + thirdB == player.getPosition().getY() && player.getPosition().getX() >= laser.getPosition().getX() + 1)
                        collision = true;
                }
            }
        }
        return collision;
    }

    public boolean checkCollisionsRocket(Rocket rocket, Player player) {
        Position temp = new Position(rocket.getX() + 1, rocket.getPosition().getY());
        return rocket.getPosition().equals(player.getPosition()) || temp.equals(player.getPosition());
    }
}
