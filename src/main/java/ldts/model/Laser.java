package ldts.model;


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
        int y = (int) (Math.random() * (View.getRows() - 2)) + 2;
        int x = 51;//View.getScreen().getTerminalSize().getColumns();
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

