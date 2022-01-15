package ldts.model;

public abstract class Element {
    protected Position position;

    public Element() {
        position = new Position(-1, -1);
    }

    public Position getPosition(){
        return position;
    }

    public void move(int x, int y){
        position.setX(position.getX() + x);
        position.setY(position.getY() + y);
    }

    public int getX(){
        return position.getX();
    }

    public void setX(int x){
        position.setX(x);
    }

    public int getY(){
        return position.getY();
    }

    public void setY(int y){
        position.setY(y);
    }

    public abstract boolean isLaser();
    public abstract boolean isRocket();
    public abstract boolean isCoin();
}
