package ldts.model;


import ldts.control.States.RunningState;
import ldts.view.View;

public class Laser extends Element  {
    private final int orient;  // 1. -  2. |  3. \  4.  /
    private final int size;

    @SuppressWarnings("UnnecessaryParentheses")
    public Position getLastPosition() {
        Position position1 = switch (orient) {
            case 1 -> new Position(position.getX() + size - 1, position.getY());
            case 2 -> new Position(position.getX(), position.getY() - size + 1);
            case 3 -> new Position(position.getX() + size - 1, position.getY() - size + 1);
            case 4 -> new Position(position.getX() + size - 1, position.getY() + size - 1);
            default -> position;
        };
        return position1;
    }


    public int getOrient() {return orient;}
    public int getSize() {return size;}

    public Laser() {
        int y = (int) (Math.random() * (View.getNumberRows() - 2)) + 2;
        int x = View.getNumberColumns();
        position = new Position(x,y);
        int ori = (int) (Math.random() * (10 - 1)) + 1;
        if (ori <= 2) {
            orient = 3;
            if (y < 7) size = (int) (Math.random() * ((y-1) - 1)) + 1;
            else size = (int) (Math.random() * (5 - 1)) + 1;
        }
        else if (ori <= 5) {
            orient = 2;
            if (y < 7) size = (int) (Math.random() * ((y-1) - 1)) + 1;
            else size = (int) (Math.random() * (5 - 1)) + 1;

        }
        else if (ori <= 8) {
            orient = 1;
            size = (int) (Math.random() * (5 - 1)) + 1;
        }
        else {
            orient = 4;
            if (y > 14) size = (int) (Math.random() * ((19-y) - 1)) + 1;
            else size = (int) (Math.random() * (5 - 1)) + 1;

        }
    }
    public Laser(Position position, int orient, int size) {
        this.position = position;
        this.orient = orient;
        this.size = size;
    }

    @Override
    public boolean checkCollision(Position pos) {
        int targetY = pos.getY(), targetX = pos.getX(), laserX = this.getX(), laserY = this.getY(), laserLastX = this.getLastPosition().getX(), laserLastY = this.getLastPosition().getY();
        boolean collision = false;
        if (laserX <= targetX && targetX <= laserLastX) {
            int m = 0;
            if (laserX == laserLastX && laserLastY <= targetY && targetY <= laserY)
                collision = true;
            else {
                if (laserY > laserLastY)
                    m = -1;
                else if (laserY < laserLastY)
                    m = 1;
                if (m == 0 && laserY == targetY)
                    collision = true;

                if (m != 0) {
                    collision = checkCollisionInclinedLaser(m, pos);
                }
            }
        }
        if (collision)
        {
            RunningState.endGame();
        }
        return collision;
    }

    private boolean checkCollisionInclinedLaser(int m, Position pos) {
        int targetX = pos.getX(), targetY = pos.getY(), laserX = this.getX(), laserY = this.getY();
        Position secondPos = new Position(laserX, laserY + (m < 0 ? -1 : 1));
        Position thirdPos = new Position(laserX + 1, laserY);

        int b = laserY - m * laserX;
        int secondB = secondPos.getY() - m * secondPos.getX();
        int thirdB = thirdPos.getY() - m * thirdPos.getX();
        return (targetX * m + b == targetY) || (targetX * m + secondB == targetY && targetX <= getLastPosition().getX() - 1) || (targetX * m + thirdB == targetY && targetX >= laserX + 1);
    }
    
    @Override
    public boolean isLaser() {return true;}

    @Override
    public boolean isRocket() {
        return false;
    }

    @Override
    public boolean isCoin() {
        return false;
    }
}

