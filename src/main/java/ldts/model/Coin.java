package ldts.model;

import ldts.view.View;

public class Coin extends Element {
    private boolean collected;

    public Coin() {
        collected = false;
        int y = (int) (Math.random() * (View.getRows() - 2)) + 2;
        int x = 51;//View.getScreen().getTerminalSize().getColumns();
        position = new Position(x,y);
    }

    public Coin(int x, int y){
        collected = false;
        position = new Position(x, y);
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
        return true;
    }

    public boolean isCollected() {
        return collected;
    }

    public void collect(){
        collected = true;
    }
}
