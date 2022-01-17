package ldts.model;

import ldts.view.View;

public class Rocket extends Element {

    public Rocket() {
        int y = (int) (Math.random() * (View.getRows() - 2)) + 2;
        position = new Position(/*View.getScreen().getTerminalSize().getColumns()*/51,y);
    }
    public Rocket(int x, int y) {
        position= new Position(x,y);
    }

    @Override
    public boolean isLaser() {
        return false;
    }

    @Override
    public boolean isRocket() {
        return true;
    }

    @Override
    public boolean isCoin() {
        return false;
    }
}

